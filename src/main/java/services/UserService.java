/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

public class UserService extends DatabaseService {

  public ArrayList<User> getAll() {
    ArrayList<User> users = new ArrayList<>();

    this.connect();

    try {
      String sql = "SELECT * FROM users";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        users.add(mapResultOneUser(result));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return users;
  }

  public User getOne(int id) {
    User user = null;

    this.connect();

    try {
      String sql = "SELECT * FROM users WHERE user_id = ? LIMIT 1";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        user = mapResultOneUser(result);
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return user;
  }

  public int createOne(User user) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "INSERT INTO users (user_fullname, username, password) VALUES (?, ?, ?)";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, user.getFullName());
      stmt.setString(2, user.getUsername());
      stmt.setString(3, user.getPassword());
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
  }

  public int updateOne(User user) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "UPDATE users SET user_fullname = ?, username = ?, password = ? WHERE user_id = ?";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, user.getFullName());
      stmt.setString(2, user.getUsername());
      stmt.setString(3, user.getPassword());
      stmt.setInt(4, user.getId());
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
  }

  public int deleteOne(int id) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "DELETE FROM users WHERE user_id = ?";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
  }

  private User mapResultOneUser(ResultSet result) {
    User user = null;

    try {
      user = new User();
      user.setId(result.getInt("user_id"));
      user.setFullName(result.getString("user_fullname"));
      user.setUsername(result.getString("username"));
      user.setPassword(result.getString("password"));
    } catch (SQLException e) {
      System.out.println(e);
    }

    return user;
  }

}
