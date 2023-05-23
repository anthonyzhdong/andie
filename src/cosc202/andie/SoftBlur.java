package cosc202.andie;
import java.awt.image.*;


/**
 * <p>
 * ImageOperation to apply a soft blur
 * </p>
 * 
 * <p>
 * The Soft blur softly blurs the image
 * </p>
 * 
 * Chat Gpt did the comments
 * 
 * @see java.awt.image.ConvolveOp
 * @author 
 * @version 1.0
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {
    private boolean sN = false;

    /**
     * Constructs a SoftBlur object without applying the shiftNegative operation.
     */
    SoftBlur() {}


    /**
     * Constructs a SoftBlur object with the option to apply the shiftNegative operation.
     *
     * @param t A boolean flag indicating whether to apply the shiftNegative operation before sharpening.
     *          If true, the shiftNegative operation will be applied; otherwise, it will be skipped.
     */
    SoftBlur(boolean t) {
        this.sN = t;
    }

    /**
     * <p>
     * Softly blur an image
     * </p>
     * 
     * <p>
     * As with many filters, the soft blur is implemented via convolution. 
     * </p>
     * 
     * @param input The image to apply the soften blur to.
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply (BufferedImage input) {

        if (sN == true) {input = shiftNegative.fixNegative(input);}
        // The values for the kernel as a 9-element array
        float [] array = { 0 , 1/8.0f, 0 ,
                          1/8.0f, 1/2.0f, 1/8.0f,
                          0 , 1/8.0f, 0 };
        
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);

        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        
        return output;
        }
        
}