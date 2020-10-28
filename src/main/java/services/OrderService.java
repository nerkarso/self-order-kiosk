/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Order;
import models.OrderDetail;

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
    int orderId = 0;

    this.connect();

    try {
      String sql = "INSERT INTO orders (order_eating_location, order_payment_method, order_status) VALUES (?, ?, ?)";
      PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, order.getEatingLocation());
      stmt.setString(2, order.getPaymentMethod());
      stmt.setInt(3, order.getStatus());
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        orderId = rs.getInt(1);
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    this.disconnect();

    return orderId;
  }

  public int createOneDetails(int orderId, ArrayList<OrderDetail> orderDetails) {
    int rowCount = 0;

    this.connect();

    for (int i = 0; i < orderDetails.size(); i++) {
      try {
        String sql = "INSERT INTO order_details (order_id, item_id, item_quantity , item_size , item_order_price) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setInt(1, orderId);
        stmt.setInt(2, orderDetails.get(i).getId());
        stmt.setInt(3, orderDetails.get(i).getQuantity());
        stmt.setString(4, orderDetails.get(i).getSize());
        stmt.setDouble(5, orderDetails.get(i).getOrderPrice());
        rowCount = stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    }

    this.disconnect();

    return rowCount;
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
