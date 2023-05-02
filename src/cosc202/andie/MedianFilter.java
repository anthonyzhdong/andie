package cosc202.andie;

import java.awt.image.*;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Color;
import java.util.ArrayList;
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
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private Integer radius;

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
    MedianFilter(Integer radius) {
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
    MedianFilter() {
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
        
        //Integer size = (2*radius+1) * (2*radius+1);

        ArrayList<Integer> alpha = new ArrayList<Integer>();
        ArrayList<Integer> red = new ArrayList<Integer>();
        ArrayList<Integer> green = new ArrayList<Integer>();
        ArrayList<Integer> blue = new ArrayList<Integer>();
        Color c = new Color(input.getRGB(0, 0), true);
        
        for (Integer y = 1; y < input.getHeight(); ++y) {
            for (Integer x = 1; x < input.getWidth(); ++x) {
                
                for(Integer kernalY = -(2*radius+1)/2; kernalY < (2*radius+1); kernalY++) {
                    for(Integer kernalX = -(2*radius+1)/2; kernalX < (2*radius+1); kernalX++) {
                        
                        if(y + kernalY <= 0) {
                            System.out.println(y + kernalY);
                            continue;
                        }
                        if(x + kernalX <= 0) {
                            System.out.println(x + kernalX);
                            continue;
                        }
                        if(y + kernalY > input.getHeight()) continue;
                        if(x + kernalX > input.getWidth()) continue;

                        c = new Color(input.getRGB(x+kernalX, y+kernalY), true);
                
                        Integer r = c.getRed();
                        Integer g = c.getGreen();
                        Integer b = c.getBlue();
                        Integer a = c.getAlpha();

                        alpha.add(a);
                        red.add(r);
                        green.add(g);
                        blue.add(b);
                    }
                }  
                Collections.sort(alpha);
                Collections.sort(red);
                Collections.sort(green);
                Collections.sort(blue);
                Color newC = new Color((red.get(red.size()/2)), (green.get(green.size()/2)), (blue.get(blue.size()/2)), (alpha.get(alpha.size()/2)));
                output.setRGB(x, y, newC.getRGB());
            }
        }
        return output;
    }
    
}
