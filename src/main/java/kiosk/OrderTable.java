/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package kiosk;

/**
 *
 * @author Hassen
 */
public class OrderTable extends javax.swing.table.DefaultTableModel {

  OrderTable() {

    super(new Object[][]{}, new String[]{"Item", "Size", "Quantity", "Price"});
    
  }

  public void addRows(java.util.ArrayList<models.OrderDetail> items) {
    setRowCount(0);
    items.forEach(item -> {
      addRow(new Object[]{
        item.getName(), item.getSize(),item.getQuantity(), item.getOrderTotal()
      });
    });
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }
}
