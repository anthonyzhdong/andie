package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.*;


 /**
 * <p>
 * Creation of the toolbar.
 * </p>
 * 
 * <p>
 * The toolbar for ANDIE will have the undo, redo, save buttons added
 * Icons will show the actions rather than text
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Lachlan Graham
 * @version 1.0
 */
public class ToolBar {

    public static JToolBar toolbar;

    /**
     * <p>
     * Creates the toolbar.
     * </p>
     */
    public ToolBar() {}

    /**
     * <p>
     * Create a toolbar containing certain menubar functions.
     * </p>
     * 
     * @return A completed toolbar.
     */
    public static void createToolBar(JButton buttonIn) throws Exception {
        toolbar = new JToolBar();
        toolbar.setFloatable(true);
        toolbar.setRollover(true);

        Image saveImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/save24px.png"));
        Image saveSmallerImage = saveImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon saveIcon = new ImageIcon(saveSmallerImage, "Save");
        Action save = new FileActions.FileSaveAction(null, saveIcon , SettingsActions.bundle.getString("Save"), Integer.valueOf(KeyEvent.VK_S));
        JButton saveButton = new JButton(save);
        toolbar.add(saveButton);
        
        Image undoImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/undo24px.png"));
        Image undoSmallerImage = undoImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon undoIcon = new ImageIcon(undoSmallerImage, "Undo");
        Action undo = new EditActions.UndoAction(null, undoIcon, SettingsActions.bundle.getString("Undo"), Integer.valueOf(KeyEvent.VK_Z));
        JButton undoButton = new JButton(undo);
        toolbar.add(undoButton);

        /** 
        JComboBox jco = new JComboBox();
        // works vertically but is scaled with the other buttons and width wont change
        jco.setPreferredSize(new Dimension(5,5));
       // System.out.println(jco.getPreferredSize());
        jco.addItem("Test1");
        jco.addItem("Test2");
        toolbar.add(jco);
        */

        Image redoImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/redo24px.png"));
        Image redoSmallerImage = redoImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon redoIcon = new ImageIcon(redoSmallerImage, "Redo");
        Action redo = new EditActions.RedoAction(null, redoIcon, SettingsActions.bundle.getString("Redo"), Integer.valueOf(KeyEvent.VK_Y));
        JButton redoButton = new JButton(redo);
        toolbar.add(redoButton);

        Image brightnessAndContrastImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/brightnessAndContrast24px.png"));
        Image brightnessAndContrastSmallerImage = brightnessAndContrastImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon brightnessAndContrastIcon = new ImageIcon(brightnessAndContrastSmallerImage, "Brightness and Contrast");
        Action brightnessAndContrast = new ColourActions.BrightnessContrastAction(null, brightnessAndContrastIcon, SettingsActions.bundle.getString("BrightnessContrast"), Integer.valueOf(KeyEvent.VK_B));
        JButton brightnessAndContrastButton = new JButton(brightnessAndContrast);
        toolbar.add(brightnessAndContrastButton);

        if (buttonIn != null) {
            toolbar.add(buttonIn);
        } else {
            Image recordMacroImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/record24px.png"));
            Image recordMacroSmallerImage = recordMacroImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon recordMacroIcon = new ImageIcon(recordMacroSmallerImage, "Record Macro");
            Action recordMacro = new MacroActions.MacroRecordAction(null, recordMacroIcon, null, null);
            JButton recordMacroButton = new JButton(recordMacro);
            toolbar.add(recordMacroButton);
        }


        Container contentPane = Andie.frame.getContentPane();  
        contentPane.add(toolbar, BorderLayout.NORTH);  
    }

    public static void changeToolBarDuringMacroActionRecording() throws Exception{
        Andie.frame.getContentPane().remove(toolbar);
        Andie.frame.getContentPane().revalidate();
        Image recordMacroImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/stopRecording24px.png"));
        Image recordMacroSmallerImage = recordMacroImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon recordMacroIcon = new ImageIcon(recordMacroSmallerImage, "Recording Macro");
        Action recordMacro = new MacroActions.MacroStopRecordAction(null, recordMacroIcon, null, null);
        JButton recordMacroButton = new JButton(recordMacro);
        ToolBar.createToolBar(recordMacroButton);
    }

    public static void changeToolBarToDefault() throws Exception {
        Andie.frame.getContentPane().remove(toolbar);
        Andie.frame.getContentPane().revalidate();
        ToolBar.createToolBar(null);
    }
}

    









   