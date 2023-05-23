package cosc202.andie;

import java.awt.event.*;

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
     * <p>
     * Creates hotkeys for the program, and associates actions to them.
     * </p>
     * <p>
     * Does this via adding ActionListeners to the ImagePanel so that anywhere
     * within the program, the hotkeys will work.
     * </p>
     * @param target the ImagePanel to add the hotkeys to.
     */
    public void createDefaultHotkeys(ImagePanel target) {

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), "Open");
        target.getActionMap().put("Open", openAction);

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "Save");
        target.getActionMap().put("Save", saveAction);

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "Undo");
        target.getActionMap().put("Undo", undoAction);

        target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                "Exit");
        target.getActionMap().put("Exit", exitAction);
    }


    /**
     * <p>
     * Action for opening a file.
     * </p>
     */
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

    /**
     * <p>
     * Action for undoing.
     * </p>
     */
    Action undoAction = new AbstractAction("undo") {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (target.checkImage() == false) {
                ErrorHandling.NoFileOpenError();
                return;
            }
            try {
                target.getImage().undo();
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                ErrorHandling.NoUndoError();
            }

        }
    };

    /**
     * <p>
     * Action for saving.
     * </p>
     */
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

    /**
     * <p>
     * Action for exiting.
     * </p>
     */
    Action exitAction = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    // This is here to make the compiler happy
    public void actionPerformed(ActionEvent e) {
    }
}