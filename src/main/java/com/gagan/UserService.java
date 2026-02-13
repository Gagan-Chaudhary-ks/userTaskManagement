package com.gagan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UserService {

    public static void fetchUsers() {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT * FROM users";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println(id + " | " + name + " | " + email);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(String name, String email) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, email);

            pstmt.executeUpdate();

            System.out.println("User inserted successfully!");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
