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


import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 * <p>
 * Actions provided by the Adjustments menu.
 * </p>
 * 
 * <p>
 * The Shapes menu contains actions which allow the user to customise a shape (color, line thickness and hollow or filled)
 * and then draw either a rectangle, oval, line, or use a draw tool to follow the cursor.
 * 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Anthony Dong
 * @version 1.0
 */
public class ShapeActions{
 /** A list of actions for the Adjustments menu. */
 protected ArrayList<Action> actions;
    //private static double transparencyScale;
    //private static boolean transparencySelected = false;

    private static boolean shapeFill = false;
    private static boolean shapeOutline = true;
   // public static double transparencyNum = 1.0;
    private static float lineSize = 8;
    private static Color shapeFillColour = Color.white;
    private static Color shapeOutlineColour = Color.black;
    private static boolean outlineEyeDropper = false;
    private static boolean fillEyeDropper = false;
    public static Color eyeDropperColour = Color.blue;

 /**
  * <p>
  * Create a set of Adjustment menu actions.
  * </p>
  */
 public ShapeActions() {
     actions = new ArrayList<Action>();
     actions.add(new ShapeOutlineAction(SettingsActions.bundle.getString("ShapeOutlineSettings"), null, SettingsActions.bundle.getString("ShapeOutlineSettingsMessage"), Integer.valueOf(KeyEvent.VK_C)));
     actions.add(new ShapeFillAction(SettingsActions.bundle.getString("ShapeFillSettings"), null, SettingsActions.bundle.getString("ShapeFillSettingsMessage"), Integer.valueOf(KeyEvent.VK_C)));
     actions.add(new DrawRectangleAction(SettingsActions.bundle.getString("DrawRectangle"),null,SettingsActions.bundle.getString("DrawRectangleMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new DrawOvalAction(SettingsActions.bundle.getString("DrawOval"),null,SettingsActions.bundle.getString("DrawOvalMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new DrawLineAction(SettingsActions.bundle.getString("DrawLine"),null,SettingsActions.bundle.getString("DrawLineMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new DrawAction(SettingsActions.bundle.getString("Draw"),null,SettingsActions.bundle.getString("DrawMessage"), Integer.valueOf(KeyEvent.VK_R)));
     actions.add(new EyeDropperAction(SettingsActions.bundle.getString("EyeDropper"),null,SettingsActions.bundle.getString("EyeDropperMessage"), Integer.valueOf(KeyEvent.VK_R)));

}
     /**
     * <p>
     * Create a menu contianing the list of Shape actions.
     * </p>
     * 
     * @return The shape menu UI element.
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
         * Create a new DrawRectangle action.
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
         * Callback for when the DrawRectangle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawRectangle is triggered.
         * It adds a listener to the image panel which detects the next user click and drag
         * and draws the according rectangle.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()){
                target.addShapeListener(new RectangleDrawListener(target,shapeOutlineColour,shapeFillColour, lineSize, shapeFill,shapeOutline,outlineEyeDropper,fillEyeDropper));
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
 
    }
    
    public class DrawOvalAction extends ImageAction{
        /**
         * <p>
         * Create a new DrawOval action.
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
         * This method is called whenever the DrawOval is triggered.
         * It adds a listener to the image panel which detects the next user click and drag
         * and draws the according oval.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()){
                target.addShapeListener(new OvalListener(target,shapeOutlineColour,shapeFillColour, lineSize, shapeFill,shapeOutline,outlineEyeDropper,fillEyeDropper));
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }

    public static class DrawLineAction extends ImageAction{
        /**
         * <p>
         * Create a new DrawLine action.
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
         * Callback for when the DrawLine action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawLine is triggered.
         * It adds a listener to the image panel which detects the next user click and drag
         * and draws the according line.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()) {
                target.addShapeListener(new LineListener(target, shapeOutlineColour, lineSize, outlineEyeDropper));
            } else {
                ErrorHandling.NoFileOpenError();
            }
            
        }
    }
    public static class DrawAction extends ImageAction{
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
         * Callback for when the Draw action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Draw is triggered.
         * It adds a listener to the image panel which detects the next user click and drag
         * and draws circles wherever the user drags until mouse release.
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            if(EditableImage.hasImage()) {
                target.addShapeListener(new DrawListener(target, shapeOutlineColour, (int)lineSize));
            } else {
                ErrorHandling.NoFileOpenError();
            }
            
        }
    }
        public class ShapeFillAction extends ImageAction {

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
        ShapeFillAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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

                JLabel checkBoxLabel = new JLabel(SettingsActions.bundle.getString("ShapeFill"));
                JCheckBox checkBox = new JCheckBox("",shapeFill);
                
                JLabel interiorColourLabel = new JLabel(SettingsActions.bundle.getString("SelectInteriorColour"));
                JColorChooser colourChooser = new JColorChooser();
                
                /** 
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
                        transparencySelected = true;
                    }
                }

                TransparencySliderListener tsl = new TransparencySliderListener();
                transparencySlider.addChangeListener(tsl);
*/
                /** 
                AbstractColorChooserPanel[] hsvPanel = colourChooser.getChooserPanels();
                for(AbstractColorChooserPanel panel : hsvPanel){
                    String displayName = panel.getDisplayName();
                    if(!displayName.equals("RGB") && !displayName.equals("Swatches")){
                       // colourChooser.removeChooserPanel(panel);
                    }
                }
                */
                JComponent[] labels = new JComponent[]  {checkBoxLabel, checkBox, interiorColourLabel, colourChooser}; //, transparencyLabel, transparencySlider};
               
                int optionSize = JOptionPane.showOptionDialog(null, labels, SettingsActions.bundle.getString("ShapeFillSettings"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
                if (optionSize == JOptionPane.CANCEL_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    target.repaint();// do i need this?
                    target.getParent().revalidate();
                    return;
                } else if (optionSize == JOptionPane.OK_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);

                    if(checkBox.isSelected()){
                        shapeFill = true;
                    }else{
                        shapeFill = false;
                    }
                    
                    shapeFillColour = colourChooser.getColor();
 /** 
                    if(transparencySelected){
                    transparencyNum = transparencyScale/10;
                    }
                    */
                }
                    target.removeShapeListener();;
                    target.repaint();
                    target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }
        public class ShapeOutlineAction extends ImageAction{
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
            ShapeOutlineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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

                    BufferedImage originalTarget = target.getImage().getCurrentImage();
                    //all jtext make translations and call them from the package
                    //SettingsActions.bundle.getString(key);

                    JLabel checkBoxLabel = new JLabel(SettingsActions.bundle.getString("ShapeOutline"));
                    JCheckBox checkBox = new JCheckBox("",shapeOutline);

                    JLabel outlineLabel = new JLabel(SettingsActions.bundle.getString("SelectOutlineSize"));

                    Integer lineSizes[] = {2,4,6,8,12,16,20,25,30,50,100};
                    JComboBox<Integer> comboBox = new JComboBox<>(lineSizes);
                    comboBox.setSelectedItem((int)lineSize);

                    JLabel eyeDropperLabel = new JLabel(SettingsActions.bundle.getString("UseEyeDropperColour"));
                    JCheckBox eyeDropperCheckBox = new JCheckBox("",outlineEyeDropper);
                    
                    JLabel outlineColourLabel = new JLabel(SettingsActions.bundle.getString("SelectOutlineColour"));
                    JColorChooser outlineColourChooser = new JColorChooser();

                    JComponent[] labels = new JComponent[]  {checkBoxLabel, checkBox, outlineLabel, comboBox, eyeDropperLabel, eyeDropperCheckBox, outlineColourLabel, outlineColourChooser};
                
                    int optionSize = JOptionPane.showOptionDialog(null, labels, SettingsActions.bundle.getString("ShapeOutlineSettings"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                
                    if (optionSize == JOptionPane.CANCEL_OPTION) {
                        target.getImage().setCurrentImage(originalTarget);
                        target.repaint();
                        target.getParent().revalidate();
                        return;
                    } else if (optionSize == JOptionPane.OK_OPTION) {
                        target.getImage().setCurrentImage(originalTarget);
    
                        lineSize = (int)comboBox.getSelectedItem();

                        shapeOutlineColour = outlineColourChooser.getColor();

                        if(checkBox.isSelected()){
                            shapeOutline = true;
                        }else{
                            shapeOutline = false;
                        }

                        if(eyeDropperCheckBox.isSelected()){
                            outlineEyeDropper = true;
                        }else{
                            outlineEyeDropper = false;
                        }

                        target.repaint();
                        target.getParent().revalidate();
                    }
                } else {
                    ErrorHandling.NoFileOpenError();
                }
                
            }
        }
        public class EyeDropperAction extends ImageAction{
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
            EyeDropperAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
                    //EyeDropperListener a = new EyeDropperListener(target);
                    //target.addShapeListener(a);
                    target.addShapeListener(new EyeDropperListener(target));
                    //System.out.println(eyeDropper);
                } else {
                    ErrorHandling.NoFileOpenError();
                }
                
            }
        }
}