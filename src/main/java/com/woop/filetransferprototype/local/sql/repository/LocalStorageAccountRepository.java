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
            String salt = rs.getString(5);
            String role = rs.getString(6);
            return new Account(id,login,password,token,salt,role);
        } catch (SQLException ex) {
            return null;
        }
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
            return null;
        }
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
            return null;
        }
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
            return null;
        }
        
    }
    
    public Account getByLoginAndPassword(String login, String password) {
        try {
                Connection connection = getSqliteConnection().getConnection();
                String sql = "select * from accounts where login = ? and password = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                return assembleAccount(rs);
            } catch (SQLException ex) {
                return null;
            }
    }
    
    public boolean save(Account entity) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "insert into accounts values ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            //ps.setInt(1,0);
            ps.setString(2,entity.getLogin());
            ps.setString(3,entity.getPassword());
            ps.setString(4,entity.getToken());
            ps.setString(5,entity.getSalt());
            ps.setString(6, entity.getRole());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }

    
    public boolean remove(String login) {
        try {
            Connection connection = getSqliteConnection().getConnection();
            String sql = "DELETE FROM accounts WHERE login = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
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
            return false;
        }
    }
}
