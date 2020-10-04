/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Order;

public class OrderService extends DatabaseService {

  public ArrayList<Order> getAll() {
    ArrayList<Order> orders = new ArrayList<>();

    return orders;
  }

  public Order getOne(int id) {
    Order order = null;

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
      order.setDate(result.getDate("order_date"));
    } catch (SQLException e) {
      System.out.println(e);
    }

    return order;
  }

}
