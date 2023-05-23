package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;


/**
 * <p>
 * Actions provided by the Settings menu.
 * </p>
 * 
 * <p>
 * The Settings menu contains actions that update settings within Andie.
 * This includes language selection but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Lachlan Graham
 * @version 1.0
 */
public class SettingsActions extends AbstractAction {

      /**
     * <p>
     * Checks the height of the full screen from the main display.
     * </p>
     * 
     * @return Screen height.
     */
    private int checkFullScreenHeight(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.height;
    }
    
    /** Creation of bundle fro language save */
    public static ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle_en_NZ");
    Preferences prefs = Preferences.userNodeForPackage(cosc202.andie.Andie.class);

    /** A list of actions for the Settings menu. */
    protected ArrayList<Action> actions;

    public ArrayList<Action> getActions(){
        return actions;
    }

    public void activateAction(Action a) {
        a.setEnabled(true);
    }
    /**
     * <p>
     * Create a set of Settings menu actions.
     * </p>
     */
    public SettingsActions() {
        actions = new ArrayList<Action>();
        actions.add(new LanguageChangeEnglishAction("English", null, "Changes display language to English",null));
        actions.add(new LanguageChangeFrenchAction("French", null, "Changes display language to French",null));
        actions.add(new LanguageChangeMaoriAction("Maori", null, "Changes display language to Maori",null));
        actions.add(new LanguageChangeSpanishAction("Spanish", null, "Changes display language to Spanish",null));

    }

    /**
     * <p>
     * Create a menu containing the list of Settings actions.
     * </p>
     * 
     * @return The Settings menu UI element.
     */
    public JMenu createMenu() {
        JMenu SettingsMenu = new JMenu(SettingsActions.bundle.getString("Settings"));
        JMenu LanguageSubMenu = new JMenu(SettingsActions.bundle.getString("LanguageSelect"));

        for(Action action: actions) {
            LanguageSubMenu.add(new JMenuItem(action));
        }


        SettingsMenu.add(LanguageSubMenu);

        return SettingsMenu;
    }

    /**
     * <p>
     * Action to change the display language of Andie to English.
     * </p>
     * 
     * @see cosc202.andie.ImageAction ImageAction
     */
    public class LanguageChangeEnglishAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change English action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LanguageChangeEnglishAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the LanguageChangeEnglishAction is triggered.
         * It changes the display language to English.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            /* Potenial code for saving preferences inbetween instances of Andie
            bundle.clearCache();
            prefs.put("language", "en"); 
            prefs.put("country","NZ");
            */
    
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle_en_NZ");

            try {
                Andie.frame.setJMenuBar(null);
                Andie.frame.setJMenuBar(Andie.createMenuBar());
                if (checkFullScreenHeight(Andie.frame) >= Andie.frame.getSize().height){
                } else {
                Andie.frame.pack();
                }
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        }

    }


        /**
     * <p>
     * Action to change the display language of Andie to Maori.
     * </p>
     * 
     * @see cosc202.andie.ImageAction ImageAction
     */
    public class LanguageChangeMaoriAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change Maori action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LanguageChangeMaoriAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the LanguageChangeMaoriAction is triggered.
         * It changes the display language to Maori.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle_mi_NZ");

            try {
                Andie.frame.setJMenuBar(Andie.createMenuBar());
                if (checkFullScreenHeight(Andie.frame) >= Andie.frame.getSize().height){
                } else {
                Andie.frame.pack();
                }
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }


        /**
     * <p>
     * Action to change the display language of Andie to French.
     * </p>
     * 
     * @see cosc202.andie.ImageAction ImageAction
     */
    public class LanguageChangeFrenchAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change French action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LanguageChangeFrenchAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the LanguageChangeFrenchAction is triggered.
         * It changes the display language to French.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle_fr_FR");

            try {
                Andie.frame.setJMenuBar(Andie.createMenuBar());
                if (checkFullScreenHeight(Andie.frame) >= Andie.frame.getSize().height){
                } else {
                Andie.frame.pack();
                }
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }


        /**
     * <p>
     * Action to change the display language of Andie to Spanish.
     * </p>
     * 
     * @see cosc202.andie.ImageAction ImageAction 
     */
    public class LanguageChangeSpanishAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change Spanish action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LanguageChangeSpanishAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the LanguageChangeSpanishAction is triggered.
         * It changes the display language to Spanish.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(target.getShapeListener() != null) target.removeShapeListener();
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle_sp_SP");

            try {
                Andie.frame.setJMenuBar(Andie.createMenuBar());
                if (checkFullScreenHeight(Andie.frame) >= Andie.frame.getSize().height){
                } else {
                Andie.frame.pack();
                }
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    //method to keep compiler happy
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
