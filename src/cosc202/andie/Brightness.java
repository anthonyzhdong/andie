package cosc202.andie;
import java.awt.image.*;

public class Brightness implements ImageOperation, java.io.Serializable {

    private float degree;

    Brightness(float degree) {
    // Any construction code goes here
        this.degree = degree;
    }

    Brightness() {
        this.degree = 1;
    }

    public BufferedImage apply (BufferedImage input) {
        // The values for the kernel as a 1-element 1 dimensional array
        float [] array = {degree};
        // Make a 1x1 filter from the array
        Kernel kernel = new Kernel(1, 1, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done
        return output;
        }

        
}