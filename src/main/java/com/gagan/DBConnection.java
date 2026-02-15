package com.gagan;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/user_task_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Gaganjio@14";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
}


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Insert User");
            System.out.println("2. View Users");
            System.out.println("3. Delete User");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice ;
            if(sc.hasNextInt()){
                choice = sc.nextInt();
                sc.nextLine();
            }else{
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter email: ");
                String email = sc.nextLine();

                UserService.insertUser(name, email);

            } else if (choice == 2) {
                System.out.println("\n");
                UserService.fetchUsers();

            } else if (choice == 3) {
                System.out.println("\n");
            UserService.fetchUsers();
            System.out.print("Enter user ID to delete: ");

            if (sc.hasNextInt()) {
                int id = sc.nextInt();
                sc.nextLine();
                UserService.deleteUser(id);
            } else {
                System.out.println("Invalid ID.");
                sc.next();
            }

        } else if (choice == 4) {

            break;
        }
          else{
                System.out.println("Please choose a valid option !");
                continue;
            }
    }

        sc.close();
    }


}