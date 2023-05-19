package cosc202.andie;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;

/**
 * <p>
 * Hotkeys to access actions quickly.
 * </p>
 * 
 * <p>
 * Hotkeys for the program include:
 * CTRL Z = undo
 * CTRL S = save
 * CTRL O = open
 * ESC = exit
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Lachlan Graham
 * @version 1.0
 */

public class Hotkeys extends ImageAction {

    public Hotkeys() {
        super(null, null, null, null);
    }

    /**
     * Creates hotkeys for the program, and associates actions to them.
     * Does this via adding ActionListeners to the ImagePanel so that anywhere within the program, the hotkeys will work.
     */
    public void createDefaultHotkeys(ImagePanel target) {

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), "Open");   
        target.getActionMap().put("Open", openAction);   

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "Save");   
        target.getActionMap().put("Save", saveAction);   

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "Undo");   
        target.getActionMap().put("Undo", undoAction);   

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");   
        target.getActionMap().put("Exit", exitAction);   
    }


    /**
     * Actions for the hotkeys
     */
    //Action for opening a file
    Action openAction = new AbstractAction("Open") {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    ErrorHandling.FileError();
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }
    };

    //Action for undoing an action
    Action undoAction = new AbstractAction("undo") {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(target.checkImage() == false) {
                ErrorHandling.NoFileOpenError();
                return;
            }
            try {
                target.getImage().undo();
                target.repaint();
                target.getParent().revalidate();
            } catch(Exception ex) {
                ErrorHandling.NoUndoError();
            }

        }
    };

    //Action for saving a file
    Action saveAction = new AbstractAction("Save") {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (EditableImage.hasImage()) {
                try {
                    target.getImage().save();
                } catch (Exception ex) {
                    ErrorHandling.NoFileOpenError();
                }
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    };

    //Action for exiting the program
    Action exitAction = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    //This is here to make the compiler happy
    public void actionPerformed(ActionEvent e) {
    }


    public static void createNewHotkey(Stack<ImageOperation> ops, String macroName, ArrayList<Integer> keystrokes) {

        Action newMacroAction = new AbstractAction(macroName) {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ImageOperation op : ops) {
                    try {
                        EditableImage.apply(op);
                    } catch (Exception ex) {
                        ErrorHandling.NoFileOpenError();
                    }
                }
            }
        };
        //need code to convert keystroke array into something that can be placed below
        //.toString().replaceFirst("(released )|(pressed )|(typed )", "");
        StringBuilder macroAsString = new StringBuilder();
        for (Integer key : keystrokes) {
            macroAsString.append(KeyEvent.getKeyText(key));
            macroAsString.append(" ");
        }

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(macroAsString.toString()), macroName);;
        target.getActionMap().put(macroName, newMacroAction);   

    }
}