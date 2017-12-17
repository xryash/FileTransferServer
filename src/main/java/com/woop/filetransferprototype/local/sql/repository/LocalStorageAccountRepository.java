/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.sql.repository;

import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.sql.connection.ISQLiteConnection;
import com.woop.filetransferprototype.local.sql.connection.SQLiteJDBCDriverConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NoID
 */
public class LocalStorageAccountRepository implements IAccountRepository {

    

    private ISQLiteConnection getSqliteConnection() {
            return new SQLiteJDBCDriverConnection();
    }
    
    private Account assembleAccount(ResultSet rs) {
        try {
            rs.next();
            int id = rs.getInt(1);
            String login = rs.getString(2);
            String password = rs.getString(3);
            String token = rs.getString(4);
            return new Account(id,login,password,token);
        } catch (SQLException ex) {
            Logger.getLogger(LocalStorageAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getById(int id) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "select * from accounts where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return assembleAccount(rs);
        } catch (SQLException ex) {
            Logger.getLogger(LocalStorageAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getByLogin(String login) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "select * from accounts where login = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return assembleAccount(rs);
        } catch (SQLException ex) {
            Logger.getLogger(LocalStorageAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getByToken(String token) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "select * from accounts where token = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            return assembleAccount(rs);
        } catch (SQLException ex) {
            Logger.getLogger(LocalStorageAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean save(Account entity) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "insert into accounts values ( ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,entity.getLogin());
            ps.setString(2,entity.getPassword());
            ps.setString(3,entity.getToken());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocalStorageAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean remove(int id) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "DELETE FROM accounts WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocalStorageAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}