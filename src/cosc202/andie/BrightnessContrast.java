package cosc202.andie;
import java.awt.image.*;
import java.awt.Color;

/**
 * <p>
 * ImageOperation to alter a given image's brightness and contrast.
 * </p>
 * 
 * <p>
 * This method modifies the specific RGB values of each pixel by a percentage 
 * value using a given formula. The value is passed in from the ColourActions 
 * class by a end-user of this system.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Liam Kerr
 * @version 2.0
 */
public class BrightnessContrast implements ImageOperation, java.io.Serializable {

    private int brightnessDegree;
    private int contrastDegree;

    /**
     * <p>
     * Create a new BrightnessContrast operation. 
     * </p>
     * 
     * @param brightnessDegree //percentage brightness change
     * @param contrastDegree //percentage contrast change
     * 
     */
    BrightnessContrast(int brightnessDegree, int contrastDegree) {
        this.brightnessDegree = brightnessDegree;
        this.contrastDegree = contrastDegree;
    }
    //Default Constructor
    BrightnessContrast() {
        this.brightnessDegree = 0;
        this.contrastDegree = 0;
    }
    /** 
     * Implementation of apply method from ImageOperation interface
     * Method to alter an images brightness + contrast
     * <p>
     * Creates copy in of passed in BufferedImage, iterates through every pixel in the image, 
     * updating each with a percent change in the red, green and blue values using 
     * an equation, and leaving the alpha value as is. Return the copy with the updated 
     * rgb values. 
     * </p>
     * 
     * @param input The image to have its brightness/contrast altered
     * @return The resulting altered image.
     * 
     */
    public BufferedImage apply(BufferedImage input) {
        int brightnessValue = brightnessDegree;
        int contrastValue = contrastDegree;
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for(int i = 0; i < input.getWidth(); i++) {
            for(int j = 0; j < input.getHeight(); j++) {

                

                Color c = new Color(output.getRGB(i, j), true);
                
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int a = c.getAlpha();

                if(a != 0) {
                    r = Truncate((int)((1 + contrastValue / 100.0) * (r - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                    g = Truncate((int)((1 + contrastValue / 100.0) * (g - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                    b = Truncate((int)((1 + contrastValue / 100.0) * (b - 127.5) + (127.5 * (1 + brightnessValue / 100.0))));
                    c = new Color(r, g, b, a);

                    output.setRGB(i, j, c.getRGB());
                }
                
                /* Old code, may be useful in future
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

        /** 
         * Truncates a int to between 0 and 255
         * <p> 
         * Takes a int parameter and checks it to see if it between
         * 0 and 255. If within range, return value unchanged. If 
         * greater than 255, set it to 255 and return. If less than
         * 0, set value to 0 and return.
         * </p>
         * 
         * @param value value to be checked
         * @return checked value
        */

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