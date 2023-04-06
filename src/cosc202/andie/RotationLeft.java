package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

/**
 * <p>
 * ImageOperation to rotate the image to the left by 90 degrees.
 * </p>
 * 
 * <p>
 * This method transforms the image to be rotated 90 degrees to the left.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */

public class RotationLeft implements ImageOperation, java.io.Serializable  {

    RotationLeft(){}

    /**
     * <p>
     * Creates a new BufferedImage which is "transform"'d. This transform translates the image
     * and also rotates it by 270 degrees(Math.PI/2 = 90 so Math.PI/2*3 = 270 degrees). 
     * </p>
     * 
     * 
     * @param input The image to apply the rotation to.
     * @return The resulting rotated (270 degree) image.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage rotatedToLeft = new BufferedImage(height,width,input.TYPE_INT_ARGB);
        Graphics2D graphic = rotatedToLeft.createGraphics();
        AffineTransform transform = new AffineTransform();

        transform.translate(0,width);
        transform.rotate(Math.PI/2*3);

        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();

        return rotatedToLeft; 
    }

}