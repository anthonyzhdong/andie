package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * <p>
 * ImageOperation to change the size of the image.
 * </p>
 * 
 * <p>
 * This method uses a scale to create the dimensions for the new (resized) image.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class DrawRectangle implements ImageOperation, java.io.Serializable  {
    /**
     * The percentage to scale the image by.
     */

     private BufferedImage output;
     private Rectangle rect;
     private Color shapeOutlineColour;
     private Color shapeFillColour;
     private float lineSize;
     private boolean shapeFill;



     public DrawRectangle(Rectangle r, Color shapeOC, Color shapeFC, float ls, boolean sf){
        this.rect = r;
        this.shapeOutlineColour = shapeOC;
        this.shapeFillColour = shapeFC;
        this.lineSize = ls;
        this.shapeFill = sf;

     }
    // private boolean filled = true;
 
     public DrawRectangle(Rectangle r){// boolean filled) {
         this.rect = r;
        // this.filled = filled;
     }
     // create constructor that passes through the variables 

 
    /**
     * <p>
     * Calculates the new dimensions by multiplying the initial dimensions by the scale,
     * then creates a new buffered image with new given dimensions.
     * </p>
     * 
     * 
     * @param input The image to apply the resizing to.
     * @return The resulting (resized) image.
     */
    public BufferedImage apply(BufferedImage input){
         int width = input.getWidth();
         int height = input.getHeight();

         output = new BufferedImage(width,height,input.TYPE_INT_ARGB);

         Graphics2D g = output.createGraphics();
         g.drawImage(input,0,0,null);
         g.setStroke(new BasicStroke(lineSize));


         if(shapeFill){
         g.setColor(shapeFillColour);
         g.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
         }
         g.setColor(shapeOutlineColour); 
         g.drawRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
      
        g.dispose();
        return output;
    }
    }
    

