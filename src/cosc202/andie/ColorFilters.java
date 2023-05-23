package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.awt.Color;

/**
 * <p>
 * ImageOperation to apply several different Color filters.
 * </p>
 * 
 * <p>
 * A color filter overlays color ontop of the image
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 *  
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class ColorFilters implements ImageOperation, java.io.Serializable {
    

    private String type;
    private boolean sN = false;

    
    /**
     * Constructs a colorFilter object with the specified type and shift negative flag.
     *
     * @param type the type of the emboss filter
     * @param t    the shift negative flag
     */
    ColorFilters(String type) {
        this.type = type;  
    }

    /**
     * Constructs an colorFilter object with the specified type and shift negative flag.
     *
     * @param type the type of the color filter
     * @param t    the shift negative flag
     */
    ColorFilters(String type, boolean t) {
        this.type = type;  
        this.sN = t;
    }

    /**
     * Constructs an colorFilter object with the default type red.
     */
    ColorFilters() {
        this("red");
    }


    /**
     * Apply the color filter to the input image.
     *
     * @param input the image to apply the color filter to
     * @return the resulting color image
     */
    public BufferedImage apply(BufferedImage input) {
        boolean patches = false;
        boolean patches1 = false;
        boolean patches2 = false;
        boolean patches3 = false;

        int count = 1;
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);
                
                if (type == "patches radius 1" || patches == true) {
                    patches = true;
                    Random re = new Random();
                    int result = re.nextInt(7);

                    if (result == 1) {type = "red";}
                    if (result == 2) {type = "blue";}
                    if (result == 3) {type = "green";}
                    if (result == 4) {type = "pink";}
                    if (result == 5) {type = "light blue";}
                    if (result == 6) {type = "yellow";}
                } else if (type == "patches radius 5" || patches1 == true) {
                    patches1 = true;
                    Random re = new Random();
                    int result = re.nextInt(7);
                    if (count == 5) {
                        if (result == 1) {type = "red";}
                        if (result == 2) {type = "blue";}
                        if (result == 3) {type = "green";}
                        if (result == 4) {type = "pink";}
                        if (result == 5) {type = "light blue";}
                        if (result == 6) {type = "yellow";}
                        count = 1;
                    }
                    count++;
                } else if (type == "patches radius 1000" || patches2 == true) {
                    patches2 = true;
                    Random re = new Random();
                    int result = re.nextInt(7);
                    if (count == 1000) {
                        if (result == 1) {type = "red";}
                        if (result == 2) {type = "blue";}
                        if (result == 3) {type = "green";}
                        if (result == 4) {type = "pink";}
                        if (result == 5) {type = "light blue";}
                        if (result == 6) {type = "yellow";}
                        count = 1;
                    }
                    count++;
                } else if (type == "patches radius 100000" || patches3 == true) {
                    patches3 = true;
                    Random re = new Random();
                    int result = re.nextInt(7);
                    if (count == 100000) {
                        if (result == 1) {type = "red";}
                        if (result == 2) {type = "blue";}
                        if (result == 3) {type = "green";}
                        if (result == 4) {type = "pink";}
                        if (result == 5) {type = "light blue";}
                        if (result == 6) {type = "yellow";}
                        count = 1;
                    }
                    count++;
                }

                if (type == "red") {
                    r = 255;
                    g = (int)g/2;
                    b = (int)b/2;

                } else if (type == "blue") {
                    r = (int)r/2;
                    g = (int)g/2;
                    b = 255;
                } else if (type == "green") {
                    r = (int)r/2;
                    g = 255;
                    b = (int)b/2;
                } else if (type == "pink") {
                    r = 255;
                    g = (int)g/2;
                    b = 255;
                } else if (type == "light blue") {
                    r = (int)r/2;
                    g = 255;
                    b = 255;
                } else if (type == "yellow") {
                    r = 255;
                    g = 255;
                    b = (int)b/2;
                } else {
                    r = 255;
                    g = (int)g/2;
                    b = (int)b/2;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }

        if (sN == true) {input = shiftNegative.fixNegative(input);}
        
        

        return input;
    }




}
