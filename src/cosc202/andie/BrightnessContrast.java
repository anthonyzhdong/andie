package cosc202.andie;
import java.awt.image.*;

public class BrightnessContrast implements ImageOperation, java.io.Serializable {

    private int brightnessDegree;
    private int contrastDegree;

    BrightnessContrast(int brightnessDegree, int contrastDegree) {
    // Any construction code goes here
        this.brightnessDegree = brightnessDegree;
        this.contrastDegree = contrastDegree;
    }

    BrightnessContrast() {
        this.brightnessDegree = 0;
        this.contrastDegree = 0;
    }

    public BufferedImage apply (BufferedImage input) {
        int[] rgb;
        int brightnessValue = brightnessDegree;
        int contrastValue = contrastDegree;
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for(int i = 0; i < input.getWidth(); i++) {
            for(int j = 0; j < input.getHeight(); j++) {
                rgb = output.getRaster().getPixel(i, j, new int[3]);

                double red = rgb[0];
                double green = rgb[1];
                double blue = rgb[2];

                red = ((1 + contrastValue / 100.0) * (red - 127.5) + (127.5 * (1 + brightnessValue / 100.0)));
                green = ((1 + contrastValue / 100.0) * (green - 127.5) + (127.5 * (1 + brightnessValue / 100.0)));
                blue = ((1 + contrastValue / 100.0) * (blue - 127.5) + (127.5 * (1 + brightnessValue / 100.0)));
                if(i == 0 && j == 0){
                    System.out.println(red);
                }
                double arr[] = {red, green, blue};

                output.getRaster().setPixel(i, j, arr);
            }
        }

        return output;
        }
        
}