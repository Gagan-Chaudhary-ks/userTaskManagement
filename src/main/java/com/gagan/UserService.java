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


    public static void deleteUser(int userId) {
        try {
            Connection conn = DBConnection.getConnection();

            // Step 1: Delete tasks of that user
            String deleteTasks = "DELETE FROM tasks WHERE user_id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(deleteTasks);
            pstmt1.setInt(1, userId);
            pstmt1.executeUpdate();

            // Step 2: Delete user
            String deleteUser = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(deleteUser);
            pstmt2.setInt(1, userId);
            int rowsAffected = pstmt2.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found.");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
