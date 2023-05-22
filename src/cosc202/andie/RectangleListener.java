package cosc202.andie;

import java.awt.*;
import java.awt.event.*;


/**
<p>
Class that enables the selection of a rectangular area within an ImagePanel based on user input.
It extends the ShapeListener class.
</p>
<p>
The RectangleListener class allows users to interactively define a rectangular region by clicking and dragging the mouse
within the ImagePanel. It provides methods to retrieve the selected rectangle coordinates and dimensions.
</p>
<p>
This class is designed to be used in conjunction with an ImagePanel component, where the rectangle selection
is visually represented by drawing a rectangle on top of the image.
</p>
<p>
This class is adapted from the ShapeListener class and incorporates additional functionality to support rectangle selection.
</p>
<p>
<a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
</p>
@see ShapeListener
@see ImagePanel
@author Liam Kerr
@version 3.0
*/

public class RectangleListener extends ShapeListener {

    private Rectangle initialRectangle = new Rectangle(0, 0, 0, 0);
    private Rectangle rectToDraw = new Rectangle(0, 0, 0, 0);
    private Rectangle currentRectangle = new Rectangle(0, 0, 0, 0);

    private static boolean select;

    public RectangleListener(ImagePanel target) {
        super(target);
    }

    /**
     * Called when the mouse is clicked.
     * Clears the selected rectangle and repaints the target component.
     */
    public void mouseClicked(MouseEvent e) {
        setShapesToZero();
        target.repaint();
    }

    /**
     * Called when the mouse is dragged.
     * Updates the size of the selected rectangle based on the mouse position.
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
     * If selection mode is enabled, updates the size of the selected rectangle.
     * Otherwise, clears the selected rectangle and repaints the target component.
     *
     * @param e The MouseEvent triggered by the mouse button release.
     */
    public void mouseReleased(MouseEvent e) {
        if (select) {
            updateSize(e);
        } else {
            setShapesToZero();
            target.repaint();
        }
    }

    /**
     * Updates the size of the selected rectangle based on the mouse position.
     *
     * @param e The MouseEvent containing the mouse position.
     */
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialRectangle.setSize(x - initialRectangle.x, y - initialRectangle.y);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
        currentRectangle = new Rectangle(rectToDraw);
    }

    /**
     * Updates the dimensions and position of the drawable shape based on the component size.
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
     * Sets the selection mode.
     *
     * @param b The selection mode.
     */
    public static void setSelect(boolean b) {
        select = b;
    }

    /**
     * Returns the current selection mode.
     *
     * @return The current selection mode.
     */
    public static boolean getSelect() {
        return select;
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
            g2.drawRect(rectToDraw.x, rectToDraw.y, (int) rectToDraw.getWidth(), (int) rectToDraw.getHeight());
        }
    }
}


        