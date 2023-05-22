package cosc202.andie;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * This class represents a listener for drawing points on an ImagePanel. It extends the ShapeListener class,
 * which is an abstract class for the listener class of each shape. It also extends the MouseInputAdapter class
 * for handling mouse input events.
 * 
 * <p>
 * The initial code for this class was adapted from the Oracle documentation on mouse motion listeners for Swing events.
 * </p>
 * 
 * <p>
 * This class provides functionality to draw points on the ImagePanel. It tracks the coordinates of the points being drawn
 * and applies the points to the image on mouse release. The drawn points can be customized with the shape color and radius.
 * </p>
 * 
 * <p>
 * This class contains overridden methods from the MouseInputAdapter class to handle mouse events such as mouse dragging,
 * mouse pressing, and mouse releasing. It also includes helper methods to update the point position based on the mouse
 * coordinates, as well as getters and setters for the point coordinates. The point drawing operation is applied to the image
 * on mouse release.
 * </p>
 * 
 * <p>
 * The <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html">Oracle Mouse Motion Listener Tutorial</a>
 * was used as a reference to create the initial point selection for the ImagePanel, which was then adapted to suit
 * the requirements of ANDIE (Analog and Digital Image Editing).
 * </p>
 * 
 * <p>
 * This software is distributed under the <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a> license.
 * </p>
 * 
 * @see javax.swing.event.MouseInputAdapter
 * @see cosc202.andie.ShapeListener
 * @author Liam Kerr
 * @version 1.0
 */
public class DrawListener extends ShapeListener {

    private Point pointToDraw = new Point(0, 0);
    private Color shapeColor;
    private int radius;
    private Collection<Point> points = new ArrayList<>();

    public DrawListener(ImagePanel target, Color shapeColor, int radius) {
        super(target);
        this.shapeColor = shapeColor;
        this.radius = radius;
    }

    public DrawListener(ImagePanel target) {
        super(target);
    }

    /**
     * Called when the mouse is clicked.
     * This method is empty in this implementation.
     *
     * @param e The MouseEvent triggered by the mouse click.
     */
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Called when the mouse is dragged.
     * Updates the position of the point based on the mouse position and repaints the target component.
     *
     * @param e The MouseEvent containing the mouse position.
     */
    public void mouseDragged(MouseEvent e) {
        updateSize(e);
    }

    /**
     * Called when the mouse enters the target component.
     * This method is empty in this implementation.
     *
     * @param e The MouseEvent triggered by the mouse entering the component.
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Called when the mouse exits the target component.
     * This method is empty in this implementation.
     *
     * @param e The MouseEvent triggered by the mouse exiting the component.
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Called when the mouse is moved.
     * This method is empty in this implementation.
     *
     * @param e The MouseEvent containing the mouse position.
     */
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Called when the mouse button is pressed.
     *

 Updates the position of the point based on the mouse position.
     *
     * @param e The MouseEvent triggered by the mouse button press.
     */
    public void mousePressed(MouseEvent e) {
        updateSize(e);
    }

    /**
     * Called when the mouse button is released.
     * Updates the position of the point and applies the points to the image.
     * Clears the drawn points, removes the ShapeListener, and repaints the target component.
     *
     * @param e The MouseEvent triggered by the mouse button release.
     */
    public void mouseReleased(MouseEvent e) {
        updateSize(e);
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }

    /**
     * Updates the position of the point based on the mouse position.
     * Adds the point to the collection of points and repaints the target component.
     *
     * @param e The MouseEvent containing the mouse position.
     */
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pointToDraw.setLocation(x, y);
        target.getImage().tempApplyBrightnessContrast(new Draw(pointToDraw, shapeColor, radius));
        points.add(pointToDraw);
        target.repaint();
    }

    /**
     * Returns the current point to draw.
     *
     * @return The current point to draw.
     */
    public Point getPointToDraw() {
        return pointToDraw;
    }

    /**
     * Sets the point coordinates to zero, effectively clearing the selected point.
     */
    public void setShapesToZero() {
        pointToDraw = new Point(0, 0);
    }

    /**
     * Paints the shape on the graphics object.
     * This method is empty in this implementation.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    protected void paintShape(Graphics2D g2) {
        // No shape to paint for points
    }

    @Override
    protected void updateDrawableShape(double compWidth, double compHeight) {
        // TODO Auto-generated method stub
        
    }
}

        