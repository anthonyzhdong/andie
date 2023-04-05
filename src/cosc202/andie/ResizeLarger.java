package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;



public class ResizeLarger implements ImageOperation, java.io.Serializable  {

    private int scale;
    ResizeLarger(int scale){
        this.scale = scale;

    
    }
    ResizeLarger(){
        this.scale = 0;
    }
    public BufferedImage apply(BufferedImage input){
        scale = 2;
        int scaleNum = scale;

        int newWidth = (input.getWidth() * scaleNum);
        int newHeight = (input.getHeight() * scaleNum);
        BufferedImage image = new BufferedImage(newWidth,newHeight,input.TYPE_INT_ARGB);

        Graphics2D g = image.createGraphics();
        g.drawImage(input,0,0,newWidth,newHeight,null);
        g.dispose();

        return image;
    }

    }
    

