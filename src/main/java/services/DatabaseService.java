/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseService {

  private final String url;
  private final String username;
  private final String password;
  public Connection conn;

  DatabaseService() {
    ResourceBundle bundle = ResourceBundle.getBundle("app/db");
    url = bundle.getString("url");
    username = bundle.getString("username");
    password = bundle.getString("password");
  }

  public void connect() {
    try {
      conn = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public void disconnect() {
    try {
      conn.close();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

}
