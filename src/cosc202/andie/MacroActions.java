package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class MacroActions {

    static Stack<ImageOperation> opsPreMacro;
    static Stack<ImageOperation> opsPostMacro;
    static Stack<ImageOperation> macroOps;
    static KeyAdapter keyAdapter;
    static ArrayList<Integer> keystrokes;
    static String opsFilename;

    /**
     * <p>
     * Action to record a new Macro.
     * </p>
     * 
     * @see cosc202.andie.ImageAction ImageAction
     */
    public static class MacroRecordAction extends ImageAction {

        /**
         * <p>
         * Create a new MacroAction.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MacroAction is triggered.
         * It records a new Macro.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            JDialog frame = new JDialog();
            Object[] options = { "Yes",
                    "No",
                    "Advanced Options" };
            int n = JOptionPane.showOptionDialog(frame,
                    "Are you ready to record a new Macro?",
                    "Macro Recording Options",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

            if (n == 0) {
                // Macro begin recording code
                // make stop button appear on toolbar
                try {
                    ToolBar.changeToolBarDuringMacroActionRecording();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // explain to user how to stop recording macro
                JDialog stopRecordingInfo = new JDialog();
                JOptionPane.showMessageDialog(stopRecordingInfo,
                        "Press OK when ready to begin recording the actions for your new Macro." +
                                "\n\nWhen you are finished, press the stop button on the toolbar to assign key bindings to your Macro.");

                // creates ops files for pre macro recording
                opsPreMacro = new Stack<ImageOperation>();
                for (ImageOperation op : EditableImage.getOps()) {
                    opsPreMacro.push(op);
                }

                //

            }
            if (n == 2) {
                int y = JOptionPane.showOptionDialog(frame,
                        "Select presaved .ops file to create Macro?",
                        "Create Macro From .ops File",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                // ObjectInputStream objIn = new ObjectInputStream(fileIn); potential code for
                // reading in .ops file (found in EditableImage.java, line 156)
                // @SuppressWarnings("unchecked")
                // Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>)
                // objIn.readObject();
                // ops = opsFromFile;
                // objIn.close();
            }

        }

    }

    /**
     * <p>
     * Action to stop recording of Macro.
     * </p>
     * 
     * @see cosc202.andie.ImageAction ImageAction
     */
    public static class MacroStopRecordAction extends ImageAction {

        /**
         * <p>
         * Show list of macros saved.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroStopRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MacroListAction is triggered.
         * It prints the recorded macros in a diolog box.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // creates .ops file for post macro recording.
            // then calculates the difference between pre and post macro recording to get
            // final macro. If no pre macro recording, final = post
            opsPostMacro = EditableImage.getOps();
            macroOps = new Stack<ImageOperation>();
            int index = 0;
            if (opsPreMacro.size() > 0) {
                if (opsPreMacro.size() > 1) {
                    index = opsPostMacro.size() - opsPreMacro.size() - 2;
                } else {
                    index = opsPostMacro.size() - opsPreMacro.size() - 1;
                }
                while (index < opsPostMacro.size()) {
                    macroOps.push(opsPostMacro.get(index));
                    index++;
                }
            } else {
                macroOps = opsPostMacro;
            } 

            //creates a listener for keystroke bindings and initializes keystroke array
            keystrokes = new ArrayList<Integer>();

            keyAdapter = new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                  int keyCode = e.getKeyCode();
                    keystrokes.add((Integer) keyCode);
                }
              };

            

            //open dialog box to ask for keystrokes for new Macro
            JDialog macroKeystrokeFrame = new JDialog();
            JOptionPane.showMessageDialog(macroKeystrokeFrame,
            "Press OK to input key bindings for your new Macro." +
                    "\n\nWhen you are finished, press the tick button on the toolbar to name your Macro.");

            //initializes keystroke listener
            Andie.frame.addKeyListener(keyAdapter);

            //changes toolbar to show tick for completion of macro making
            try {
                ToolBar.changeToolBarDuringMacroKeystrokeRecording();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }



    }

}

/**
 * <p>
 * Action to stop recording of Macro.
 * </p>
 * 
 * @see cosc202.andie.ImageAction ImageAction
 */
public static class MacroSaveAction extends ImageAction {

    /**
     * <p>
     * Show list of macros saved.
     * </p>
     * 
     * @param name     The name of the action (ignored if null).
     * @param icon     An icon to use to represent the action (ignored if null).
     * @param desc     A brief description of the action (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
     */
    MacroSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * This method is called whenever the MacroListAction is triggered.
     * It prints the recorded macros in a diolog box.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {

        // stops listening for keystrokes
        Andie.frame.removeKeyListener(keyAdapter);

        // changes toolbar back to default
        try {
            ToolBar.changeToolBarToDefault();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // open dialog box to ask for Macro name
        JDialog macroNameFrame = new JDialog();
        opsFilename = JOptionPane.showInputDialog(macroNameFrame, "Enter the name for your new Macro:");

        //sends collected .ops file, keystrokes and desired name for macro to be created
        Hotkeys.createNewHotkey(macroOps, opsFilename, keystrokes);

    }

}

/**
 * <p>
 * Action to view list of Macros.
 * </p>
 * 
 * @see cosc202.andie.ImageAction ImageAction
 */
public static class MacroListAction extends ImageAction {

    /**
     * <p>
     * Show list of macros saved.
     * </p>
     * 
     * @param name     The name of the action (ignored if null).
     * @param icon     An icon to use to represent the action (ignored if null).
     * @param desc     A brief description of the action (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
     */
    MacroListAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * This method is called whenever the MacroListAction is triggered.
     * It prints the recorded macros in a diolog box.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("Macro");
    }

}

/**
 * <p>
 * Action to delete one or multiple Macros.
 * </p>
 * 
 * @see cosc202.andie.ImageAction ImageAction
 */
public static class MacroDeleteAction extends ImageAction {

    /**
     * <p>
     * Create a new MacroDeleteAction.
     * </p>
     * 
     * @param name     The name of the action (ignored if null).
     * @param icon     An icon to use to represent the action (ignored if null).
     * @param desc     A brief description of the action (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
     */
    MacroDeleteAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * This method is called whenever the MacroDeleteAction is triggered.
     * It deletes all selected macros.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("Macro");
    }

}}
