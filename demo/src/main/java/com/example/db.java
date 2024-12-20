package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
/*db : abstraction de la source de données (DataSource). */
/* Une seule instance signifie que les paramètres de connexion (URL, utilisateur, etc.) sont gérés en un seul endroit. */
public class db {
    private static db instance;//singleton
    private Connection cnn;
    private static final String url = "jdbc:mysql://localhost:3306/reservationsdao";
    private static final String user = "root";
    private static final String psswd = "";

    private db() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.cnn = DriverManager.getConnection(url, user, psswd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static db getInstance() {
        if (instance == null) {
            instance = new db();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnn;
    }

    public void close() {
        try {
            if (cnn != null && !cnn.isClosed()) {
                cnn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

