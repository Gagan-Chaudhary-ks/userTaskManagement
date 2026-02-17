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

    public void addTask() {

        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        try {
            // Validate user
            if (!userExists(userId)) {
                System.out.println("User not found.");
                return;
            }

            String query = "INSERT INTO tasks (user_id, title, status) VALUES (?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, "PENDING");

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Task added successfully.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred while adding task.");
            e.printStackTrace();
        }
    }



}
