package com.gagan;

import java.sql.Connection;
import java.util.Scanner;

public class TaskService {

    private Connection conn;
    private Scanner sc;

    public TaskService(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }
}
