/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NoID
 */
public class SQLiteJDBCDriverConnection {

    private final static String DATABASE_URI = "jdbc:sqlite:D:/FileTransfer/FileTransferDataBase.db";
    private final Connection connection;
    public static SQLiteJDBCDriverConnection instance;

    public static synchronized SQLiteJDBCDriverConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteJDBCDriverConnection();
        }
        return instance;
    }

    private SQLiteJDBCDriverConnection() {
        this.connection = createConnection();

    }
    /**
    private String getDataBasePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        String resource = classLoader.getResource("/FileTransferDataBase.db").getPath();
        System.out.println(resource);
        return "jdbc:sqlite:" +resource;
    }
    **/
    
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URI);
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteJDBCDriverConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

}
