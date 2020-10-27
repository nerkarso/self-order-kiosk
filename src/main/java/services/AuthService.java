/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService extends DatabaseService {

  public boolean getAuth(models.User user) {
    boolean isAuth = false;

    this.connect();

    try {
      String sql = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        isAuth = true;
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    return isAuth;
  }

}
