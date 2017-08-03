package com.joedpreece.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public static Connection connectToDatabase() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mytestdb",
                            "example", "example");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully.\n");
        return c;
    }
}
