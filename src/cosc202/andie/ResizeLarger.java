package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to change the size of the image.
 * </p>
 * 
 * <p>
 * This method uses a scale to create the dimensions for the new (resized) image.
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
public class ResizeLarger implements ImageOperation, java.io.Serializable  {
    /**
     * The percentage to scale the image by.
     */
    private double scale;

    ResizeLarger(int scale){
        if(scale == 0){this.scale = 1;} 
        else {this.scale = scale;}
    }

    ResizeLarger() {this.scale = 1;}

    /**
     * <p>
     * Calculates the new dimensions by multiplying the initial dimensions by the scale,
     * then creates a new buffered image with new given dimensions.
     * </p>
     * 
     * 
     * @param input The image to apply the resizing to.
     * @return The resulting (resized) image.
     */
    public BufferedImage apply(BufferedImage input){
        double scaleNum = scale/100;

        int newWidth = (int)(input.getWidth() * scaleNum);
        int newHeight = (int)(input.getHeight() * scaleNum);

        BufferedImage image = new BufferedImage(newWidth,newHeight,input.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(input,0,0,newWidth,newHeight,null);
        g.dispose();

        return image;
    }
    }
    

