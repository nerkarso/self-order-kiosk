/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package kiosk;

import java.util.ArrayList;
import models.OrderDetail;

public class StateManager {

  private static ArrayList<OrderDetail> orderedItems;
  private static String eatingLocation;
  private static String paymentMethod;

  public static ArrayList<OrderDetail> getOrderedItems() {
    return orderedItems;
  }

  public static OrderDetail getOrderedItem(int index) {
    if (orderedItems != null && orderedItems.size() > 0) {
      return orderedItems.get(index);
    } else {
      return null;
    }
  }

  public static void setOrderedItem(OrderDetail orderDetail) {
    if (orderedItems == null) {
      orderedItems = new ArrayList<OrderDetail>();
    }
    orderedItems.add(orderDetail);
  }

  public static void setOrderedItem(OrderDetail orderDetail, int index) {
    orderedItems.set(index, orderDetail);
  }

  public static void removeOrderedItem(int index) {
    orderedItems.remove(index);
  }

  public static String getEatingLocation() {
    return eatingLocation;
  }

  public static void setEatingLocation(String location) {
    eatingLocation = location;
  }

  public static String getPaymentMethod() {
    return paymentMethod;
  }

  public static void setPaymentMethod(String method) {
    paymentMethod = method;
  }

  public static void reset() {
    orderedItems = null;
    eatingLocation = null;
    paymentMethod = null;
  }

}
