/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

public class UserService extends DatabaseService {

  public int Auth() {
    return 0;
  }

  public ArrayList<User> getAll() {
    ArrayList<User> users = new ArrayList<>();

    return users;
  }

  public User getOne(int id) {
    User user = null;

    return user;
  }

  public int createOne(User user) {
    return 0;
  }

  public int updateOne(User user) {
    return 0;
  }

  public int deleteOne(int id) {
    return 0;
  }

  private User mapResultOneUser(ResultSet result) {
    User user = null;

    try {
      user = new User();
      user.setId(result.getInt("user_id"));
      user.setName(result.getString("user_fullname"));
      user.setUsername(result.getString("username"));
    } catch (SQLException e) {
      System.out.println(e);
    }

    return user;
  }

}
