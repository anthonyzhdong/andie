package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.lang.Math.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian blur to an image.
 * </p>
 * 
 * <p>
 * A Gaussian blur blurs an image by convolving each pixel with a Gaussian kernel.
 * The blur effect is stronger with larger radii.
 * </p>
 * 
 * Comments written by chat gpt
 * 
 * @see java.awt.image.ConvolveOp
 * @see java.awt.image.Kernel
 * @see cosc202.andie.ImageOperation
 * @author Steven Mills
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of the filter to apply. A radius of 1 corresponds to a 3x3 filter,
     * a radius of 2 corresponds to a 5x5 filter, and so on.
     */
    private int radius;
    private boolean sN = false;

    /**
     * Constructs a Gaussian blur with the given radius.
     * 
     * @param radius the radius of the Gaussian blur
     */
    GaussianBlur(int radius) {
        this.radius = radius;    
    }

    /**
     * Constructs a Gaussian blur with the default radius of 1.
     * By default, a 3x3 Gaussian filter is applied.
     */
    GaussianBlur() {
        this(1);
    }


    /**
     * Constructs a Gaussian blur with the given radius and shiftNegative flag.
     * 
     * @param radius the radius of the Gaussian blur
     * @param sN     the shiftNegative flag indicating whether to perform negative shifting
     */
    GaussianBlur(int radius, boolean t) {
        this.radius = radius;  
        this.sN = t;
    }

    /**
     * Apply a Gaussian blur to an image.
     * 
     * As with many filters, the Gaussian blur is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to a stronger Gaussian blur effect.
     * 
     * @param input the image to apply the Gaussian blur to
     * @return the resulting blurred image
     */
    public BufferedImage apply(BufferedImage input) {

        if (sN == true) {input = shiftNegative.fixNegative(input);}
        // Set the values for use
        float oInt = (float) 0.3333 * radius;
        int x = 0 - radius;
        int y = 0 - radius;
        int squares = radius*2 + 1;
        int counting = 0;
        float total = 0;

        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];

        // Go over each array
        for (int e1 = 0; e1 < array.length; e1++) {
            // Apply the equation to that array number
            float test2 = (float) Math.pow(oInt, 2); 
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
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        return output;
    }


}
