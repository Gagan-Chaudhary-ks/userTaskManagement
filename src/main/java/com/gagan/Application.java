package com.gagan;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static final String URL = "jdbc:mysql://localhost:3306/user_task_db";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
}


    public static void main(String[] args) throws SQLException {

        Connection conn = Application.getConnection();
        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService(conn, sc);
        TaskService taskService = new TaskService(conn, sc);

        while (true) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. User Menu");
            System.out.println("2. Task Menu");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }

            if (choice == 1) {

                while (true) {
                    System.out.println("\n--- USER MENU ---");
                    System.out.println("1. Insert User");
                    System.out.println("2. View Users");
                    System.out.println("3. Delete User");
                    System.out.println("4. Back");
                    System.out.print("Choose option: ");

                    int userChoice;

                    if (sc.hasNextInt()) {
                        userChoice = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("Invalid input.");
                        sc.nextLine();
                        continue;
                    }

                    if (userChoice == 1) {
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter email: ");
                        String email = sc.nextLine();

                        userService.insertUser(name, email);

                    } else if (userChoice == 2) {
                        userService.fetchUsers();

                    } else if (userChoice == 3) {
                        userService.deleteUser();

                    } else if (userChoice == 4) {
                        break;

                    } else {
                        System.out.println("Invalid option.");

                    }
                }

            } else if (choice == 2) {

                while (true) {
                    System.out.println("\n--- TASK MENU ---");
                    System.out.println("1. Add Task");
                    System.out.println("2. View Tasks By User");
                    System.out.println("3. Update Task Status");
                    System.out.println("4. Delete Task");
                    System.out.println("5. Back");
                    System.out.print("Choose option: ");

                    int taskChoice;

                    if (sc.hasNextInt()) {
                        taskChoice = sc.nextInt();
                        sc.nextLine();
                    } else {
                        System.out.println("Invalid input.");
                        sc.nextLine();
                        continue;
                    }

                    if (taskChoice == 1) {
                        taskService.addTask();

                    } else if (taskChoice == 2) {
                        taskService.viewTasksByUser();

                    } else if (taskChoice == 3) {
                        taskService.updateTaskStatus();

                    } else if (taskChoice == 4) {
                        taskService.deleteTask();

                    } else if (taskChoice == 5) {
                        break;

                    } else {
                        System.out.println("Invalid option.");

                    }
                }

            } else if (choice == 3) {
                break;

            } else {
                System.out.println("Invalid option.");

            }
        }

        sc.close();
        conn.close();
    }



}