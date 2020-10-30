/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package app;

public class Global {

  public static void setAppIcon(javax.swing.JFrame frame) {
    javax.swing.ImageIcon appIcon = new javax.swing.ImageIcon(frame.getClass().getResource("/app/app-logo-1x.png"));
    frame.setIconImage(appIcon.getImage());
  }

  public static void setDefaultTheme() {
    com.formdev.flatlaf.FlatIntelliJLaf.install();
  }

  public static String toTitleCase(String input) {
    String sanitizedInput = input.replaceAll("_", " ");
    StringBuilder titleCase = new StringBuilder(sanitizedInput.length());
    boolean nextTitleCase = true;
    for (char c : sanitizedInput.toLowerCase().toCharArray()) {
      if (!Character.isLetterOrDigit(c)) {
        nextTitleCase = true;
      } else if (nextTitleCase) {
        c = Character.toTitleCase(c);
        nextTitleCase = false;
      }
      titleCase.append(c);
    }
    return titleCase.toString();
  }

  public static javax.swing.ImageIcon getImagePreview(String url, int w, int h, Object context) {
    javax.swing.ImageIcon image = new javax.swing.ImageIcon(context.getClass().getResource("/icons/soup-bowl.png"));
    if (url != null && !url.isEmpty()) {
      try {
        java.net.URL imageUrl = new java.net.URL(url + "?fit=around%7C" + w + "%3A" + h + "&crop=" + w + "%3A" + h + "%3B%2A%2C%2A");
        java.awt.Image resizedImage = javax.imageio.ImageIO.read(imageUrl);
        resizedImage = resizedImage.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        image = new javax.swing.ImageIcon(resizedImage);
      } catch (java.io.IOException e) {
        System.out.println(e);
      }
    }
    return image;
  }

  public static String toCurrency(Double input) {
    java.text.NumberFormat formatter = new java.text.DecimalFormat("$ ###,###,##0.00");
    return formatter.format(input);
  }

  public static void setTotalPrice(java.util.ArrayList<models.OrderDetail> items, javax.swing.JLabel lblTotalValue) {
    double total = 0;
    for (int i = 0; i < items.size(); i++) {
      total += items.get(i).getSubTotal();
    }
    lblTotalValue.setText(toCurrency(total));
  }

}
