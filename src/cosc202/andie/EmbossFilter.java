package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.Color;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a convoloution.
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
public class EmbossFilter implements ImageOperation, java.io.Serializable {
    
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;
    private String type;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    EmbossFilter(String type) {
        this.type = type;  
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    EmbossFilter() {
        this("Center Left");
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float [] array = { 0 , 0, 0 ,
            0, 0, 0,
             0 , 0, 0 };

        if (type == "Center Left") {
            array[3] = 1;
            array[5] = -1;
        } else if(type == "Top Left") {
            array[0] = 1;
            array[8] = -1;
        } else if(type == "Top Center") {
            array[1] = 1;
            array[7] = -1;
        } else if(type == "Top Right") {
            array[2] = 1;
            array[6] = -1;
        } else if(type == "Center Right") {
            array[5] = 1;
            array[3] = -1;
        } else if(type == "Bottom Right") {
            array[8] = 1;
            array[0] = -1;
        } else if(type == "Bottom Center") {
            array[7] = 1;
            array[1] = -1;
        } else if(type == "Bottom Left") {
            array[6] = 1;
            array[2] = -1;
        } else if(type == "Sobel Horizontal") {
            array[0] = -1/2;
            array[2] = 1/2;
            array[3] = -1;
            array[5] = 1;
            array[6] = -1/2;
            array[8] = 1/2;
        } else if(type == "Sobel Vertical") {
            array[0] = -1/2;
            array[1] = -1;
            array[2] = -1/2;
            array[6] = 1/2;
            array[7] = 1;
            array[8] = 1/2;
        }

        
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);

        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }



}
