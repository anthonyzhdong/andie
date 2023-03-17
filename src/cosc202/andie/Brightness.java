package cosc202.andie;
import java.awt.image.*;

public class Brightness implements ImageOperation, java.io.Serializable {

    private int degree;

    Brightness(int degree) {
    // Any construction code goes here
        this.degree = degree;
    }

    Brightness() {
        this.degree = 0;
    }

    public BufferedImage apply (BufferedImage input) {
        /*// The values for the kernel as a 1-element 1 dimensional array
        float [] array = {degree};
        // Make a 1x1 filter from the array
        Kernel kernel = new Kernel(1, 1, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done */
        int[] rgb;
        int brightnessValue = degree;
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for(int i = 0; i < input.getWidth(); i++) {
            for(int j = 0; j < input.getHeight(); j++) {
                rgb = output.getRaster().getPixel(i, j, new int[3]);

                int red = Truncate(rgb[0] + brightnessValue);
                int green = Truncate(rgb[1] + brightnessValue);
                int blue = Truncate(rgb[2] + brightnessValue);

                int arr[] = {red, green, blue};

                output.getRaster().setPixel(i, j, arr);
            }
        }

        return output;
        }

    public static int Truncate(int value) {
        if (value < 0) {
            value = 0;
        }
        else if (value > 255) {
            value = 255;
        }
        return value;
    }

    

        
}