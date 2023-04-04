package cosc202.andie;
import java.awt.image.*;
import java.awt.Color;
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
        int brightnessValue = brightnessDegree;
        int contrastValue = contrastDegree;
        System.out.println(input.getColorModel().toString());
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for(int i = 0; i < input.getWidth(); i++) {
            for(int j = 0; j < input.getHeight(); j++) {

                

                Color c = new Color(output.getRGB(i, j), true);
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                
                int a = c.getAlpha();
                
                if(i == 0 && j == 0) {
                    System.out.println(a);
                }
                if(a != -1) {
                    r = Truncate((int)((1 + contrastValue / 100.0) * (r - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                    g = Truncate((int)((1 + contrastValue / 100.0) * (g - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                    b = Truncate((int)((1 + contrastValue / 100.0) * (b - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                    
                    c = new Color(r, g, b, a);

                    output.setRGB(i, j, c.getRGB());
                }
                
                /* 
                rgb = output.getRaster().getPixel(i, j, new int[3]);

                int red = rgb[0];
                int green = rgb[1];
                int blue = rgb[2];

                red = Truncate((int)((1 + contrastValue / 100.0) * (red - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                green = Truncate((int)((1 + contrastValue / 100.0) * (green - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                blue = Truncate((int)((1 + contrastValue / 100.0) * (blue - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                int arr[] = {red, green, blue};
                output.getRaster().setPixel(i, j, arr);
                */
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