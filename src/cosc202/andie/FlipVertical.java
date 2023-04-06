package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip the image vertically.
 * </p>
 * 
 * <p>
 * This method flips the pixels vertically and saves it to the new image
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

public class FlipVertical implements ImageOperation, java.io.Serializable{

    FlipVertical(){}

     /**
     * <p>
     * Creates a new BufferedImage copy, iterates through each pixel in the original image
     * collecting its colour value then setting that colour value to the corresponding pixel
     * in the new copy but with the y coordinate flipped.
     * </p>
     * 
     * 
     * @param input The image to apply the flip to.
     * @return The image which is flipped vertically
     */
    public BufferedImage apply(BufferedImage input){
        
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage FlippedVertical = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        for(int x = 0; x<height;x++){
            for(int y = 0; y<width;y++){
                int rgb = input.getRGB(y,x);
                FlippedVertical.setRGB(y,height-x-1,rgb);
            }
        }
    return FlippedVertical;
    }

}