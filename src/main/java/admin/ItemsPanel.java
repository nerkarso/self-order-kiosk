/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package admin;

import models.Category;
import models.Item;

public class ItemsPanel extends javax.swing.JPanel {

  services.ItemService itemService;
  services.CategoryService categoryService;

  int currentRowIndex;
  int currentItemId;
  javax.swing.table.DefaultTableModel tbmItems;
  java.util.ArrayList<Category> categoriesRef;

  /**
   * Creates new form ItemsPanel
   */
  public ItemsPanel() {
    /**
     * Initialize
     */
    itemService = new services.ItemService();
    categoryService = new services.CategoryService();
    tbmItems = new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Price", "Category"}
    ) {
      boolean[] canEdit = new boolean[]{false, false, false, false};

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    };

    initComponents();

    /**
     * Populate on load
     */
    getAllItems();
    getAllCategories();
  }

  private void addRow(Item item) {
    tbmItems.addRow(new Object[]{
      item.getId(), item.getName(), item.getPrice(), item.getCategory().getName()
    });
  }

  private void addRows(java.util.ArrayList<Item> items) {
    tbmItems.setRowCount(0);
    items.forEach(item -> addRow(item));
    resizeColumns();
  }

  private void updateRow(Item item) {
    javax.swing.table.TableModel model = (javax.swing.table.TableModel) tblItems.getModel();
    model.setValueAt(item.getId(), currentRowIndex, 0);
    model.setValueAt(item.getName(), currentRowIndex, 1);
    model.setValueAt(item.getPrice(), currentRowIndex, 2);
    model.setValueAt(item.getCategory().getName(), currentRowIndex, 3);
  }

  private void removeRow() {
    tbmItems.removeRow(currentRowIndex);
  }

  private void onRowClicked(java.awt.event.MouseEvent evt) {
    javax.swing.table.TableModel model = (javax.swing.table.TableModel) tblItems.getModel();
    int rowIndex = tblItems.getSelectedRow();
    int itemId = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());

    currentRowIndex = rowIndex;
    currentItemId = itemId;

    getOneItem(itemId);
  }

  private void resizeColumns() {
    javax.swing.table.TableColumnModel model = (javax.swing.table.TableColumnModel) tblItems.getColumnModel();
    model.getColumn(0).setPreferredWidth(50);
    model.getColumn(0).setMaxWidth(50);
    model.getColumn(2).setPreferredWidth(100);
    model.getColumn(2).setMaxWidth(100);
    model.getColumn(3).setPreferredWidth(150);
    model.getColumn(3).setMaxWidth(150);
  }

  private void handleRefresh() {
    getAllItems();
  }

  private void handleSave() {
    String name = txtItemName.getText();
    String price = txtItemPrice.getText();
    String image = txtItemImage.getText();

    if (name.isEmpty()) {
      javax.swing.JOptionPane.showMessageDialog(this, "Name is required", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
    } else if (price.isEmpty()) {
      javax.swing.JOptionPane.showMessageDialog(this, "Price is required", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
    } else if (!validPrice(price)) {
      javax.swing.JOptionPane.showMessageDialog(this, "Price should only contain numbers", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
    } else {
      Category category = categoriesRef.get(cmbItemCategory.getSelectedIndex());
      Item item = new Item();
      item.setId(currentItemId);
      item.setName(name);
      item.setPrice(Double.parseDouble(price));
      item.setImage(image);
      item.setCategory(category);

      if (currentRowIndex >= 0) {
        updateOneItem(item);
      } else {
        createOneItem(item);
        clearFields();
      }
    }
  }

  private void handleNew() {
    clearFields();
  }

  private void handleDelete() {
    if (currentRowIndex >= 0) {
      deleteOneItem(currentItemId);
      clearFields();
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "No item selected to delete", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
    }
  }

  private void populateFields(Item item) {
    txtItemName.setText(item.getName());
    txtItemPrice.setText(Double.toString(item.getPrice()));
    txtItemImage.setText(item.getImage());
    for (int i = 0; i < categoriesRef.size(); i++) {
      if (categoriesRef.get(i).getId() == item.getCategory().getId()) {
        cmbItemCategory.setSelectedIndex(i);
        break;
      }
    }
    lblItemImagePreview.setIcon(app.Global.getImagePreview(item.getImage(), 200, 200, this));
  }

  private void clearFields() {
    currentRowIndex = -1;
    currentItemId = -1;

    tblItems.clearSelection();

    lblItemImagePreview.setIcon(null);

    txtItemName.setText("");
    txtItemPrice.setText("");
    txtItemImage.setText("");
    cmbItemCategory.setSelectedIndex(0);
  }

  private void getAllItems() {
    java.util.ArrayList<Item> items = itemService.getAll();
    if (items.size() > 0) {
      addRows(items);
    }
  }

  private void getOneItem(int id) {
    Item item = itemService.getOneById(id);
    if (item != null) {
      populateFields(item);
    }
  }

  private void createOneItem(Item item) {
    int rowCount = itemService.createOne(item);
    if (rowCount > 0) {
      getAllItems();
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "Failed to create a new item", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
  }

  private void updateOneItem(Item item) {
    int rowCount = itemService.updateOne(item);
    if (rowCount > 0) {
      updateRow(item);
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "Failed to update an item", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
  }

  private void deleteOneItem(int id) {
    int rowCount = itemService.deleteOne(id);
    if (rowCount > 0) {
      removeRow();
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "Failed to delete an item", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
  }

  private void getAllCategories() {
    categoriesRef = categoryService.getAll();
    if (categoriesRef.size() > 0) {
      categoriesRef.forEach(category -> cmbItemCategory.addItem(category.getName()));
    }
  }

  private boolean validPrice(String price) {
    try {
      Double.parseDouble(price);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    scpItems = new javax.swing.JScrollPane();
    tblItems = new javax.swing.JTable();
    pnlItemFields = new javax.swing.JPanel();
    lblItemImagePreview = new javax.swing.JLabel();
    lblItemName = new javax.swing.JLabel();
    lblItemPrice = new javax.swing.JLabel();
    lblItemImage = new javax.swing.JLabel();
    lblItemCategory = new javax.swing.JLabel();
    txtItemName = new javax.swing.JTextField();
    txtItemPrice = new javax.swing.JTextField();
    txtItemImage = new javax.swing.JTextField();
    cmbItemCategory = new javax.swing.JComboBox<>();
    pnlItemActions = new javax.swing.JPanel();
    btnItemRefresh = new javax.swing.JButton();
    btnItemSave = new javax.swing.JButton();
    btnItemNew = new javax.swing.JButton();
    btnItemDelete = new javax.swing.JButton();

    setPreferredSize(new java.awt.Dimension(720, 600));
    setLayout(new java.awt.GridBagLayout());

    tblItems.setModel(tbmItems);
    tblItems.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    tblItems.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        tblItemsMouseClicked(evt);
      }
    });
    scpItems.setViewportView(tblItems);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(scpItems, gridBagConstraints);

    java.awt.GridBagLayout pnlItemFieldsLayout = new java.awt.GridBagLayout();
    pnlItemFieldsLayout.columnWidths = new int[] {200, 100, 300};
    pnlItemFieldsLayout.rowHeights = new int[] {45, 0, 45, 0, 45};
    pnlItemFields.setLayout(pnlItemFieldsLayout);

    lblItemImagePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblItemImagePreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/soup-bowl.png"))); // NOI18N
    lblItemImagePreview.setMaximumSize(new java.awt.Dimension(150, 150));
    lblItemImagePreview.setMinimumSize(new java.awt.Dimension(150, 150));
    lblItemImagePreview.setPreferredSize(new java.awt.Dimension(150, 150));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 7;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
    pnlItemFields.add(lblItemImagePreview, gridBagConstraints);

    lblItemName.setLabelFor(txtItemName);
    lblItemName.setText("Name");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    pnlItemFields.add(lblItemName, gridBagConstraints);

    lblItemPrice.setLabelFor(txtItemPrice);
    lblItemPrice.setText("Price");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    pnlItemFields.add(lblItemPrice, gridBagConstraints);

    lblItemImage.setLabelFor(txtItemImage);
    lblItemImage.setText("Image");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    pnlItemFields.add(lblItemImage, gridBagConstraints);

    lblItemCategory.setLabelFor(cmbItemCategory);
    lblItemCategory.setText("Category");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    pnlItemFields.add(lblItemCategory, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    pnlItemFields.add(txtItemName, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    pnlItemFields.add(txtItemPrice, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    pnlItemFields.add(txtItemImage, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    pnlItemFields.add(cmbItemCategory, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
    add(pnlItemFields, gridBagConstraints);

    pnlItemActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));

    btnItemRefresh.setText("Refresh");
    btnItemRefresh.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnItemRefreshActionPerformed(evt);
      }
    });
    pnlItemActions.add(btnItemRefresh);

    btnItemSave.setText("Save");
    btnItemSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnItemSaveActionPerformed(evt);
      }
    });
    pnlItemActions.add(btnItemSave);

    btnItemNew.setText("New");
    btnItemNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnItemNewActionPerformed(evt);
      }
    });
    pnlItemActions.add(btnItemNew);

    btnItemDelete.setText("Delete");
    btnItemDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnItemDeleteActionPerformed(evt);
      }
    });
    pnlItemActions.add(btnItemDelete);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
    gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
    add(pnlItemActions, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents

  private void tblItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsMouseClicked
    onRowClicked(evt);
  }//GEN-LAST:event_tblItemsMouseClicked

  private void btnItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemRefreshActionPerformed
    handleRefresh();
  }//GEN-LAST:event_btnItemRefreshActionPerformed

  private void btnItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemSaveActionPerformed
    handleSave();
  }//GEN-LAST:event_btnItemSaveActionPerformed

  private void btnItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemNewActionPerformed
    handleNew();
  }//GEN-LAST:event_btnItemNewActionPerformed

  private void btnItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemDeleteActionPerformed
    handleDelete();
  }//GEN-LAST:event_btnItemDeleteActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnItemDelete;
  private javax.swing.JButton btnItemNew;
  private javax.swing.JButton btnItemRefresh;
  private javax.swing.JButton btnItemSave;
  private javax.swing.JComboBox<String> cmbItemCategory;
  private javax.swing.JLabel lblItemCategory;
  private javax.swing.JLabel lblItemImage;
  private javax.swing.JLabel lblItemImagePreview;
  private javax.swing.JLabel lblItemName;
  private javax.swing.JLabel lblItemPrice;
  private javax.swing.JPanel pnlItemActions;
  private javax.swing.JPanel pnlItemFields;
  private javax.swing.JScrollPane scpItems;
  private javax.swing.JTable tblItems;
  private javax.swing.JTextField txtItemImage;
  private javax.swing.JTextField txtItemName;
  private javax.swing.JTextField txtItemPrice;
  // End of variables declaration//GEN-END:variables
}
