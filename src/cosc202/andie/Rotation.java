package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Rotation implements ImageOperation, java.io.Serializable  {
    Rotation () {}


    /**
     * <p>
     * Rotate an image by 90* at a time
     * </p>
     * 
     * 
     * @param input The image to apply the rotation to.
     * @return The resulting (rotated) image.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage rotatedImage = new BufferedImage(height,width,input.TYPE_INT_ARGB);
        Graphics2D graphic = rotatedImage.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.translate(height,0);
        transform.rotate(Math.PI/2);
        
        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();
        
        return rotatedImage; 
    }

}