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

    public void viewTasksByUser() {

        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        sc.nextLine();

        try {

            // Validate user
            if (!userExists(userId)) {
                System.out.println("User not found.");
                return;
            }

            String query = """
                SELECT u.name, t.task_id, t.title, t.status
                FROM tasks t
                JOIN users u ON t.user_id = u.id
                WHERE u.id = ?
                """;

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                boolean hasTasks = false;

                while (rs.next()) {
                    if (!hasTasks) {
                        String userName = rs.getString("name");
                        System.out.println("Tasks for User: " + userName);
                    }

                    hasTasks = true;
                    System.out.println(
                            "Task ID: " + rs.getInt("task_id") +
                                    " | Title: " + rs.getString("title") +
                                    " | Status: " + rs.getString("status")
                    );
                }

                if (!hasTasks) {
                    System.out.println("No tasks found for this user.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred while fetching tasks.");
            e.printStackTrace();
        }
    }

    private boolean taskExists(int taskId) throws SQLException {

        String query = "SELECT task_id FROM tasks WHERE task_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, taskId);

            ResultSet rs = ps.executeQuery();

            return rs.next();
        }
    }



}
