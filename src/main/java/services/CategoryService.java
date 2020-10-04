/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;

public class CategoryService extends DatabaseService {

  public ArrayList<Category> getAll() {
    ArrayList<Category> categories = new ArrayList<>();

    this.connect();

    try {
      PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM categories");
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        categories.add(mapResultOneCategory(result));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return categories;
  }

  private Category mapResultOneCategory(ResultSet result) {
    Category category = null;

    try {
      category = new Category();
      category.setId(result.getInt("category_id"));
      category.setName(result.getString("category_name"));
    } catch (SQLException e) {
      System.out.println(e);
    }

    return category;
  }

}
