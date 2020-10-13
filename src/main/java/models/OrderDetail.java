/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package models;

public class OrderDetail extends Item {

  private int quantity;
  private String size;
  private double orderPrice;
  private int orderId;

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getSize() {
    return this.size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public double getOrderPrice() {
    return this.orderPrice;
  }

  public void setOrderPrice(double orderPrice) {
    this.orderPrice = orderPrice;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

}
