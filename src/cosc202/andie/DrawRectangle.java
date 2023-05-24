package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * <p>
 * ImageOperation to draw a rectangle on the image.
 * </p>
 * 
 * <p>
 * This class uses a Rectangle object to specify the dimensions of the rectangle
 * to be drawn.
 * It also allows customization of the rectangle's outline color, fill color,
 * line size, and fill/outline options.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Liam Kerr
 * @version 3.0
 */
public class DrawRectangle implements ImageOperation, java.io.Serializable {
    /**
     * The percentage to scale the image by.
     */

    private BufferedImage output;
    private Rectangle rect;
    private Color shapeOutlineColour;
    private Color shapeFillColour;
    private float lineSize;
    private boolean shapeFill;
    private boolean shapeOutline;
    private boolean outlineEyeDropper;
    private boolean fillEyeDropper;

    /**
     * Construct a DrawRectangle object with the given rectangle, outline color,
     * fill color, line size, fill option, and outline option.
     *
     * @param r       The Rectangle object specifying the dimensions of the
     *                rectangle.
     * @param shapeOC The color of the rectangle's outline.
     * @param shapeFC The color of the rectangle's fill.
     * @param ls      The size of the line used for the outline.
     * @param sf      Whether to fill the rectangle or not.
     * @param so      Whether to draw the outline of the rectangle or not.
     * @param oed     Whether to use the eyedropper color as the outline color.
     * @param fed     Whether to use the eyedropper color as the fill color.
     */
    public DrawRectangle(Rectangle r, Color shapeOC, Color shapeFC, float ls, boolean sf, boolean so, boolean oed,
            boolean fed) {
        this.rect = r;
        this.shapeOutlineColour = shapeOC;
        this.shapeFillColour = shapeFC;
        this.lineSize = ls;
        this.shapeFill = sf;
        this.shapeOutline = so;
        this.outlineEyeDropper = oed;
        this.fillEyeDropper = fed;

    }

    /**
     * Construct a DrawRectangle object with the given rectangle.
     *
     * @param r The Rectangle object specifying the dimensions of the rectangle.
     */
    public DrawRectangle(Rectangle r) {
        this.rect = r;
    }
    // create constructor that passes through the variables

    /**
     * Apply the draw rectangle operation to the input image.
     *
     * @param input The image to apply the draw rectangle operation to.
     * @return The resulting image with the rectangle drawn.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        output = new BufferedImage(width, height, input.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics();
        g.drawImage(input, 0, 0, null);
        g.setStroke(new BasicStroke(lineSize));

        if (shapeFill) {
            if (fillEyeDropper) {
                // Use the eye dropper colour for the fill
                g.setColor(ShapeActions.eyeDropperColour);
            } else {
                // Use the specified shape fill colour
                g.setColor(shapeFillColour);
            }
            g.fillRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        }
        if (shapeOutline) {
            if (outlineEyeDropper) {
                g.setColor(ShapeActions.eyeDropperColour);
            } else {
                g.setColor(shapeOutlineColour);
            }
            // Fill the rectangle with the specified dimensions
            g.drawRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        }
        g.dispose();
        return output;
    }
}
