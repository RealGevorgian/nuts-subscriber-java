package com.nuts.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MessageService {
    private final Connection conn;

    public MessageService(Connection conn) {
        this.conn = conn;
    }

    /** Persist a single message row */
    public void saveMessage(String content) {
        String sql = "INSERT INTO messages(content, timestamp) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, content);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            System.out.println("✔️  Saved to DB");
        } catch (SQLException e) {
            System.err.println("❌ Failed to save message: " + e.getMessage());
        }
    }
}
