package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip the image horizontally.
 * </p>
 * 
 * <p>
 * This method flips the pixels horizontally and saves it to the new image
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

public class FlipHorizontal implements ImageOperation, java.io.Serializable{

    FlipHorizontal(){}

     /**
     * <p>
     * Creates a new BufferedImage copy, iterates through each pixel in the original image
     * collecting its colour value then setting that colour value to the corresponding pixel
     * in the new copy but with the x coordinate flipped.
     * </p>
     * 
     * 
     * @param input The image to apply the flip to.
     * @return The image which is flipped horizontally
     */
    public BufferedImage apply(BufferedImage input){
        
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage FlippedHorizontal = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        for(int x = 0; x<width;x++){
            for(int y = 0; y<height;y++){
                int rgb = input.getRGB(x,y);
                FlippedHorizontal.setRGB(width-x-1,y,rgb);
            }
        }
    return FlippedHorizontal;
    }

}
