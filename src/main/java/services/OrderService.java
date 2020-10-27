/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Order;

public class OrderService extends DatabaseService {

  private final String queryOrders = "SELECT * FROM orders";

  public ArrayList<Order> getAll() {
    ArrayList<Order> orders = new ArrayList<>();

    this.connect();

    try {
      String sql = queryOrders.concat(" ORDER BY order_date");
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        orders.add(mapResultOneOrder(result));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return orders;
  }

  public Order getOne(int id) {
    Order order = null;

    this.connect();

    try {
      String sql = queryOrders.concat(" WHERE order_id = ? LIMIT 1");
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setInt(1, id);

      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        order = mapResultOneOrder(result);
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return order;
  }

  public int createOne(Order order) {
    return 0;
  }

  private Order mapResultOneOrder(ResultSet result) {
    Order order = null;

    try {
      order = new Order();
      order.setId(result.getInt("order_id"));
      order.setStatus(result.getInt("order_status"));
      order.setEatingLocation(result.getString("order_eating_location"));
      order.setPaymentMethod(result.getString("order_payment_method"));
      order.setDate(result.getDate("order_date"));
    } catch (SQLException e) {
      System.out.println(e);
    }

    return order;
  }

}
