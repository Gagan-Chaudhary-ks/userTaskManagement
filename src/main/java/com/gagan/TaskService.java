package com.gagan;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;

public class TaskService {

    private Connection conn;
    private Scanner sc;

    public TaskService(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }

    private boolean userExists(int userId) {

        String query = "SELECT id FROM users WHERE id = ?";

        try (var ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);

            var rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error checking user existence.");
            e.printStackTrace();
            return false;
        }
    }

}
