package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * <p>
 * Actions provided by the Adjustments menu.
 * </p>
 * 
 * <p>
 * The Adjustments menu contains actions which change the dimensions of the image.
 * This includes image resize, image rotation and also the ability to flip an image.
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
 private int scale;
 /**
  * <p>
  * Create a set of Adjustment menu actions.
  * </p>
  */
 public AdjustmentActions() {
     actions = new ArrayList<Action>();
     actions.add(new ResizeLargerAction(SettingsActions.bundle.getString("IncreaseSize"), null, SettingsActions.bundle.getString("IncreaseSizeMessage"), Integer.valueOf(KeyEvent.VK_S)));
     actions.add(new RotationRightAction(SettingsActions.bundle.getString("RotateRight"), null, SettingsActions.bundle.getString("RotateRightMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new RotationLeftAction(SettingsActions.bundle.getString("RotateLeft"), null, SettingsActions.bundle.getString("RotateLeftMessage"), Integer.valueOf(KeyEvent.VK_L)));
     actions.add(new FlipHorizontalAction(SettingsActions.bundle.getString("FlipHorizontally"), null, SettingsActions.bundle.getString("FlipHorizontalMessage"), Integer.valueOf(KeyEvent.VK_H)));
     actions.add(new FlipVerticalAction(SettingsActions.bundle.getString("FlipVertically"), null, SettingsActions.bundle.getString("FlipVerticalMessage"), Integer.valueOf(KeyEvent.VK_V)));
     

//rotate 90 to left
//rotate 180
 }
     /**
     * <p>
     * Create a menu contianing the list of Adjustment actions.
     * </p>
     * 
     * @return The adjustment menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(SettingsActions.bundle.getString("Adjustments"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

     /**
     * <p>
     * Action to resize image.
     * </p>
     * 
     * @see ResizeLarger
     */
    public class ResizeLargerAction extends ImageAction{

           /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ResizeLargerAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
         /**
         * <p>
         * Callback for when the resize larger action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the resize larger is triggered.
         * It changes the image size.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                BufferedImage originalTarget = target.getImage().getCurrentImage();
                BufferedImage copyTarget = target.getImage().getCurrentImage();
                
                // Pop-up dialog box to ask for the radius value.
                JSlider sizeSlider = new JSlider(0, 200, 100);
                JLabel sizeLabel = new JLabel("Size : " + 100 + "%");

                class SizeSliderListener implements ChangeListener {
                
                    public void stateChanged(ChangeEvent e) {
                        scale = sizeSlider.getValue();
                        if(scale == 0){
                            scale = 1;
                        }
                        sizeLabel.setText("Size : " + scale + "%");
                        target.getImage().setCurrentImage(copyTarget);
                        target.getImage().tempApplyBrightnessContrast(new ResizeLarger(scale));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }

                SizeSliderListener ssl = new SizeSliderListener();
                sizeSlider.addChangeListener(ssl);

                JComponent[] labels = new JComponent[]  {sizeSlider, sizeLabel};
                int optionSize= JOptionPane.showOptionDialog(null, labels, SettingsActions.bundle.getString("EnterSize"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
                if (optionSize == JOptionPane.CANCEL_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    scale = 0;
                
                    target.repaint();
                    target.getParent().revalidate();
                    return;
                } else if (optionSize == JOptionPane.OK_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    target.getImage().apply(new ResizeLarger(scale));
                    target.repaint();
                    target.getParent().revalidate();
                }
    
            } else {
                ErrorHandling.NoFileOpenError();
            }
           
        }
    }

    public class RotationRightAction extends ImageAction{
        /**
         * <p>
         * Create a new brightness action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotationRightAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
                /**
         * <p>
         * Callback for when the Rotation action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationRightAction is triggered.
         * It rotates the image.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                target.getImage().apply(new RotationRight());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
        }
            
        }
    }
    public class RotationLeftAction extends ImageAction{
        /**
         * <p>
         * Create a new brightness action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotationLeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
                /**
         * <p>
         * Callback for when the Rotation action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationLeftAction is triggered.
         * It rotates the image.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                target.getImage().apply(new RotationLeft());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
        }
            
        }
    }
    public class FlipHorizontalAction
     extends ImageAction{
         /**
         * <p>
         * Create a new brightness action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

          /**
         * <p>
         * Callback for when the Flip action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipHorizontalAction
         *  is triggered.
         * It flips the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */


        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                target.getImage().apply(new FlipHorizontal());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }

    public class FlipVerticalAction extends ImageAction{
        /**
        * <p>
        * Create a new brightness action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
       FlipVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
           super(name, icon, desc, mnemonic);
       }

         /**
        * <p>
        * Callback for when the Flip action is triggered.
        * </p>
        * 
        * <p>
        * This method is called whenever the FlipHorizontalAction
        *  is triggered.
        * It flips the image.
        * </p>
        * 
        * @param e The event triggering this callback.
        */


       public void actionPerformed(ActionEvent e) {
           if(EditableImage.hasImage()){
               target.getImage().apply(new FlipVertical());
               target.repaint();
               target.getParent().revalidate();
           } else {
               ErrorHandling.NoFileOpenError();
           }
       }
   }
}