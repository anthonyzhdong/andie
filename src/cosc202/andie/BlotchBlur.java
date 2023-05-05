package cosc202.andie;

import java.awt.image.*;
import java.util.Arrays;
import java.awt.Color;

/**
 * <p>
 * Credit to https://stackoverflow.com/questions/29074735/mean-and-median-image-filtering-in-java article
 * used and then modified for our particular use
 * 
 * <p>
 * ImageOperation to convert an image from colour to greyscale.
 * </p>
 * 
 * <p>
 * The images produced by this operation are still technically colour images,
 * in that they have red, green, and blue values, but each pixel has equal
 * values for red, green, and blue giving a shade of grey.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class BlotchBlur implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the 'local neighbourhood' used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    BlotchBlur(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter
     */
    BlotchBlur() {
        this(1);
    }


    /**
     * <p>
     * Apply a median filter to an image
     * </p>
     * 
     * <p>
     * Locates a specific pixel, and takes the median of the ARGB values of the set of pixels around 
     * it, using the radius to decide the size of the 'local neighbourhood'. The median ARGB value is
     * then set as the original pixels value.
     * </p>
     * 
     * @param input The image to have the median filter applied on
     * @return The resulting median filtered image.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        
        int size = (2*radius+1) * (2*radius+1);

        int[] alpha = new int[size];
        int[] red = new int[size];
        int[] green = new int[size];
        int[] blue = new int[size];
        Color c = new Color(input.getRGB(0, 0), true);
        
        for (int y = 1; y < input.getHeight(); ++y) {
            for (int x = 1; x < input.getWidth(); ++x) {
                
                int kernalIterator = 0;
                for(int kernalY = 0; kernalY < (2*radius+1); kernalY++) {
                    for(int kernalX = 0; kernalX < (2*radius+1); kernalX++) {

                        

                        if(y >= input.getHeight() - (2*radius+1) - 1 && x <= input.getWidth() - (2*radius+1) - 1) {
                            c = new Color(input.getRGB(x+kernalX-1, y-kernalY), true);
                        } 
                        else if(x >= input.getWidth() - (2*radius+1) - 1 && y <= input.getHeight() - (2*radius+1) - 1) {
                            c = new Color(input.getRGB(x-kernalX, y+kernalY-1), true);
                        } 
                        else if(x >= input.getWidth() - (2*radius+1) - 1 && y >= input.getHeight() - (2*radius+1) - 1) {
                            c = new Color(input.getRGB(x-kernalX, y-kernalY), true);
                        }
                        else {
                            c = new Color(input.getRGB(x+kernalX-1, y+kernalY-1), true);
                        }
                       
                
                        int r = c.getRed();
                        int g = c.getGreen();
                        int b = c.getBlue();
                        int a = c.getAlpha();

                        alpha[kernalIterator] = a;
                        red[kernalIterator] = r;
                        green[kernalIterator] = g;
                        blue[kernalIterator] = b;
                        kernalIterator++;
                    }
                }  
                Arrays.sort(alpha);
                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);
                Color newC = new Color((red[radius+1]), (green[radius+1]), (blue[radius+1]), (alpha[radius+1]));
                output.setRGB(x, y, newC.getRGB());
            }
        }
        return output;
    }
    
}
