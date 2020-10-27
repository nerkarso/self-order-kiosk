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

  public Category getOneById(int id) {
    Category category = null;

    this.connect();

    try {
      String sql = "SELECT * FROM categories WHERE category_id = ? LIMIT 1";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        category = mapResultOneCategory(result);
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return category;
  }

  public int createOne(Category category) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "INSERT INTO categories (category_name) VALUES (?)";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, category.getName());
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
  }

  public int updateOne(Category category) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, category.getName());
      stmt.setInt(2, category.getId());
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
      String sql = "DELETE FROM categories WHERE category_id = ?";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
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
