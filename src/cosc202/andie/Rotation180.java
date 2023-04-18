package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

/**
 * <p>
 * ImageOperation to rotate the image by 180 degrees.
 * </p>
 * 
 * <p>
 * The image produced by this operation will rotate the BufferedImage by 180 degrees
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

public class Rotation180 implements ImageOperation, java.io.Serializable  {

    Rotation180(){}

     /**
     * <p>
     * Creates a new BufferedImage which is "transform"'d. This transform translates the image
     * and also rotates it by 180 degrees(Math.PI). 
     * </p>
     * 
     * @param input The image to apply the rotation to.
     * @return The resulting rotated (180 degrees) image.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage rotated180 = new BufferedImage(width,height,input.TYPE_INT_ARGB);
        Graphics2D graphic = rotated180.createGraphics();
        AffineTransform transform = new AffineTransform();

        transform.translate(width,height);
        transform.rotate(Math.PI);
        
        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();

        return rotated180; 
    }

}