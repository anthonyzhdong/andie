package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * <p>
 * ImageOperation to change the size of the image.
 * </p>
 * 
 * <p>
 * This method uses a scale to create the dimensions for the new (resized)
 * image.
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

     /**
      * Construct a DrawRectangle object with the given rectangle, outline color, fill color, line size, fill option, and outline option.
      * 
      * @param rect The Rectangle object specifying the dimensions of the rectangle.
      * @param shapeOutlineColour The color of the rectangle's outline.
      * @param shapeFillColour The color of the rectangle's fill.
      * @param lineSize The size of the line used for the outline.
      * @param shapeFill Whether to fill the rectangle or not.
      * @param shapeOutline Whether to draw the outline of the rectangle or not.
      */
     public DrawRectangle(Rectangle rect, Color shapeOutlineColour, Color shapeFillColour, float lineSize, boolean shapeFill, boolean shapeOutline){
         this.rect = rect;
         this.shapeOutlineColour = shapeOutlineColour;
         this.shapeFillColour = shapeFillColour;
         this.lineSize = lineSize;
         this.shapeFill = shapeFill;
         this.shapeOutline = shapeOutline;
     }

     /**
      * Construct a DrawRectangle object with the given rectangle.
      * 
      * @param rect The Rectangle object specifying the dimensions of the rectangle.
      */
     public DrawRectangle(Rectangle rect) {
         this.rect = rect;
     }
    
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
            g.setColor(shapeFillColour);
            g.fillRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        }
        if (shapeOutline) {
            if (outlineEyeDropper) {
                g.setColor(ShapeActions.eyeDropperColour);
            } else {
                g.setColor(shapeOutlineColour);
            }
            g.drawRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        }
        g.dispose();
        return output;
    }
}
