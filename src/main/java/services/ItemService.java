/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;
import models.Item;

public class ItemService extends DatabaseService {

  private final String queryItems = "SELECT * FROM items itm JOIN categories cat ON cat.category_id = itm.category_id";

  public ArrayList<Item> getAll() {
    ArrayList<Item> items = new ArrayList<>();

    this.connect();

    try {
      String sql = queryItems.concat(" ORDER BY itm.item_id");
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        items.add(mapResultOneItem(result));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return items;
  }

  public ArrayList<Item> getAllByCategory(int id) {
    ArrayList<Item> items = new ArrayList<>();

    this.connect();

    try {
      String sql = queryItems.concat(" WHERE cat.category_id = ?");
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);

      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        items.add(mapResultOneItem(result));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return items;
  }

  public Item getOneById(int id) {
    Item item = null;

    this.connect();

    try {
      String sql = queryItems.concat(" WHERE itm.item_id = ? LIMIT 1");
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);

      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        item = mapResultOneItem(result);
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return item;
  }

  public int createOne(Item item) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "INSERT INTO items (item_name, item_price, item_image, category_id) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, item.getName());
      stmt.setDouble(2, item.getPrice());
      stmt.setString(3, item.getImage());
      stmt.setInt(4, item.getCategory().getId());
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
  }

  public int updateOne(Item item) {
    int rowCount = 0;

    this.connect();

    try {
      String sql = "UPDATE items SET item_name = ?, item_price = ?, item_image = ?, category_id = ? WHERE item_id = ?";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, item.getName());
      stmt.setDouble(2, item.getPrice());
      stmt.setString(3, item.getImage());
      stmt.setInt(4, item.getCategory().getId());
      stmt.setInt(5, item.getId());
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
      String sql = "DELETE FROM items WHERE item_id = ?";
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return rowCount;
  }

  private Item mapResultOneItem(ResultSet result) {
    Item item = null;

    try {
      Category category = new Category();
      category.setId(result.getInt("category_id"));
      category.setName(result.getString("category_name"));
      item = new Item();
      item.setId(result.getInt("item_id"));
      item.setName(result.getString("item_name"));
      item.setPrice(result.getDouble("item_price"));
      item.setImage(result.getString("item_image"));
      item.setCategory(category);
    } catch (SQLException e) {
      System.out.println(e);
    }

    return item;
  }

}
