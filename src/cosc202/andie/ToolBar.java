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
 * @version 2.0
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
     * @param buttonIn The button to add to the toolbar, if null, default toolbar is made.
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
        toolbar.addSeparator();
        
        Image undoImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/undo24px.png"));
        Image undoSmallerImage = undoImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon undoIcon = new ImageIcon(undoSmallerImage, "Undo");
        Action undo = new EditActions.UndoAction(null, undoIcon, SettingsActions.bundle.getString("Undo"), Integer.valueOf(KeyEvent.VK_Z));
        JButton undoButton = new JButton(undo);
        toolbar.add(undoButton);
        toolbar.addSeparator();

        Image redoImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/redo24px.png"));
        Image redoSmallerImage = redoImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon redoIcon = new ImageIcon(redoSmallerImage, "Redo");
        Action redo = new EditActions.RedoAction(null, redoIcon, SettingsActions.bundle.getString("Redo"), Integer.valueOf(KeyEvent.VK_Y));
        JButton redoButton = new JButton(redo);
        toolbar.add(redoButton);
        toolbar.addSeparator();

        Image brightnessAndContrastImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/brightnessAndContrast24px.png"));
        Image brightnessAndContrastSmallerImage = brightnessAndContrastImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon brightnessAndContrastIcon = new ImageIcon(brightnessAndContrastSmallerImage, "Brightness and Contrast");
        Action brightnessAndContrast = new ColourActions.BrightnessContrastAction(null, brightnessAndContrastIcon, SettingsActions.bundle.getString("BrightnessContrast"), Integer.valueOf(KeyEvent.VK_B));
        JButton brightnessAndContrastButton = new JButton(brightnessAndContrast);
        toolbar.add(brightnessAndContrastButton);
        toolbar.addSeparator();

        Image rectangleSelectImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/rectangle-select.png"));
        Image rectangleSelectSmallerImage = rectangleSelectImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon rectangleSelectIcon = new ImageIcon(rectangleSelectSmallerImage, "rectangleSelect");
        Action rectangleSelect = new AdjustmentActions.RectangularSelectAction(null, rectangleSelectIcon, SettingsActions.bundle.getString("RectangularSelect"), Integer.valueOf(KeyEvent.VK_L));
        JButton rectangleSelectButton = new JButton(rectangleSelect);
        toolbar.add(rectangleSelectButton);
        toolbar.addSeparator();

        Image imageCropImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/crop.png"));
        Image imageCropSmallerImage = imageCropImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageCropIcon = new ImageIcon(imageCropSmallerImage, "imageCrop");
        Action imageCrop = new AdjustmentActions.ImageCropAction(null, imageCropIcon, SettingsActions.bundle.getString("ImageCrop"), Integer.valueOf(KeyEvent.VK_L));
        JButton imageCropButton = new JButton(imageCrop);
        toolbar.add(imageCropButton);
        toolbar.addSeparator();

        Image drawLineImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/drawLine24px.png"));
        Image drawLineSmallerImage = drawLineImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon drawLineIcon = new ImageIcon(drawLineSmallerImage, "drawLine");
        Action drawLine = new ShapeActions.DrawAction(null, drawLineIcon, SettingsActions.bundle.getString("DrawLine"), Integer.valueOf(KeyEvent.VK_L));
        JButton drawLineButton = new JButton(drawLine);
        toolbar.add(drawLineButton);
        toolbar.addSeparator();

        Image drawRectangleImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/rectangle.png"));
        Image drawRectangleSmallerImage = drawRectangleImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon drawRectangleIcon = new ImageIcon(drawRectangleSmallerImage, "DrawRectangle");
        Action drawRectangle = new ShapeActions.DrawRectangleAction(null, drawRectangleIcon, SettingsActions.bundle.getString("DrawRectangle"), Integer.valueOf(KeyEvent.VK_L));
        JButton drawRectangleButton = new JButton(drawRectangle);
        toolbar.add(drawRectangleButton);
        toolbar.addSeparator();

        Image drawOvalImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/circle.png"));
        Image drawOvalSmallerImage = drawOvalImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon drawOvalIcon = new ImageIcon(drawOvalSmallerImage, "imageCrop");
        Action drawOval = new ShapeActions.DrawOvalAction(null, drawOvalIcon, SettingsActions.bundle.getString("DrawOval"), Integer.valueOf(KeyEvent.VK_L));
        JButton drawOvalButton = new JButton(drawOval);
        toolbar.add(drawOvalButton);
        toolbar.addSeparator();

        Image colorOutlineSelectImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/color-outline-selection.png"));
        Image colorOutlineSelectSmallerImage = colorOutlineSelectImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon colorOutlineSelectIcon = new ImageIcon(colorOutlineSelectSmallerImage, "OutlineColorSelect");
        Action colorOutlineSelect = new ShapeActions.ShapeOutlineAction(null, colorOutlineSelectIcon, SettingsActions.bundle.getString("ShapeOutlineSettings"), Integer.valueOf(KeyEvent.VK_L));
        JButton colorOutlineSelectButton = new JButton(colorOutlineSelect);
        toolbar.add(colorOutlineSelectButton);
        toolbar.addSeparator();

        Image colorFillSelectImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/color-fill-selection.png"));
        Image colorFillSelectSmallerImage = colorFillSelectImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon colorFillSelectIcon = new ImageIcon(colorFillSelectSmallerImage, "FillColorSelect");
        Action colorFillSelect = new ShapeActions.ShapeFillAction(null, colorFillSelectIcon, SettingsActions.bundle.getString("ShapeFillSettings"), Integer.valueOf(KeyEvent.VK_L));
        JButton colorFillSelectButton = new JButton(colorFillSelect);
        toolbar.add(colorFillSelectButton);
        toolbar.addSeparator();

        Image eyedropperImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/color-picker.png"));
        Image eyedropperSmallerImage = eyedropperImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon eyedropperIcon = new ImageIcon(eyedropperSmallerImage, "FillColorSelect");
        Action eyedropper = new ShapeActions.EyeDropperAction(null, eyedropperIcon, SettingsActions.bundle.getString("EyeDropper"), Integer.valueOf(KeyEvent.VK_L));
        JButton eyedropperButton = new JButton(eyedropper);
        toolbar.add(eyedropperButton);
        toolbar.addSeparator();

        if (buttonIn != null) {
            toolbar.add(buttonIn);
        } else {
            Image recordMacroImage = ImageIO.read(Andie.class.getClassLoader().getResource("Icons/record24px.png"));
            Image recordMacroSmallerImage = recordMacroImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon recordMacroIcon = new ImageIcon(recordMacroSmallerImage, "Record Macro");
            Action recordMacro = new MacroActions.MacroRecordAction(null, recordMacroIcon, SettingsActions.bundle.getString("MacroButton"), null);
            JButton recordMacroButton = new JButton(recordMacro);
            toolbar.add(recordMacroButton);
        }

        Container contentPane = Andie.frame.getContentPane();  
        contentPane.add(toolbar, BorderLayout.NORTH);  
    }


    /**
     * <p>
     * Changes the toolbar to show the stop recording button.
     * </p>
     */
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

    /**
     * <p>
     * Changes the toolbar back to the default toolbar.
     * </p>
     */
    public static void changeToolBarToDefault() throws Exception {
        Andie.frame.getContentPane().remove(toolbar);
        Andie.frame.getContentPane().revalidate();
        ToolBar.createToolBar(null);
    }
}

    









   