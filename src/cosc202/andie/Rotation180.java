package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Rotation180 implements ImageOperation, java.io.Serializable  {

    Rotation180(){
        
    
    }
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        
        BufferedImage rotated180 = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        Graphics2D graphic = rotated180.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.translate(width,height);
        transform.rotate(Math.PI);
        
        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();

        return rotated180; 
    }

}