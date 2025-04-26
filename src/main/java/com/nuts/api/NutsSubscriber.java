package com.nuts.api;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;

/**
 * Thin wrapper around the NATS client.
 */
public class NutsSubscriber {

    private final Connection natsConnection;
    private final Dispatcher dispatcher;

    public NutsSubscriber(Connection natsConnection) {
        this.natsConnection = natsConnection;
        this.dispatcher = natsConnection.createDispatcher(msg -> { /* no‑op default */ });
    }

    /** Subscribe and forward each message to the given handler. */
    public void subscribe(String subject, java.util.function.Consumer<String> handler) {
        dispatcher.subscribe(subject, m -> handler.accept(new String(m.getData())));
        System.out.printf("📡 Subscribed to %s%n", subject);
    }
}
