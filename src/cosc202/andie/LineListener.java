package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;


/**
 * This class represents a listener for drawing lines on an ImagePanel. It extends the ShapeListener class,
 * which is an abstract class for the listener class of each shape. It also extends the MouseInputAdapter class
 * for handling mouse input events.
 * 
 * <p>
 * The initial code for this class was adapted from the Oracle documentation on mouse motion listeners for Swing events.
 * </p>
 * 
 * <p>
 * This class provides functionality to draw lines on the ImagePanel. It tracks the coordinates of the line, including
 * the initial point, the current point being drawn, and the final point. The line appearance can be customized with
 * the shape outline color and line width.
 * </p>
 * 
 * <p>
 * This class contains overridden methods from the MouseInputAdapter class to handle mouse events such as mouse dragging,
 * mouse pressing, and mouse releasing. It also includes helper methods to update the line size and position based on
 * the mouse coordinates, as well as getters and setters for the line coordinates. The line drawing operation is applied
 * to the image on mouse release.
 * </p>
 * 
 * <p>
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html">Oracle Mouse Motion Listener Tutorial</a>
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

public class LineListener extends ShapeListener {

    // Variables for tracking line coordinates
    private Point initialPoint = new Point(0, 0);
    private Point pointToDraw = new Point(0, 0);
    private Point finalPoint = new Point(0, 0);
    private Line2D currentLine = new Line2D.Double(initialPoint, pointToDraw);

    // Variables for shape appearance
    private Color shapeOutlineColour;
    private float lineWidth;

    /**
     * Constructs a LineListener with the specified parameters.
     * @param target              the ImagePanel to draw on
     * @param shapeOutlineColour  the color of the line
     * @param lineWidth           the width of the line
     */
    public LineListener(ImagePanel target, Color shapeOutlineColour, float lineWidth) {
        super(target);
        this.shapeOutlineColour = shapeOutlineColour;
        this.lineWidth = lineWidth;
    }

    /**
     * Constructs a LineListener with the specified ImagePanel target.
     * @param target  the ImagePanel to draw on
     */
    public LineListener(ImagePanel target) {
        super(target);
    }

    // Overridden event handling methods

    @Override
    public void mouseClicked(MouseEvent e) {
        // No action needed for mouse click event
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateSize(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No action needed for mouse enter event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No action needed for mouse exit event
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // No action needed for mouse move event
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Update the initial point of the line
        int x = e.getX();
        int y = e.getY();
        initialPoint = new Point(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Complete the line and apply the drawing operation to the image
        finalPoint.setLocation(pointToDraw.getX(), pointToDraw.getY());
        currentLine.setLine(initialPoint, finalPoint);
        target.getImage().apply(new DrawLine(currentLine, shapeOutlineColour, lineWidth));
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }

    // Helper methods

    /**
     * Updates the size and position of the line based on the current mouse coordinates.
     * @param e  the MouseEvent containing the current mouse position
     */
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pointToDraw.setLocation(x, y);
        currentLine = new Line2D.Double(initialPoint, pointToDraw);
        target.repaint();
    }

    // Getters for line coordinates

    public Point getInitialPoint() {
        return initialPoint;
    }

    public Point getPointToDraw() {
        return pointToDraw;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    // Resetting line coordinates

    public void setShapesToZero() {
        initialPoint = new Point(0, 0);
        pointToDraw = new Point(0, 0);
        finalPoint = new Point(0, 0);
        currentLine = new Line2D.Double(0, 0, 0, 0);
    }

    @Override
    protected void updateDrawableShape(double compWidth, double compHeight) {
    }


    // Drawing the line on the ImagePanel

    @Override
    protected void paintShape(Graphics2D g2) {
        if (initialPoint != null) {
            // Draw a line on top of the image
            g2.setXORMode(Color.white); // Color of line varies depending on image colors
            g2.drawLine(
                (int) getInitialPoint().getX(),
                (int) getInitialPoint().getY(),
                (int) getPointToDraw().getX(),
                (int) getPointToDraw().getY()
            );
        }
    }
}
