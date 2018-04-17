/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.sql.repository;

import com.woop.filetransferprototype.local.entity.LocalFile;

import com.woop.filetransferprototype.local.sql.connection.SQLiteJDBCDriverConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NoID
 */
public class LocalStorageFileRepository implements IFileRepository {

    private Connection getSqliteConnection() {
           return SQLiteJDBCDriverConnection.getInstance().getConnection();
    }

    public LocalFile getById(int id) {
        try {
            Connection connection = getSqliteConnection();
            String sql = "select * from localfiles where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return parseFiles(rs).get(0);
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean save(LocalFile entity) {
        try {
            System.out.println(entity.toString());
            Connection connection = getSqliteConnection();
            String sql = "insert into localfiles values ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(2, entity.getTargetFileName());
            ps.setString(3, entity.getSubmittedFileName());
            ps.setInt(4, entity.getHost());
            ps.setString(5, entity.getDirectory());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(LocalFile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<LocalFile> getFilesByHost(int host) {
        try {
            Connection connection = getSqliteConnection();
            String sql = "select * from localfiles where host = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, host);
            ResultSet rs = ps.executeQuery();
            return parseFiles(rs);
        } catch (SQLException ex) {
            return null;
        }
    }

    private List<LocalFile> parseFiles(ResultSet rs) {
        try {
            List<LocalFile> list = new ArrayList<LocalFile>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String targetFileName = rs.getString(2);
                String submittedFileName = rs.getString(3);
                int host = rs.getInt(4);
                String directory = rs.getString(5);
                LocalFile file = new LocalFile(id, host, targetFileName,submittedFileName, directory);
                list.add(file);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<LocalFile> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean remove(int id) {
        try {
            Connection connection = getSqliteConnection();
            String sql = "DELETE FROM localfiles WHERE id = ?";
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
