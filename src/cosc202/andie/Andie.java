package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;


/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {
    //Testing branch use
    

    //Launches GUI frame for application (outside so accessable for other classes)
    
    public static JFrame frame = new JFrame("ANDIE");
    public static JMenuBar menuBar = createMenuBar();;
    public static boolean status = true;
    public static ImagePanel imagePanel = new ImagePanel();
    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save, edit, etc. 
     * These operations are implemented {@link ImageOperation}s and triggerd via 
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see AdjustmentActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     * 
     */
    public static void createAndShowGUI() throws Exception {

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates toolbar and hotkeys
        ToolBar.createToolBar(null);
        Hotkeys hotkeys = new Hotkeys();
        hotkeys.createDefaultHotkeys(imagePanel);
        
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        //frame.pack();
        frame.setVisible(true);
    }


       /**
        * <p>
        * Method for creating the menu bar.
        * </p>
        * 
        * <p>
        * This method is called when Andie is initialized and when language change actions occur.
        * </p>
        * 
        * @return JMenuBar to be used in frame.
        */

    public static JMenuBar createMenuBar(){

        frame.setJMenuBar(null);
        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Actions that affect the display or 'under the hood' components of Andie
        AdjustmentActions adjustmentActions = new AdjustmentActions();
        menuBar.add(adjustmentActions.createMenu());

        ShapeActions shapeActions = new ShapeActions();
        menuBar.add(shapeActions.createMenu());

        // Actions that affect the display or 'under the hood' components of Andie
        SettingsActions languageActions = new SettingsActions();
        menuBar.add(languageActions.createMenu());


        return menuBar;
    }

    public static void setMenuBarStatus(boolean b) {
        for(int i = 0; i < menuBar.getMenuCount(); i++){
            menuBar.getMenu(i).setEnabled(b);
        }
        status = b;
    }

    public static boolean getMenuBarStatus() {
        return status;
    }
    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
}
