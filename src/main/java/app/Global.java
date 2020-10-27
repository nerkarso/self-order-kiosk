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

}
