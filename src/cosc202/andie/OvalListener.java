package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

/**
 * The OvalListener class represents a listener for handling mouse events related to drawing ovals on an ImagePanel.
 * It extends the ShapeListener class, which is an abstract class for the listener class of each shape, and provides
 * functionality for drawing and interacting with oval shapes.
 *
 * <p>
 * The initial code for this class was adapted from the Oracle documentation on mouse motion listeners for Swing events.
 * </p>
 *
 * <p>
 * This class tracks the initial, current, and drawable oval shapes using instances of Ellipse2D. It provides methods
 * to update the size of the oval based on mouse dragging, set the initial position of the oval on mouse press, and
 * apply the drawn oval on mouse release. The appearance of the oval can be customized with the shape outline color,
 * shape fill color, line width, shape fill option, and shape outline option.
 * </p>
 *
 * <p>
 * This class contains overridden methods from the MouseInputAdapter class to handle mouse events such as mouse dragging,
 * mouse pressing, and mouse releasing. It also includes helper methods to update the size and position of the oval,
 * as well as getters and setters for the oval shapes. The oval drawing operation is applied to the image on mouse release.
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

public class OvalListener extends ShapeListener {

    // Variables to store the initial, current, and drawable oval shapes
    private Ellipse2D initialOval = new Ellipse2D.Double(0, 0, 0, 0);
    private Ellipse2D ovalToDraw = new Ellipse2D.Double(0, 0, 0, 0);
    private Ellipse2D currentOval = new Ellipse2D.Double(0, 0, 0, 0);

    // Variables to store shape properties
    private Color shapeOutlineColour;
    private Color shapeFillColour;
    private float lineWidth;
    private boolean shapeFill;
    private boolean shapeOutline;
    private boolean outlineEyeDropper;
    private boolean fillEyeDropper;

    /**
     * Constructs an OvalListener object with the specified shape properties.
     * @param target the ImagePanel to draw on
     * @param shapeOutlineColour the color for the outline of the oval
     * @param shapeFillColour the color to fill the oval
     * @param lineWidth the width of the outline
     * @param shapeFill whether to fill the oval or not
     * @param shapeOutline whether to draw the outline of the oval or not
     */
    public OvalListener(ImagePanel target, Color shapeOutlineColour, Color shapeFillColour, float lineWidth, boolean shapeFill, boolean shapeOutline, boolean outlineEyeDropper, boolean fillEyeDropper) {
        super(target);
        this.shapeOutlineColour = shapeOutlineColour;
        this.shapeFillColour = shapeFillColour;
        this.lineWidth = lineWidth;
        this.shapeFill = shapeFill;
        this.shapeOutline = shapeOutline;
        this.outlineEyeDropper = outlineEyeDropper;
        this.fillEyeDropper = fillEyeDropper;
    }

    /**
     * Constructs an OvalListener object with default shape properties.
     * @param target the ImagePanel to draw on
     */
    public OvalListener(ImagePanel target) {
        super(target);
    }

    // MouseListener methods

    /**
     * Not implemented for oval drawing.
     */
    public void mouseClicked(MouseEvent e) {
        // Not implemented for oval drawing
    }

    /**
     * Handles the mouse being dragged to update the size of the oval.
     */
    public void mouseDragged(MouseEvent e) {
        updateSize(e);
    }

    /**
     * Not implemented for oval drawing.
     */
    public void mouseEntered(MouseEvent e) {
        // Not implemented for oval drawing
    }

    /**
     * Not implemented for oval drawing.
     */
    public void mouseExited(MouseEvent e) {
        // Not implemented for oval drawing
    }

    /**
     * Not implemented for oval drawing.
     */
    public void mouseMoved(MouseEvent e) {
        // Not implemented for oval drawing
    }

    /**
     * Handles the mouse being pressed to set the initial position of the oval.
     */
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialOval = new Ellipse2D.Double(x, y, 0, 0);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    /**
     * Handles the mouse being released to apply the drawn oval and reset the shapes.
     */
    public void mouseReleased(MouseEvent e) {
        currentOval.setFrame(ovalToDraw.getBounds());
        target.getImage().apply(new DrawOval(currentOval, shapeOutlineColour, shapeFillColour, lineWidth, shapeFill, shapeOutline,outlineEyeDropper,fillEyeDropper));
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }

    /**
     * Updates the size of the oval based on the mouse movement.
     */
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialOval.setFrame(initialOval.getX(), initialOval.getY(), x - initialOval.getX(), y - initialOval.getY());
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
        currentOval.setFrame(ovalToDraw.getBounds());
    }

    /**
     * Updates the drawable shape based on the specified component width and height.
     * @param compWidth the width of the component
     * @param compHeight the height of the component
     */
    protected void updateDrawableShape(double compWidth, double compHeight) {
        double x = initialOval.getX();
        double y = initialOval.getY();
        double width = initialOval.getWidth();
        double height = initialOval.getHeight();

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

        // The oval shouldn't extend past the drawing area.
        if ((x + width) > compWidth) {
            width = compWidth - x;
        }
        if ((y + height) > compHeight) {
            height = compHeight - y;
        }

        ovalToDraw.setFrame(x, y, width, height);
    }

    /**
     * Gets the current oval shape.
     * @return the current oval shape
     */
    public Ellipse2D getCurrentOval() {
        return currentOval;
    }

    /**
     * Gets the initial oval shape.
     * @return the initial oval shape
     */
    public Ellipse2D getInitialOval() {
        return initialOval;
    }

    /**
     * Gets the drawable oval shape.
     * @return the drawable oval shape
     */
    public Ellipse2D getOvalToDraw() {
        return ovalToDraw;
    }

    /**
     * Resets the oval shapes by setting them to zero.
     */
    public void setShapesToZero() {
        currentOval = new Ellipse2D.Double(0, 0, 0, 0);
        ovalToDraw = new Ellipse2D.Double(0, 0, 0, 0);
        initialOval = new Ellipse2D.Double(0, 0, 0, 0);
    }

    /**
     * Paints the oval shape on the graphics object.
     * @param g2 the Graphics2D object to paint on
     */
    protected void paintShape(Graphics2D g2) {
        if (initialOval != null) {
            // Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); // Color of line varies depending on image colors
            g2.drawOval((int) getOvalToDraw().getX(), (int) getOvalToDraw().getY(), (int) getOvalToDraw().getWidth() - 1, (int) getOvalToDraw().getHeight() - 1);
        }
    }
}
