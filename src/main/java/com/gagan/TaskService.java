package com.gagan;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaskService {

    private Connection conn;
    private Scanner sc;

    public TaskService(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }

    private boolean userExists(int userId) throws SQLException {
        String query = "SELECT id FROM users WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }


}
