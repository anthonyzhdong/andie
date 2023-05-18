package cosc202.andie;

import java.awt.*;

import javax.swing.*;
import java.awt.image.*;
import java.net.http.WebSocket.Listener;

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
    private RectangleListener rectangleListener = new RectangleListener(this);
    private RectangleDrawListener rectangleDrawListener = new RectangleDrawListener(this);
    private OvalListener ovalListener = new OvalListener(this);
    private LineListener lineListener = new LineListener(this);
    
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
    }

    public ImagePanel(BufferedImage bi) {
        image = new EditableImage();
        image.setCurrentImage(bi);
        scale = 1.0;
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
            rectangleListener.paintShape(g2);
            paintOvalSelect(g2);
            paintRectangleDraw(g2);
            paintLine(g2);
            g2.dispose();
        }
    }

    

    private void paintOvalSelect(Graphics2D g2) {
        if (ovalListener.getInitialOval() != null) {
            //Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); //Color of line varies depending on image colors
            g2.drawOval((int)ovalListener.getOvalToDraw().getX(), (int)ovalListener.getOvalToDraw().getY(), (int)ovalListener.getOvalToDraw().getWidth() - 1, (int)ovalListener.getOvalToDraw().getHeight() - 1);
        }
    }

    private void paintRectangleDraw(Graphics2D g2) {
        if (ovalListener.getInitialOval() != null) {
            //Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); //Color of line varies depending on image colors
            g2.drawRect(rectangleDrawListener.getRectToDraw().x, rectangleDrawListener.getRectToDraw().y, rectangleDrawListener.getRectToDraw().width - 1, rectangleDrawListener.getRectToDraw().height - 1);
        }
    }

    private void paintLine(Graphics2D g2) {
        if (ovalListener.getInitialOval() != null) {
            //Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); //Color of line varies depending on image colors
            g2.drawLine((int)lineListener.getInitialPoint().getX(), (int)lineListener.getInitialPoint().getY(), (int)lineListener.getPointToDraw().getX(), (int)lineListener.getPointToDraw().getY());
        }
    }

    public static boolean checkImage() {
        return image.hasImage(); 
    }

    public void setImage(EditableImage image) {
        this.image = image;
    }

    public void addRectangleSelectListener() {
        addMouseListener(rectangleListener);
        addMouseMotionListener(rectangleListener);
    }

    public void removeRectangleSelectListener() {
        removeMouseListener(rectangleListener);
        removeMouseMotionListener(rectangleListener);
    }


    public void addRectangleDrawListener() {
        addMouseListener(rectangleDrawListener);
        addMouseMotionListener(rectangleDrawListener);
    }

    public void removeRectangleDrawListener() {
        removeMouseListener(rectangleDrawListener);
        removeMouseMotionListener(rectangleDrawListener);
    }

    public void addOvalListener() {
        addMouseListener(ovalListener);
        addMouseMotionListener(ovalListener);
    }

    public void removeOvalListener() {
        removeMouseListener(ovalListener);
        removeMouseMotionListener(ovalListener);
    }

    public void addLineListener() {
        addMouseListener(lineListener);
        addMouseMotionListener(lineListener);
    }

    public void removeLineListener() {
        removeMouseListener(lineListener);
        removeMouseMotionListener(lineListener);
    }

    public RectangleListener getRectangleListener() {
        return rectangleListener;
    }
    public OvalListener getOvalListener() {
        return ovalListener;
    }
    public RectangleDrawListener getRectangleDrawListener() {
        return rectangleDrawListener;
    }

    public LineListener getLineListener() {
        return lineListener;
    }
    /* 
    public LineListener getLineListener() {
        return lineListener;
    } */
    
    public void removeAllListeners() {
        removeMouseListener(rectangleListener);
        removeMouseMotionListener(rectangleListener);
        removeMouseListener(ovalListener);
        removeMouseMotionListener(ovalListener);
        removeMouseListener(rectangleListener);
        removeMouseMotionListener(rectangleListener);
    }

}
