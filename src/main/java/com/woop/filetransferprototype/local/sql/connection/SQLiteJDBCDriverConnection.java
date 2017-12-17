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
public class SQLiteJDBCDriverConnection implements ISQLiteConnection {
    private final String DATABASE_URI = "jdbc:sqlite:D:/NetBeans 8.2/projects/FileTransferPrototype/src/main/resources/FileTransferDataBase.db";
    private final Connection connection;
    
    public SQLiteJDBCDriverConnection(){
            this.connection = createConnection();
        
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URI);
                    } catch (SQLException ex) {
            Logger.getLogger(SQLiteJDBCDriverConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
    
    
    
    
}
