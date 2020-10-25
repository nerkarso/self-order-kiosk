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

  public static javax.swing.ImageIcon getImagePreview(String url) {
    javax.swing.ImageIcon image = new javax.swing.ImageIcon();
    if (url != null && !url.isEmpty()) {
      try {
        java.net.URL imageUrl = new java.net.URL(url + "?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A");
        java.awt.Image resizedImage = javax.imageio.ImageIO.read(imageUrl);
        resizedImage = resizedImage.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        image = new javax.swing.ImageIcon(resizedImage);
      } catch (java.io.IOException e) {
        System.out.println(e);
      }
    }
    return image;
  }

  public static void setTotalPrice(java.util.ArrayList<models.OrderDetail> items, javax.swing.JLabel lblTotalValue) {
    double total = 0;
    for (int i = 0; i < items.size(); i++) {
      total += items.get(i).getPrice();
    }
    lblTotalValue.setText(Double.toString(total));
  }

}
