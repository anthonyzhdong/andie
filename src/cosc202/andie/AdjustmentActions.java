package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Adjustments menu.
 * </p>
 * 
 * <p>
 * 
 * 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class AdjustmentActions{
 /** A list of actions for the Adjustments menu. */
 protected ArrayList<Action> actions;

 /**
  * <p>
  * Create a set of Colour menu actions.
  * </p>
  */
 public AdjustmentActions() {
     actions = new ArrayList<Action>();
     actions.add(new ResizeLarger("Increase Size", null, "Increases Image Size", Integer.valueOf(KeyEvent.VK_L)));
     actions.add(new RotationAction("Rotate", null, "Rotates image 90 degrees", Integer.valueOf(KeyEvent.VK_K)));
 }
    /**
     * <p>
     * Create a menu contianing the list of Adjustment actions.
     * </p>
     * 
     * @return The adjustment menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Adjustments");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

     /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class ResizeLarger extends ImageAction{
        ResizeLarger(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            //target.getImage().apply(ResizeLarger());
            target.repaint();
            target.getParent().revalidate();
            
        }
    }

    public class RotationAction extends ImageAction{
        RotationAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotation());
            target.repaint();
            target.getParent().revalidate();
            
        }
    }
}