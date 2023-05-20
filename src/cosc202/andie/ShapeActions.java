package cosc202.andie;

import java.util.*;
import java.util.Formatter.BigDecimalLayoutForm;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.lang.Runnable;
import java.math.BigInteger;

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
public class ShapeActions{
 /** A list of actions for the Adjustments menu. */
 protected ArrayList<Action> actions;
    private static double transparencyScale;
    public static double transparencyNum = 1.0;
    public static boolean filled = false;
    public static int lineSize = 10;
 /**
  * <p>
  * Create a set of Adjustment menu actions.
  * </p>
  */
 public ShapeActions() {
     actions = new ArrayList<Action>();
     actions.add(new DrawRectangleAction(SettingsActions.bundle.getString("DrawRectangle"),null,SettingsActions.bundle.getString("DrawRectangleMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new DrawOvalAction(SettingsActions.bundle.getString("DrawOval"),null,SettingsActions.bundle.getString("DrawOvalMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new DrawLineAction(SettingsActions.bundle.getString("DrawLine"),null,SettingsActions.bundle.getString("DrawLineMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new DrawAction(SettingsActions.bundle.getString("Draw"),null,SettingsActions.bundle.getString("DrawMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new ColourPickerAction(SettingsActions.bundle.getString("ColourPicker"), null, SettingsActions.bundle.getString("ChangeColour"), Integer.valueOf(KeyEvent.VK_C)));
}
     /**
     * <p>
     * Create a menu contianing the list of Adjustment actions.
     * </p>
     * 
     * @return The adjustment menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(SettingsActions.bundle.getString("Shapes"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    public class DrawRectangleAction extends ImageAction{
        /**
         * <p>
         * Create a new RotationLeft action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
                /**
         * <p>
         * Callback for when the RotationLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationLeftAction is triggered.
         * It rotates the image 90 degrees to the left.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()){
                target.addShapeListener(new RectangleDrawListener(target));
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
 
    }
    
    public class DrawOvalAction extends ImageAction{
        /**
         * <p>
         * Create a new RotationLeft action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
                /**
         * <p>
         * Callback for when the RotationLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationLeftAction is triggered.
         * It rotates the image 90 degrees to the left.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()){
                target.addShapeListener(new OvalListener(target));
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }

    public class DrawLineAction extends ImageAction{
        /**
         * <p>
         * Create a new RotationLeft action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
                /**
         * <p>
         * Callback for when the RotationLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationLeftAction is triggered.
         * It rotates the image 90 degrees to the left.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()) {
                target.addShapeListener(new LineListener(target));
            } else {
                ErrorHandling.NoFileOpenError();
            }
            
        }
    }
    public class DrawAction extends ImageAction{
        /**
         * <p>
         * Create a new RotationLeft action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
                /**
         * <p>
         * Callback for when the RotationLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationLeftAction is triggered.
         * It rotates the image 90 degrees to the left.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()) {
                target.addShapeListener(new DrawListener(target));
            } else {
                ErrorHandling.NoFileOpenError();
            }
            
        }
    }
        public class ColourPickerAction extends ImageAction {

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
        ColourPickerAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                if(target.getShapeListener() != null) target.removeShapeListener();

                BufferedImage originalTarget = target.getImage().getCurrentImage();
                
                // Pop-up dialog box to ask for the radius value.
                JSlider transparencySlider = new JSlider(1, 10, (int)(transparencyNum*10));
                JLabel transparencyLabel = new JLabel("Transparency : " + transparencyNum + "%");
                
                class TransparencySliderListener implements ChangeListener {
                
                    public void stateChanged(ChangeEvent e) {
                        transparencyScale = transparencySlider.getValue();
                        if(transparencyScale == 0){
                            transparencyScale = 1;
                        }
                        transparencyLabel.setText("Transparency : " + transparencyScale/10 + "%");
                       // target.getImage().setCurrentImage(copyTarget);
                       // target.getImage().tempApplyBrightnessContrast(new ResizeLarger(scale));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }


                JCheckBox checkBox = new JCheckBox("Filled",filled);


                int lineSizes[] = {5,10,15,20};
                JComboBox comboBox = new JComboBox();
                for(int x = 0; x< lineSizes.length;x++){
                    comboBox.addItem(lineSizes[x]);
                }
                comboBox.setSelectedItem(lineSize);
                JLabel lable = new JLabel("Select outline size");

                TransparencySliderListener tsl = new TransparencySliderListener();
                transparencySlider.addChangeListener(tsl);

                JComponent[] labels = new JComponent[]  {transparencySlider, transparencyLabel,checkBox,lable,comboBox};
               
                int optionSize = JOptionPane.showOptionDialog(null, labels, SettingsActions.bundle.getString("ChangeColour"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
                if (optionSize == JOptionPane.CANCEL_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    target.repaint();
                    target.getParent().revalidate();
                    return;
                } else if (optionSize == JOptionPane.OK_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    // error when u dont change transparency then try to open it again
                    transparencyNum = transparencyScale/10.0;
                    if(checkBox.isSelected()){
                        filled = true;
                    }else{
                        filled = false;
                    }
                    lineSize = (int)comboBox.getSelectedItem();
                    
                   // target.getImage().apply(new ResizeLarger(scale));
                   //rgb values are changed
                    target.repaint();
                    target.getParent().revalidate();
                }

                
                
                target.removeShapeListener();;
               // target.getImage().apply(new ColourPicker());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }
}