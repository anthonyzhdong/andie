package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;



public class ResizeLarger implements ImageOperation, java.io.Serializable  {
    /**
     * The percentage to scale the image by.
     */
    private double scale;

    ResizeLarger(int scale){
        if(scale == 0){this.scale = 1;} 
        else {this.scale = scale;}
    }

    ResizeLarger() {this.scale = 1;}

    /**
     * <p>
     * Resize an image to be larger
     * </p>
     * 
     * 
     * @param input The image to apply the resizing to.
     * @return The resulting (resized) image.
     */
    public BufferedImage apply(BufferedImage input){
        double scaleNum = scale/100;

        int newWidth = (int)(input.getWidth() * scaleNum);
        int newHeight = (int)(input.getHeight() * scaleNum);

        BufferedImage image = new BufferedImage(newWidth,newHeight,input.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(input,0,0,newWidth,newHeight,null);
        g.dispose();

        return image;
    }
    }
    

