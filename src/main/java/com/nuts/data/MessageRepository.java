package com.nuts.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageRepository {

    public void save(String content) {
        String sql = "INSERT INTO messages (content) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, content);
            stmt.executeUpdate();
            System.out.println("Saved to DB.");
        } catch (SQLException e) {
            System.err.println("Failed to save message: " + e.getMessage());
        }
    }
}
