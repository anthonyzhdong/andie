package cosc202.andie;
import java.awt.image.*;
import java.awt.image.ConvolveOp;

public class Sharpen implements ImageOperation, java.io.Serializable {
    Sharpen() {}

    /**
     * <p>
     * Sharpen an image
     * </p>
     * 
     * <p>
     * As with many filters, the gaussian blur is implemented via convolution. 
     * </p>
     * 
     * @param input The image to apply the sharpen to.
     * @return The resulting (sharpened) image.
     */
    public BufferedImage apply (BufferedImage input) {
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