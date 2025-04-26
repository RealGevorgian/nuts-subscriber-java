package com.nuts.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data‑layer object.  Pure SQL only, no business rules.
 */
public class MessageRepository {

    private static final String INSERT_SQL =
            "INSERT INTO messages(content, created_at) VALUES (?, NOW())";

    private final Connection connection;

    public MessageRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(String content) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setString(1, content);
            ps.executeUpdate();
        }
    }
}
