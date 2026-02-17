package com.gagan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
import  java.sql.SQLException;

public class UserService {

    private Connection conn;
    private Scanner sc;

    public UserService(Connection conn, Scanner sc){
     this.conn = conn;
     this.sc = sc;
    }

    public void fetchUsers() {

        String query = "SELECT * FROM users";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println(id + " | " + name + " | " + email);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching users.");
            e.printStackTrace();
        }
    }


    public void insertUser(String name, String email) {

        String query = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);

            pstmt.executeUpdate();

            System.out.println("User inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting user.");
            e.printStackTrace();
        }
    }


    public void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int userId = sc.nextInt();

        try {
            conn.setAutoCommit(false);

            String checkUser = "SELECT id FROM users WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkUser)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("User not found.");
                    conn.setAutoCommit(true);
                    return;
                }
            }

            String deleteTasks = "DELETE FROM tasks WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteTasks)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            String deleteUser = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteUser)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("User and associated tasks deleted successfully.");

        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("Transaction failed. Rolled back.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
