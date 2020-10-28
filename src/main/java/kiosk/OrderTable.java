/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package kiosk;

public class OrderTable extends javax.swing.table.DefaultTableModel {
  
  OrderTable() {
    super(new Object[][]{}, new String[]{"Item", "Size", "Qty", "Price"});
  }
  
  public void addRows(java.util.ArrayList<models.OrderDetail> items) {
    setRowCount(0);
    items.forEach(item -> {
      addRow(new Object[]{
        item.getName(), app.Global.toTitleCase(item.getSize()), item.getQuantity(), app.Global.toCurrency(item.getSubTotal())
      });
    });
  }
  
  public void resizeColumns(javax.swing.table.TableColumnModel columnModel) {
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(1).setMaxWidth(100);
    columnModel.getColumn(2).setPreferredWidth(50);
    columnModel.getColumn(2).setMaxWidth(50);
    columnModel.getColumn(3).setPreferredWidth(100);
    columnModel.getColumn(3).setMaxWidth(100);
  }
  
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }
  
}
