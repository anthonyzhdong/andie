package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class RotationRight implements ImageOperation, java.io.Serializable  {

    RotationRight(){
        
    
    }
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage rotatedToRight = new BufferedImage(height,width,input.TYPE_INT_ARGB);
        Graphics2D graphic = rotatedToRight.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.translate(height,0);
        transform.rotate(Math.PI/2);
        
        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();
        
        return rotatedToRight; 
    }

}