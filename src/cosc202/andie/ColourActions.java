package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {
    
    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;
    private static int brightnessDegree;
    private static int contrastDegree;
    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(SettingsActions.bundle.getString("Greyscale"), null, SettingsActions.bundle.getString("GreyscaleConvert"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessContrastAction(SettingsActions.bundle.getString("BrightnessContrast"), null, SettingsActions.bundle.getString("AlterBrightness"), Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(SettingsActions.bundle.getString("Colour"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

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
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
                target.removeShapeListener();;
                target.getImage().apply(new ConvertToGrey());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }

    

    public static class BrightnessContrastAction extends ImageAction {
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
        BrightnessContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the BrightnessContrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessContrastAction is triggered.
         * It generates a pop-up that allows a user to use 2 sliders to input a brightness and 
         * contrast respectively, with labels showing the percentage change. Generates a copy of 
         * the image that is used in other methods to act as a preview for the user.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                target.removeShapeListener();;
                BufferedImage originalTarget = target.getImage().getCurrentImage();
                BufferedImage copyTarget = target.getImage().getCurrentImage();
                
                // Pop-up dialog box to ask for the radius value.
                JSlider brightnessSlider = new JSlider((-100), 100, brightnessDegree);
                JLabel brightnessLabel = new JLabel("Brightness : " + brightnessDegree + "%");

                JSlider contrastSlider = new JSlider((-100), 100, contrastDegree);
                JLabel contrastLabel = new JLabel("Contrast : " + contrastDegree + "%");



                class BrightnessSliderListener implements ChangeListener {
                    
                    /**
                     * <p>
                     * Callback for when the BrightnessSliderListener is triggered.
                     * </p>
                     * 
                     * <p>
                     * This method is called whenever the BrightnessSliderListener is triggered.
                     * Updates brightnessLabel and applies the current value of the brightness and contrast
                     * sliders to the copy of the image, as a sort of preview.
                     * </p>
                     * 
                     * @param e The event triggering this callback.
                     */

                    public void stateChanged(ChangeEvent e) {
                        brightnessDegree = brightnessSlider.getValue();
                        brightnessLabel.setText("Brightness : " + brightnessDegree + "%");
                        target.getImage().setCurrentImage(copyTarget);
                        target.getImage().tempApplyBrightnessContrast(new BrightnessContrast(brightnessDegree, contrastDegree));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }

                class ContrastSliderListener implements ChangeListener {

                    /**
                     * <p>
                     * Callback for when the BrightnessSliderListener is triggered.
                     * </p>
                     * 
                     * <p>
                     * This method is called whenever the ContrastSliderListener is triggered.
                     * Updates contrastLabel and applies the current value of the brightness and contrast
                     * sliders to the copy of the image, as a sort of preview.
                     * </p>
                     * 
                     * @param e The event triggering this callback.
                     */

                
                    public void stateChanged(ChangeEvent e) {
                        contrastDegree = contrastSlider.getValue();
                        contrastLabel.setText("Contrast : " + contrastDegree + "%");
                        target.getImage().setCurrentImage(copyTarget);
                        target.getImage().tempApplyBrightnessContrast(new BrightnessContrast(brightnessDegree, contrastDegree));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }

                BrightnessSliderListener bcl = new BrightnessSliderListener();
                brightnessSlider.addChangeListener(bcl);

                ContrastSliderListener ccl = new ContrastSliderListener();
                contrastSlider.addChangeListener(ccl);
                

                JComponent[] labels = new JComponent[]  {brightnessSlider, brightnessLabel, contrastSlider, contrastLabel};
                int optionBrightness = JOptionPane.showOptionDialog(null, labels, SettingsActions.bundle.getString("EnterBrightnessAndContrast"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
                // Check the return value from the dialog box.
                /* if cancel is chosen, revert image to the original image, and change
                 * values back to 0 for brightness and contrast
                 * 
                 * if ok is chosen apply the current brightness and contrast values in the slider 
                 * to the original image.
                 */
                if (optionBrightness == JOptionPane.CANCEL_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    brightnessDegree = 0;
                    contrastDegree = 0;
                    target.repaint();
                    target.getParent().revalidate();
                    return;
                } else if (optionBrightness == JOptionPane.OK_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    target.getImage().apply(new BrightnessContrast(brightnessDegree, contrastDegree));
                    target.repaint();
                    target.getParent().revalidate();
                }
            } else {
                ErrorHandling.NoFileOpenError();
            }
            
        }
    }

}
