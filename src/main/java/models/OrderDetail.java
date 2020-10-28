/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package models;

public class OrderDetail extends Item {

  /**
   * Constants
   */
  public static final String SMALL = "SMALL";
  public static final String MEDIUM = "MEDIUM";
  public static final String LARGE = "LARGE";

  private int quantity;
  private String size;
  private double subTotal;
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

  public void setSubTotal(double subTotal) {
    this.subTotal = subTotal;
  }

  public double getSubTotal() {
    return this.subTotal;
  }

}
