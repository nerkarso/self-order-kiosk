/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package models;

import java.sql.Date;
import java.util.ArrayList;

public class Order {

  /**
   * Constants
   */
  public static final String EAT_IN = "EAT_IN";
  public static final String TAKE_OUT = "TAKE_OUT";
  public static final String PAY_HERE = "PAY_HERE";
  public static final String PAY_COUNTER = "PAY_COUNTER";

  private int id;
  private int status;
  private String eatingLocation;
  private String paymentMethod;
  private Date date;
  private ArrayList<OrderDetail> details;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getEatingLocation() {
    return this.eatingLocation;
  }

  public void setEatingLocation(String eatingLocation) {
    this.eatingLocation = eatingLocation;
  }

  public String getPaymentMethod() {
    return this.paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public ArrayList<OrderDetail> getDetails() {
    return this.details;
  }

  public void setDetails(ArrayList<OrderDetail> details) {
    this.details = details;
  }

}
