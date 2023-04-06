package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FlipVertical implements ImageOperation, java.io.Serializable{

    FlipVertical(){
    
    
    }
    public BufferedImage apply(BufferedImage input){
        
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage FlippedVertical = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        for(int x = 0; x<height;x++){
            for(int y = 0; y<width;y++){
                int rgb = input.getRGB(y,x);
                FlippedVertical.setRGB(y,height-x-1,rgb);
            }
        }
    return FlippedVertical;
    }

}