package cosc202.andie;

import java.awt.*;

import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.event.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel {
    
    /**
     * The image to display in the ImagePanel.
     */
    private static EditableImage image;


    private Rectangle initialRectangle = null;
    private Rectangle rectToDraw = null;
    private Rectangle currentRectangle = null;
    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        MyListener myListener = new MyListener();
        addMouseListener(myListener);
        addMouseMotionListener(myListener);
    }

    public ImagePanel(BufferedImage bi) {
        image = new EditableImage();
        image.setCurrentImage(bi);
        scale = 1.0;
        MyListener myListener = new MyListener();
        addMouseListener(myListener);
        addMouseMotionListener(myListener);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }


    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }


    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            if (initialRectangle != null) {
                //Draw a rectangle on top of the image.
                g.setXORMode(Color.white);; //Color of line varies depending on image colors
                g.drawRect(rectToDraw.x, rectToDraw.y, rectToDraw.width - 1, rectToDraw.height - 1);
            }
            g2.dispose();
        }
    }

    public Rectangle getinitialRectangle() {
        return currentRectangle;
    }
    
    public static boolean checkImage() {
        return image.hasImage();
    }

    public void setImage(EditableImage image) {
        this.image = image;
    }


    /** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
    private class MyListener extends MouseInputAdapter {
        public void mouseClicked(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
            updateSize(e);
        }

        public void mouseEntered(MouseEvent e) {
                
        }

        public void mouseExited(MouseEvent e) {
                
        }

        public void mouseMoved(MouseEvent e) {
            
        }

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            initialRectangle = new Rectangle(x, y, 0, 0);
            updateDrawableRect(getWidth(), getHeight());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            updateSize(e);
        }

        void updateSize(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            initialRectangle.setSize(x - initialRectangle.x, y - initialRectangle.y);
            updateDrawableRect(getWidth(), getHeight());
            repaint();
            currentRectangle = new Rectangle(rectToDraw);
        }
    }
    
    private void updateDrawableRect(int compWidth, int compHeight) {
        int x = initialRectangle.x;
        int y = initialRectangle.y;
        int width = initialRectangle.width;
        int height = initialRectangle.height;

        //Make the width and height positive, if necessary.
        if (width < 0) {
            width = 0 - width;
            x = x - width + 1; 
            if (x < 0) {
                width += x; 
                x = 0;
            }
        }
        if (height < 0) {
            height = 0 - height;
            y = y - height + 1; 
            if (y < 0) {
                height += y; 
                y = 0;
            }
        } 
        //The rectangle shouldn't extend past the drawing area.
        if ((x + width) > compWidth) {
            width = compWidth - x;
        }
        if ((y + height) > compHeight) {
            height = compHeight - y;
        }
        rectToDraw = new Rectangle(x, y, width, height);
        //Update rectToDraw after saving old value.
        
    }

}
