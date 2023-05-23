package cosc202.andie;
import java.awt.image.*;
import java.awt.image.ConvolveOp;


/**
 * <p>
 * ImageOperation to apply a sharpening filter.
 * </p>
 * 
 * <p>
 * The sharpening filter enhances the edges and details in an image.
 * It is implemented using convolution with a specific kernel.
 * </p>
 * 
 * Chat Gpt did the comments
 * 
 * @see java.awt.image.ConvolveOp
 * @author 
 * @version 1.0
 */
public class Sharpen implements ImageOperation, java.io.Serializable {
    private boolean sN = false;

    /**
     * Constructs a Sharpen object without applying the shiftNegative operation.
     */
    Sharpen() {}

    /**
     * Constructs a Sharpen object with the option to apply the shiftNegative operation.
     *
     * @param t A boolean flag indicating whether to apply the shiftNegative operation before sharpening.
     *          If true, the shiftNegative operation will be applied; otherwise, it will be skipped.
     */
    Sharpen(boolean t) {
        this.sN = t;
    }

     /**
     * <p>
     * Apply a sharpening filter to an image.
     * </p>
     * 
     * <p>
     * The sharpening filter enhances the edges and details in an image by convolving
     * the image with a specific 3x3 kernel. The result is a sharpened image.
     * </p>
     * 
     * @param input The image to apply the sharpening filter to.
     * @return The resulting (sharpened) image.
     */
    public BufferedImage apply (BufferedImage input) {
        if (sN == true) {input = shiftNegative.fixNegative(input);}

        // The values for the kernel as a 9-element array
        float [] array = { 0 , -1/2.0f, 0 ,
                         -1/2.0f, 3, -1/2.0f,
                          0 , -1/2.0f, 0 };

        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);

        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
        }
        
}