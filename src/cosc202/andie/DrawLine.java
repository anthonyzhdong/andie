package cosc202.andie;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a line on the image.
 * </p>
 * 
 * <p>
 * This class uses a Line2D object to specify the coordinates of the line to be
 * drawn.
 * It also allows customization of the line's color and size.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Liam Kerr
 * @version 3.0
 */
public class DrawLine implements ImageOperation, java.io.Serializable {
    /**
     * The line object specifying the coordinates of the line to be drawn.
     */
    private Line2D line2d;

    /**
     * The color of the line's outline.
     */
    private Color shapeOutlineColour;

    /**
     * The size of the line.
     */
    private float lineSize;

    private boolean outlineEyeDropper;

    /**
     * Construct a DrawLine object with the given line, outline color, and line
     * size.
     * 
     * @param line2d             The Line2D object specifying the coordinates of the
     *                           line.
     * @param shapeOutlineColour The color of the line's outline.
     * @param lineSize           The size of the line.
     */
    public DrawLine(Line2D line2d, Color shapeOutlineColour, float lineSize, boolean outlineEyeDropper) {
        this.line2d = line2d;
        this.shapeOutlineColour = shapeOutlineColour;
        this.lineSize = lineSize;
        this.outlineEyeDropper = outlineEyeDropper;
    }

    /**
     * Construct a DrawLine object with the given line.
     * 
     * @param line2d The Line2D object specifying the coordinates of the line.
     */
    public DrawLine(Line2D line2d) {
        this.line2d = line2d;
    }

    /**
     * Apply the draw line operation to the input image.
     * 
     * @param input The image to apply the draw line operation to.
     * @return The resulting image with the line drawn.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, input.getType());

        Graphics2D g = output.createGraphics();
        g.drawImage(input, 0, 0, null);
        g.setStroke(new BasicStroke(lineSize));
        if(outlineEyeDropper){
            g.setColor(ShapeActions.eyeDropperColour);
        }else{
        g.setColor(shapeOutlineColour); 
        }
        g.drawLine((int)line2d.getX1(), (int)line2d.getY1(), (int)line2d.getX2(), (int)line2d.getY2());
        g.dispose();

        return output;
    }
}