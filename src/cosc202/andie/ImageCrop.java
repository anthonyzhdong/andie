package cosc202.andie;

import java.awt.*;

import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.event.*;

public class ImageCrop implements ImageOperation, java.io.Serializable {

    private BufferedImage output;
    private Rectangle toChange;

    public ImageCrop(Rectangle r) {
        toChange = r;
    }


    /**
     * <p>
     * Creates a new BufferedImage which is "transform"'d. This transform translates the image
     * and also rotates it by 270 degrees(Math.PI/2 = 90 so Math.PI/2*3 = 270 degrees). 
     * </p>
     * 
     * 
     * @param input The image to apply the rotation to.
     * @return The resulting rotated (270 degree) image.
     */
    public BufferedImage apply(BufferedImage input){
        output = new BufferedImage((int)toChange.getWidth(), (int)toChange.getHeight(), 2);
        int outputX = 0;
        for(int x = (int)toChange.getX(); x < (int)(toChange.getX() + toChange.getWidth()); x++) {
            int outputY = 0;
            for(int y = (int)toChange.getY(); y < (int)(toChange.getY() + toChange.getHeight()); y++) {
                output.setRGB(outputX, outputY, input.getRGB(x, y));
                outputY++;
            }
            outputX++;
        }
        //original.setCurrentRectangleToNull();


        return output;
    }

}
