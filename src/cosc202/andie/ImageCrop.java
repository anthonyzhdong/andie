package cosc202.andie;

import java.awt.*;

import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.event.*;

public class ImageCrop implements ImageOperation, java.io.Serializable {
    public ImageCrop() {}

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


        ImagePanel ip = ImageAction.getTarget();
        JDialog jd = new JDialog((Frame)(ip.getParent().getParent().getParent().getParent().getParent().getParent()), false);
        jd.setLocationRelativeTo(ip);
        jd.setTitle("Please select an area to crop");
        jd.setVisible(true);
        Rectangle selectedArea = ImageAction.getTarget().getCurrentRectangle();
        BufferedImage output = new BufferedImage((int)selectedArea.getWidth(), (int)selectedArea.getHeight(), 2);
        int outputX = 0;
        for(int x = (int)selectedArea.getX(); x < (int)(selectedArea.getX() + selectedArea.getWidth()); x++) {
            int outputY = 0;
            for(int y = (int)selectedArea.getY(); y < (int)(selectedArea.getY() + selectedArea.getHeight()); y++) {
                output.setRGB(outputX, outputY, input.getRGB(x, y));
                outputY++;
            }
            outputX++;
        }
        //ip.setCurrentRectangleToNull();
        return output;
    }

}
