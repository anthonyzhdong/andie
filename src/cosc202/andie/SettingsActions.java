package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
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
 * @author Steven Mills
 * @version 1.0
 */
public class SettingsActions {
    
    public static ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
    /** A list of actions for the Settings menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Settings menu actions.
     * </p>
     */
    public SettingsActions() {
        actions = new ArrayList<Action>();
        actions.add(new LanguageChangeEnglishAction("English", null, "Changes the display language",Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new LanguageChangeFrenchAction("French", null, "Changes the display language",Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new LanguageChangeSpanishAction("Spanish", null, "Changes the display language",Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new LanguageChangeMaoriAction("Maori", null, "Changes the display language",Integer.valueOf(KeyEvent.VK_B)));

    }

    /**
     * <p>
     * Create a menu contianing the list of Settings actions.
     * </p>
     * 
     * @return The Settings menu UI element.
     */
    public JMenu createMenu() {
        JMenu SettingsMenu = new JMenu("Settings");
        JMenu LanguageSubMenu = new JMenu("Language Select");

        for(Action action: actions) {
            LanguageSubMenu.add(new JMenuItem(action));
        }


        SettingsMenu.add(LanguageSubMenu);

        return SettingsMenu;
    }

    /**
     * <p>
     * Action to change the display language of Andie.
     * </p>
     * 
     * @see 
     */
    public class LanguageChangeEnglishAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change action.
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
         * This method is called whenever the LanguageChangeAction is triggered.
         * It prompts the user to choose language then applies the language change
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            Locale.setDefault(new Locale(prefs.get("language", "en"), 
            prefs.get("country","NZ")));
    
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

            try {
                Andie.frame.setJMenuBar(Andie.CreateMenuBar());
                Andie.frame.pack();
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        }

    }

    public class LanguageChangeSpanishAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change action.
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
         * This method is called whenever the LanguageChangeAction is triggered.
         * It prompts the user to choose language then applies the language change
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            Locale.setDefault(new Locale(prefs.get("language", "sp"), 
            prefs.get("country","SP")));
    
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

            try {
                Andie.frame.setJMenuBar(Andie.CreateMenuBar());
                Andie.frame.pack();
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        }

    }

    public class LanguageChangeFrenchAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change action.
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
         * This method is called whenever the LanguageChangeAction is triggered.
         * It prompts the user to choose language then applies the language change
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            Locale.setDefault(new Locale(prefs.get("language", "fr"), 
            prefs.get("country","FR")));
    
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

            try {
                Andie.frame.setJMenuBar(Andie.CreateMenuBar());
                Andie.frame.pack();
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        }

    }

    public class LanguageChangeMaoriAction extends ImageAction {

        /**
         * <p>
         * Create a new Language Change action.
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
         * This method is called whenever the LanguageChangeAction is triggered.
         * It prompts the user to choose language then applies the language change
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            Locale.setDefault(new Locale(prefs.get("language", "mi"), 
            prefs.get("country","NZ")));
    
            bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

            try {
                Andie.frame.setJMenuBar(Andie.CreateMenuBar());
                Andie.frame.pack();
                Andie.frame.setVisible(true);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


            
        }

    }
}
