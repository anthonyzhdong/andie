package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

/**
 * <p>
 * ImageOperation to rotate the image to the right by 90 degrees.
 * </p>
 * 
 * <p>
 * This method transforms the image to be rotated 90 degrees to the right.
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

public class RotationRight implements ImageOperation, java.io.Serializable  {

    RotationRight(){}
     /**
     * <p>
     * Creates a new BufferedImage which is "transform"'d. This transform translates the image
     * and also rotates it by 90 degrees(Math.PI = 180 so Math.PI/2 = 90 degrees). 
     * </p>
     * 
     * 
     * @param input The image to apply the rotation to.
     * @return The resulting rotated (90 degrees) image.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage rotatedToRight = new BufferedImage(height,width,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = rotatedToRight.createGraphics();
        AffineTransform transform = new AffineTransform();

        transform.translate(height,0);
        transform.rotate(Math.PI/2);
        
        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();
        
        return rotatedToRight; 
    }

}