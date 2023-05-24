package cosc202.andie;

import java.util.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import javax.swing.*;


/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new SoftBlurAction(SettingsActions.bundle.getString("SoftBlur"), null, SettingsActions.bundle.getString("ApplySoftBlur"),Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new SharpenAction(SettingsActions.bundle.getString("Sharpen"), null, SettingsActions.bundle.getString("ApplySharpen"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new MeanFilterAction(SettingsActions.bundle.getString("MeanFilter"), null, SettingsActions.bundle.getString("ApplyMeanFilter"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new MedianFilterAction(SettingsActions.bundle.getString("MedianFilter"), null, SettingsActions.bundle.getString("ApplyMedianFilter"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new GaussianFilterAction(SettingsActions.bundle.getString("GaussianBlur"), null, SettingsActions.bundle.getString("ApplyGaussianBlur"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BlotchBlurAction(SettingsActions.bundle.getString("BlotchBlur"), null, SettingsActions.bundle.getString("ApplyBlotchBlur"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new EmbossAction(SettingsActions.bundle.getString("EmbossFilter"), null, SettingsActions.bundle.getString("ApplyEmbossFilter"),Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new ColorAction(SettingsActions.bundle.getString("EmbossFilter"), null, SettingsActions.bundle.getString("ApplyEmbossFilter"),Integer.valueOf(KeyEvent.VK_B)));
    }   

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(SettingsActions.bundle.getString("Filter"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
                if(EditableImage.hasImage()){
                RectangleListener.setSelect(false);
                if(target.getShapeListener() != null) target.removeShapeListener();
                // Determine the radius - ask the user.
                int radius = 1;

                // Pop-up dialog box to ask for the radius value.
                SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
                JSpinner radiusSpinner = new JSpinner(radiusModel);
                int option = JOptionPane.showOptionDialog(null, radiusSpinner, SettingsActions.bundle.getString("EnterFilterRadius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                // Check the return value from the dialog box.
                if (option == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option == JOptionPane.OK_OPTION) {
                    radius = radiusModel.getNumber().intValue();
                }

                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new MeanFilter(radius,true));
                }
                else {target.getImage().apply(new MeanFilter(radius));}
                
                // Create and apply the filter
                target.repaint();
                target.getParent().revalidate(); 
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
                if(EditableImage.hasImage()){
                RectangleListener.setSelect(false);
                if(target.getShapeListener() != null) target.removeShapeListener();
                // Determine the radius - ask the user.
                int radius = 1;

                // Pop-up dialog box to ask for the radius value.
                SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
                JSpinner radiusSpinner = new JSpinner(radiusModel);
                int option = JOptionPane.showOptionDialog(null, radiusSpinner, SettingsActions.bundle.getString("EnterFilterRadius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                // Check the return value from the dialog box.
                if (option == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option == JOptionPane.OK_OPTION) {
                    radius = radiusModel.getNumber().intValue();
                }
                
                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new MedianFilter(radius, true));
                }
                else {target.getImage().apply(new MedianFilter(radius));}

                // Create and apply the filter
                target.repaint();
                target.getParent().revalidate(); 
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }

    

    public class SoftBlurAction extends ImageAction {
        SoftBlurAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
                if(EditableImage.hasImage()){
                if(target.getShapeListener() != null) target.removeShapeListener();
                RectangleListener.setSelect(false);
            // Create and apply the filter
                
                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new SoftBlur(true));
                }
                else {target.getImage().apply(new SoftBlur());}

                
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }   
    }

    public class SharpenAction extends ImageAction {
        SharpenAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                // Create and apply the filter
                if(target.getShapeListener() != null) target.removeShapeListener();
                RectangleListener.setSelect(false);
                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new Sharpen(true));
                }
                else {target.getImage().apply(new Sharpen());}
                
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }
        

    public class BlotchBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BlotchBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
             super(name, icon, desc, mnemonic);
         }
 
        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
         public void actionPerformed(ActionEvent e) {
                if(EditableImage.hasImage()){
                if(target.getShapeListener() != null) target.removeShapeListener();
                RectangleListener.setSelect(false);
                 // Determine the radius - ask the user.
                 int radius = 1;
 
                 // Pop-up dialog box to ask for the radius value.
                 SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
                 JSpinner radiusSpinner = new JSpinner(radiusModel);
                 int option = JOptionPane.showOptionDialog(null, radiusSpinner, SettingsActions.bundle.getString("EnterFilterStrength"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                 
                 // Check the return value from the dialog box.
                 if (option == JOptionPane.CANCEL_OPTION) {
                     return;
                 } else if (option == JOptionPane.OK_OPTION) {
                     radius = radiusModel.getNumber().intValue();
                 }

                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new BlotchBlur(radius,true));
                } 
                else {target.getImage().apply(new BlotchBlur(radius));}
                 
                 // Create and apply the filter
                 target.repaint();
                 target.getParent().revalidate();
                 
             } else {
                 ErrorHandling.NoFileOpenError();
             }
         }
 
    }

    public class EmbossAction extends ImageAction {
        EmbossAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            String option;
            if(EditableImage.hasImage()){
                if(target.getShapeListener() != null) target.removeShapeListener();
                RectangleListener.setSelect(false);
                String[] embossOptions = {"Center Left", "Top Left", "Center Right", "Top Center", "Top Right", "Bottom Left", "Bottom Center", "Bottom Right", "Sobel Vertical", "Sobel Horiontal"};
                if (GraphicsEnvironment.isHeadless()) {
                    option = "Center Left";
                    System.out.println("headless");
                } else {
                    option = (String) JOptionPane.showInputDialog(null, "What emboss filter are you using?", "Choose filter option", JOptionPane.QUESTION_MESSAGE,
                null, embossOptions, embossOptions[0]);
                }



                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new EmbossFilter(option, true));
                } 
                else {target.getImage().apply(new EmbossFilter(option));}

                // Create and apply the filter
                
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }

    public class ColorAction extends ImageAction {
        ColorAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            String option;
            if(EditableImage.hasImage()){
                if(target.getShapeListener() != null) target.removeShapeListener();
                RectangleListener.setSelect(false);
                String[] embossOptions = {"red", "blue","green","yellow","pink", "light blue", "patches radius 1", "patches radius 5", "patches radius 1000", "patches radius 100000"};
                if (GraphicsEnvironment.isHeadless()) {
                    option = "red";
                    System.out.println("headless");
                } else {
                    option = (String) JOptionPane.showInputDialog(null, "What color filter are you using?", "Choose filter option", JOptionPane.QUESTION_MESSAGE,
                null, embossOptions, embossOptions[0]);
                }

                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new ColorFilters(option, true));
                } 
                else {target.getImage().apply(new ColorFilters(option));}

                // Create and apply the filter
                
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }

    public class GaussianFilterAction extends ImageAction {

       /**
        * <p>
        * Create a new mean-filter action.
        * </p>
        * 
        * @param name The name of the action (ignored if null).
        * @param icon An icon to use to represent the action (ignored if null).
        * @param desc A brief description of the action  (ignored if null).
        * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
        */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

       /**
        * <p>
        * Callback for when the convert-to-grey action is triggered.
        * </p>
        * 
        * <p>
        * This method is called whenever the MeanFilterAction is triggered.
        * It prompts the user for a filter radius, then applys an appropriately sized {@link MeanFilter}.
        * </p>
        * 
        * @param e The event triggering this callback.
        */
        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                if(target.getShapeListener() != null) target.removeShapeListener();
                RectangleListener.setSelect(false);
                // Determine the radius - ask the user.
                int radius = 1;

                // Pop-up dialog box to ask for the radius value.
                SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
                JSpinner radiusSpinner = new JSpinner(radiusModel);
                int option = JOptionPane.showOptionDialog(null, radiusSpinner, SettingsActions.bundle.getString("EnterFilterStrength"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                // Check the return value from the dialog box.
                if (option == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option == JOptionPane.OK_OPTION) {
                    radius = radiusModel.getNumber().intValue();
                }

                int option2 = JOptionPane.showConfirmDialog(null,"Would you like to rebase negative image values?", "Please select one. (Select no for default)" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option2 == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option2 == JOptionPane.YES_OPTION) {
                    target.getImage().apply(new GaussianBlur(radius, true));
                }
                else {target.getImage().apply(new GaussianBlur(radius));}

                // Create and apply the filter
                target.getImage().apply(new GaussianBlur(radius));
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

   }
}
