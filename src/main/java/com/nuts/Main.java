package com.nuts;

import io.github.cdimascio.dotenv.Dotenv;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import io.nats.client.Options;

import com.nuts.data.DBConnection;
import com.nuts.service.MessageService;

import java.time.Duration;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1) load environment (or fall back to defaults)
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String natsUrl = dotenv.get("NATS_URL", "nats://localhost:4222");
        String dbUrl   = dotenv.get("DB_URL",   "jdbc:postgresql://localhost:5432/nutsdb");
        String dbUser  = dotenv.get("DB_USER",  "postgres");
        String dbPass  = dotenv.get("DB_PASS",  "1234");

        // 2) connect to NATS
        System.out.println("▶ Connecting to NATS at " + natsUrl);
        Options opts = new Options.Builder()
                .server(natsUrl)
                .connectionTimeout(Duration.ofSeconds(5))
                .build();

        try (Connection natsConn = Nats.connect(opts)) {
            System.out.println("✔ Connected to NATS");

            // 3) connect to Postgres
            try (var jdbc = DBConnection.getConnection(dbUrl, dbUser, dbPass)) {
                System.out.println("✔ Connected to Postgres");

                // 4) business layer
                MessageService svc = new MessageService(jdbc);

                // 5) subscribe & dispatch
                Dispatcher d = natsConn.createDispatcher(msg -> {
                    String body = new String(msg.getData());
                    System.out.println("📥 Received: " + body);
                    svc.saveMessage(body);
                });
                d.subscribe("updates");
                System.out.println("▶ Subscribed to subject 'updates'");

                // 6) keep the JVM alive
                Thread.currentThread().join();

            } catch (SQLException sqe) {
                System.err.println("DB error ▶ " + sqe.getMessage());
            }

        } catch (Exception ne) {
            System.err.println("NATS error ▶ " + ne.getMessage());
        }
    }
}
