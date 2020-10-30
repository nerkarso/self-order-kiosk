package common;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class SplashFrame extends JFrame {

  final int frameWidth = 560;
  final int frameHeight = 340;

  JProgressBar progressBar;

  public SplashFrame() {
    setTitle(Properties.appTitle);
    setSize(frameWidth, frameHeight);
    setUndecorated(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    addContent();
    setAppIcon();
    refreshFrame();
  }

  final void addContent() {
    JPanel contentPane = (JPanel) getContentPane();
    contentPane.setBackground(Properties.colorPrimary);

    ImageIcon iconImage = new ImageIcon(getClass().getClassLoader().getResource("images/icon-symbolic.png"));
    JLabel icon = new JLabel(iconImage);

    progressBar = new JProgressBar();
    progressBar.setBackground(Properties.colorPrimary);
    progressBar.setForeground(Properties.colorPrimaryAlt);
    progressBar.setBorderPainted(false);
    progressBar.setValue(0);
    contentPane.add(progressBar, BorderLayout.SOUTH);

    contentPane.add(icon, BorderLayout.CENTER);
  }

  final void setAppIcon() {
    ImageIcon appIcon = new ImageIcon(getClass().getClassLoader().getResource("images/icon-1x.png"));
    setIconImage(appIcon.getImage());
  }

  final void startProgress(int duration) {
    int i = 0;
    try {
      while (i <= 100) {
        progressBar.setValue(i);
        Thread.sleep(duration / 10);
        i += 10;
      }
    } catch (InterruptedException e) {
    }
  }

  final void refreshFrame() {
    setSize(frameWidth, frameHeight - 1);
    setSize(frameWidth, frameHeight);
  }

  public static void main(String[] args) {
    int delay = 3000;

    SplashFrame splashFrame = new SplashFrame();
    splashFrame.startProgress(delay);

    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
    }

    splashFrame.setVisible(false);
    splashFrame.dispose();

    LaunchFrame launchFrame = new LaunchFrame();
    launchFrame.setVisible(true);
  }

}
