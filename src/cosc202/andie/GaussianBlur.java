package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.lang.Math.*;

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
public class GaussianBlur implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

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
    GaussianBlur(int radius) {
        this.radius = radius;    
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
    GaussianBlur() {
        this(1);
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
        float oInt = (float) 0.3333 * radius;
        int x = 0 - radius;
        int y = 0 - radius;
        int squares = radius *2 + 1;
        int counting = 0;
        float total = 0;

        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];

        // Go over each array
        for (int e1 = 0; e1 < array.length; e1++) {
            // Apply the equation to that array number
            float test2 = (float) Math.pow(oInt, 2); //working
            float test3 = (float)(Math.pow(x, 2) + (float)Math.pow(y,2));
            float test = (test3)/(2 * test2); 

            float pow = (float) Math.pow(Math.E, -test);
            float equ2 = 2*(float)Math.PI*(float) Math.pow(oInt, 2);
            float equ = 1/(equ2)*pow;
            
            

            //Convert to float
            float equFull = (float) equ;

            //Change array number
            array[e1] = equFull;

            // Add each total together
            total = total + equFull;

            //Update X, Y
            counting++;
            x++;
            if (counting == squares) {
                counting = 0;
                x = 0 - radius;
                y++;
            }
            
        }

        //Then later divide each by the total
        for (int e2 = 0; e2 < array.length; e2++) {
            array[e2] = array[e2] / total;
        }
        

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        return output;
    }


}
