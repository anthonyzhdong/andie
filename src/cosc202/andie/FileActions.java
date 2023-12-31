package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FileActions {

    /** file type that user wishes to save */
    public String fileTypeSelected;

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(SettingsActions.bundle.getString("Open"), null,
                SettingsActions.bundle.getString("OpenDesc"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction(SettingsActions.bundle.getString("Save"), null,
                SettingsActions.bundle.getString("SaveDesc"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(SettingsActions.bundle.getString("SaveAs"), null,
                SettingsActions.bundle.getString("SaveAsDesc"), Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileExportAction(SettingsActions.bundle.getString("Export"), null,
                SettingsActions.bundle.getString("ExportDesc"), Integer.valueOf(0)));
        actions.add(new FileExitAction(SettingsActions.bundle.getString("Exit"), null,
                SettingsActions.bundle.getString("ExitDesc"), Integer.valueOf(0)));
    }

    /**
     * <p>
     * Create a menu contianing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(SettingsActions.bundle.getString("File"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                String opsFileName = fileChooser.getSelectedFile().getAbsolutePath();
                String test = opsFileName.substring(fileChooser.getSelectedFile().getAbsolutePath().length() - 4);
                String compare = ".ops";
                if (test.equals(compare)) {
                    try {
                        FileInputStream fileIn = new FileInputStream(opsFileName);
                        ObjectInputStream objIn = new ObjectInputStream(fileIn);
                        @SuppressWarnings("unchecked")
                        Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objIn.readObject();
                        EditableImage.applyMacro(opsFromFile);
                        objIn.close();
                        fileIn.close();
                    } catch (Exception ex) {
                        ErrorHandling.FileError();
                    }
                } else {
                    try {
                        String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        target.getImage().open(imageFilepath);
                    } catch (Exception ex) {
                        ErrorHandling.FileError();
                    }
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public static class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (EditableImage.hasImage()) {
                if (target.getShapeListener() != null)
                    target.removeShapeListener();
                RectangleListener.setSelect(false);
                try {
                    target.getImage().save();
                } catch (Exception ex) {
                    ErrorHandling.NoFileOpenError();
                }
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            if (EditableImage.hasImage()) {
                if (target.getShapeListener() != null)
                    target.removeShapeListener();
                RectangleListener.setSelect(false);
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(target);

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        target.getImage().saveAs(imageFilepath);
                    } catch (Exception ex) {
                        ErrorHandling.NoFileOpenError();
                    }
                }
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("huhuhuhu");
            System.exit(0);
        }

    }

    /**
     * <p>
     * Action to export an image to a new file location optionally a new file type.
     * </p>
     * 
     */
    public class FileExportAction extends ImageAction {

        /**
         * <p>
         * Create a new file-export action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-export action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExportAction is triggered.
         * It prompts the user to select a file name and type and exports the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (EditableImage.hasImage()) {
                fileTypeSelect();
                if (fileTypeSelected != null) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(target);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath()
                                    .concat(fileTypeSelected);
                            target.getImage().export(imageFilepath);
                        } catch (Exception ex) {
                            ErrorHandling.NoFileOpenError();
                        }
                    }
                }
            } else {
                ErrorHandling.NoFileOpenError();
            }

            fileTypeSelected = null;
        }

        /**
         * <p>
         * Creates a diolog box to select what file type you would like to save as
         * </p>
         * @return selected file type as String
         * 
         */
        private String fileTypeSelect() {
            JFrame frame = new JFrame(SettingsActions.bundle.getString("FileTypeSelectTitle"));

            Object[] fileTypes = { ".jpg", ".png", ".gif" };
            fileTypeSelected = (String) JOptionPane.showInputDialog(
                    frame,
                    SettingsActions.bundle.getString("FileTypeSelectMessage"),
                    SettingsActions.bundle.getString("FileTypeSelectPrompt"),
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    fileTypes,
                    null);

            return fileTypeSelected;
        }

    }
}
