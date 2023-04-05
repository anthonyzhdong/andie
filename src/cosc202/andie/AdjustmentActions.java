package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * <p>
 * Actions provided by the Adjustments menu.
 * </p>
 * 
 * <p>
 * 
 * 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class AdjustmentActions{
 /** A list of actions for the Adjustments menu. */
 protected ArrayList<Action> actions;
 private int scale;
 /**
  * <p>
  * Create a set of Colour menu actions.
  * </p>
  */
 public AdjustmentActions() {
     actions = new ArrayList<Action>();
     actions.add(new ResizeLargerAction(SettingsActions.bundle.getString("IncreaseSize"), null, SettingsActions.bundle.getString("IncreaseSizeMessage"), Integer.valueOf(KeyEvent.VK_L)));
     actions.add(new RotationAction(SettingsActions.bundle.getString("Rotate"), null, SettingsActions.bundle.getString("RotateMessage"), Integer.valueOf(KeyEvent.VK_K)));
     actions.add(new FlipAction(SettingsActions.bundle.getString("Flip"), null, SettingsActions.bundle.getString("FlipDesc"), Integer.valueOf(KeyEvent.VK_F)));

 }
    /**
     * <p>
     * Create a menu contianing the list of Adjustment actions.
     * </p>
     * 
     * @return The adjustment menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(SettingsActions.bundle.getString("Adjustments"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

     /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class ResizeLargerAction extends ImageAction{
        ResizeLargerAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                BufferedImage originalTarget = target.getImage().getCurrentImage();
                BufferedImage copyTarget = target.getImage().getCurrentImage();
                
                // Pop-up dialog box to ask for the radius value.
                JSlider sizeSlider = new JSlider(0, 100, scale);
                JLabel sizeLabel = new JLabel("Size : " + scale + "%");

                class SizeSliderListener implements ChangeListener {
                

                    public void stateChanged(ChangeEvent e) {
                        scale = sizeSlider.getValue();
                        sizeLabel.setText("Size : " + scale + "%");
                        target.getImage().setCurrentImage(copyTarget);
                       // target.getImage().tempApplyBrightnessContrast(new Resizelarger(scale));
                        target.repaint();
                        target.getParent().revalidate();
                    }
                }

                SizeSliderListener ssl = new SizeSliderListener();
                sizeSlider.addChangeListener(ssl);

                JComponent[] labels = new JComponent[]  {sizeSlider, sizeLabel};
                int optionSize= JOptionPane.showOptionDialog(null, labels, SettingsActions.bundle.getString("EnterSize"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
                if (optionSize == JOptionPane.CANCEL_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    scale = 0;
                
                    target.repaint();
                    target.getParent().revalidate();
                    return;
                } else if (optionSize == JOptionPane.OK_OPTION) {
                    target.getImage().setCurrentImage(originalTarget);
                    target.getImage().apply(new ResizeLarger(scale));
                    target.repaint();
                    target.getParent().revalidate();
                }
                /** 
                target.getImage().apply(new ResizeLarger());
                target.repaint();
                target.getParent().revalidate();
                */
            } else {
                ErrorHandling.NoFileOpenError();
            }
            
        }
    }

    public class RotationAction extends ImageAction{
        RotationAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                target.getImage().apply(new Rotation());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
        }
            
        }
    }
    public class FlipAction extends ImageAction{
        FlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if(EditableImage.hasImage()){
                target.getImage().apply(new Flip());
                target.repaint();
                target.getParent().revalidate();
            } else {
                ErrorHandling.NoFileOpenError();
            }
        }
    }
}