package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flip implements ImageOperation, java.io.Serializable{

    Flip(){
    
    
    }
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage FlippedImage = new BufferedImage(height,width,input.getType());
        for(int x = 0; x<height;x++){
            for(int l = 0,r=width-1;l<width;l++,r--){
                int s = input.getRGB(l,x);
                FlippedImage.setRGB(r,x,s);
            }

        }

    return FlippedImage;
    }

}
