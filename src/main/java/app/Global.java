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

}
