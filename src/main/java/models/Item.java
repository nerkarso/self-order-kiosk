/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package models;

public class Item {

  private int id;
  private String name;
  private double price;
  private String image;
  private Category category;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}
