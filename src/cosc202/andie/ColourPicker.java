package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to convert an image from colour to greyscale.
 * </p>
 * 
 * <p>
 * The images produced by this operation are still technically colour images,
 * in that they have red, green, and blue values, but each pixel has equal
 * values for red, green, and blue giving a shade of grey.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */

public class ColourPicker implements ImageOperation, java.io.Serializable {
    
    /**
     * <p>
     * Create a new CovertToGrey operation.
     * </p>
     */
    ColourPicker(){}
    

    /**
     * <p>
     * Apply greyscale conversion to an image.
     * </p>
     * 
     * <p>
     * The conversion from red, green, and blue values to greyscale uses a 
     * weighted average that reflects the human visual system's sensitivity 
     * to different wavelengths -- we are most sensitive to green light and 
     * least to blue.
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {
    
        return input;
    }
    
}
