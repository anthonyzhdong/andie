package cosc202.andie;



import javax.swing.*;
import javax.swing.JProgressBar;

public class ProgressBar {

    static JProgressBar pbar;
    JFrame frame;
  
    static final int MY_MINIMUM = 0;
  
    static final int MY_MAXIMUM = 100;
  
    public ProgressBar() {
        pbar = new JProgressBar();
        pbar.setMinimum(MY_MINIMUM);
        pbar.setMaximum(MY_MAXIMUM);
  
        this.frame = new JFrame("Progress Bar");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(pbar);
        pbar.setStringPainted(true);
        this.frame.pack();
        this.frame.setVisible(true);
        pbar.setVisible(true);
        pbar.setStringPainted(true);
    }
  
    public void updateBar(int newValue) {
        pbar.setValue(pbar.getValue() + newValue);
    }

    public void closeBar() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}