package com.example.momentweb.dao;


import com.example.momentweb.entity.Comment;
import com.example.momentweb.entity.User;
import com.example.momentweb.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Dao {
    ArrayList<String> l = new ArrayList<String>();

    public boolean select(User user) {
        final Connection connection = DBUtils.getConnection();
        final String sql = "select * from user where name = ? and password = ?";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closeConnection();
        }
    }

    public ArrayList<String> selectComment(String url) {
        final Connection connection = DBUtils.getConnection();
        final String sql = "select * from comment where url = ?";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, url);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String comment = resultSet.getString("comment");
                l.add(comment);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.closeConnection();
        }
    }

    public boolean insert(User user) {
        final Connection connection = DBUtils.getConnection();
        final String sql = "insert into user(name,password) values(?,?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            return preparedStatement.getUpdateCount() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closeConnection();
        }
    }

    public boolean insertComment(Comment c) {
        final Connection connection = DBUtils.getConnection();
        final String sql = "insert into comment(url,comment) values(?,?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getUrl());
            preparedStatement.setString(2, c.getComment());
            preparedStatement.executeUpdate();
            return preparedStatement.getUpdateCount() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closeConnection();
        }
    }
}