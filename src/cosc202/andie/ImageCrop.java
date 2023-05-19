package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        Rectangle selectedArea = ImageAction.getTarget().getinitialRectangle();
        BufferedImage output = new BufferedImage((int)selectedArea.getWidth(), (int)selectedArea.getHeight(), 2);
        for(int x = (int)selectedArea.getX(); x < (int)(selectedArea.getX() + selectedArea.getWidth()); x++) {
            for(int y = (int)selectedArea.getY(); y < (int)(selectedArea.getY() + selectedArea.getHeight()); y++) {
                output.setRGB(x, y, input.getRGB(x, y));
            }
        }
        return output;
    }
}
