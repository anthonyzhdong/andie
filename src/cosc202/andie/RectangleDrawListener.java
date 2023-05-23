package cosc202.andie;

import java.awt.*;
import java.awt.event.*;


/**
 * This class represents a listener for drawing rectangles on an ImagePanel. It extends the ShapeListener class,
 * which is an abstract class for the listener class of each shape. It also extends the MouseInputAdapter class
 * for handling mouse input events.
 * 
 * <p>
 * The initial code for this class was adapted from the Oracle documentation on mouse motion listeners for Swing events.
 * </p>
 * 
 * <p>
 * This class provides functionality to draw rectangles on the ImagePanel. It tracks the coordinates of the rectangle, including
 * the initial point, the current point being drawn, and the final point. The rectangle appearance can be customized with
 * the shape outline color, fill color, line width, and options for filling and outlining the shape. The drawn rectangle is
 * applied to the image on mouse release.
 * </p>
 * 
 * <p>
 * This class contains overridden methods from the MouseInputAdapter class to handle mouse events such as mouse dragging,
 * mouse pressing, and mouse releasing. It also includes helper methods to update the rectangle size and position based on
 * the mouse coordinates, as well as getters and setters for the rectangle coordinates. The rectangle drawing operation is
 * applied to the image on mouse release.
 * </p>
 * 
 * <p>
 * The <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html">Oracle Mouse Motion Listener Tutorial</a>
 * was used as a reference to create the initial rectangle selection for the ImagePanel, which was then adapted to suit
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
 * @version 3.0
 */

public class RectangleDrawListener extends ShapeListener {

    private Rectangle initialRectangle = new Rectangle(0, 0, 0, 0);
    private Rectangle rectToDraw = new Rectangle(0, 0, 0, 0);
    private Rectangle currentRectangle = new Rectangle(0, 0, 0, 0);
    private Color shapeOutlineColour;
    private Color shapeFillColour;
    private float lineWidth;
    private boolean shapeFill;
    private boolean shapeOutline;
    private boolean outlineEyeDropper;

<<<<<<< HEAD
    public RectangleDrawListener(ImagePanel target, Color shapeOutlineColour, Color shapeFillColour, float lineWidth, boolean shapeFill, boolean shapeOutline) {
=======
    public RectangleDrawListener(ImagePanel target, Color shapeOutlineColour, Color shapeFillColour, float lineWidth,boolean shapeFill, boolean shapeOutline, boolean outlineEyeDropper) {
>>>>>>> bdd61a7 (eye dropper rectange done)
        super(target);
        this.shapeOutlineColour = shapeOutlineColour;
        this.shapeFillColour = shapeFillColour;
        this.lineWidth = lineWidth;
        this.shapeFill = shapeFill;
        this.shapeOutline = shapeOutline;
        this.outlineEyeDropper = outlineEyeDropper;
    }

    public RectangleDrawListener(ImagePanel target) {
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
     * Updates the size of the rectangle based on the mouse position and repaints the target component.
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
     * Initializes the initialRectangle based on the mouse position and updates the drawable shape.
     *
     * @param e The MouseEvent triggered by the mouse button press.
     */
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialRectangle = new Rectangle(x, y, 0, 0);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    /**
     * Called when the mouse button is released.
     * Updates the currentRectangle with the drawn rectangle and applies the rectangle to the image.
     * Clears the drawn rectangle, removes the ShapeListener, and repaints the target component.
     *
     * @param e The MouseEvent triggered by the mouse button release.
     */
    public void mouseReleased(MouseEvent e) {
        currentRectangle.setBounds(rectToDraw);
<<<<<<< HEAD
        target.getImage().apply(new DrawRectangle(currentRectangle, shapeOutlineColour, shapeFillColour, lineWidth, shapeFill, shapeOutline));
=======
        target.getImage().apply(new DrawRectangle(currentRectangle,shapeOutlineColour,shapeFillColour,lineWidth, shapeFill, shapeOutline, outlineEyeDropper));
>>>>>>> bdd61a7 (eye dropper rectange done)
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }

    /**
     * Updates the size of the rectangle based on the mouse position and the initial rectangle.
     * Updates the drawable shape and repaints the target component.
     *
     * @param e The MouseEvent containing the mouse position.
     */
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialRectangle.setSize(x - initialRectangle.x, y - initialRectangle.y);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    /**
     * Updates the drawable shape based on the component size and the initial rectangle.
     * Ensures that the rectangle stays within the bounds of the component.
     *
     * @param compWidth  The width of the component.
     * @param compHeight The height of the component.
     */
    protected void updateDrawableShape(double compWidth, double compHeight) {
        int x = initialRectangle.x;
        int y = initialRectangle.y;
        int width = initialRectangle.width;
        int height = initialRectangle.height;

        // Make the width and height positive, if necessary.
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
        // The rectangle shouldn't extend past the drawing area.
        if ((x + width) > compWidth) {
            width = (int) compWidth - x;
        }
        if ((y + height) > compHeight) {
            height = (int) compHeight - y;
        }
        rectToDraw = new Rectangle(x, y, width, height);
        // Update rectToDraw after saving the old value.
    }

    /**
     * Returns the current selected rectangle.
     *
     * @return The current selected rectangle.
     */
    public Rectangle getCurrentRectangle() {
        return currentRectangle;
    }

    /**
     * Returns the initial rectangle when the mouse button was pressed.
     *
     * @return The initial rectangle.
     */
    public Rectangle getInitialRectangle() {
        return initialRectangle;
    }

    /**
     * Returns the rectangle to draw on the component.
     *
     * @return The rectangle to draw.
     */
    public Rectangle getRectToDraw() {
        return rectToDraw;
    }

    /**
     * Sets all the shapes to zero, effectively clearing the selected rectangle.
     */
    public void setShapesToZero() {
        currentRectangle = new Rectangle(0, 0, 0, 0);
        rectToDraw = new Rectangle(0, 0, 0, 0);
        initialRectangle = new Rectangle(0, 0, 0, 0);
    }

    /**
     * Paints the shape on the graphics object.
     * Draws a rectangle on top of the image component to represent the selected rectangle.
     *
     * @param g2 The Graphics2D object to draw on.
     */
    protected void paintShape(Graphics2D g2) {
        if (initialRectangle != null) {
            // Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); // Color of line varies depending on image colors
            g2.drawRect(getRectToDraw().x, getRectToDraw().y, getRectToDraw().width - 1, getRectToDraw().height - 1);
        }
    }
}
