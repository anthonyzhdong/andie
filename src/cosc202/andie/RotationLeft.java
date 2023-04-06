package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class RotationLeft implements ImageOperation, java.io.Serializable  {

    RotationLeft(){
        
    
    }
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage rotatedToLeft = new BufferedImage(height,width,input.TYPE_INT_ARGB);
        //BufferedImage rotatedToLeft = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        Graphics2D graphic = rotatedToLeft.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.translate(0,width);
        transform.rotate(Math.PI/2*3);
       //transform.translate(width,height);
      // transform.rotate(Math.PI);
        
        graphic.setTransform(transform);
        graphic.drawImage(input,0,0,null);
        graphic.dispose();

        return rotatedToLeft; 
    }

}