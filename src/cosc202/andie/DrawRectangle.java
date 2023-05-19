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
     private Color c;
     private float lineWidth = 1;
     private boolean filled = true;
 
     public DrawRectangle(Rectangle r, Color c, float lineWidth, boolean filled) {
         rect = r;
         this.c = c;
         this.lineWidth = lineWidth;
         this.filled = filled;
     }

     public DrawRectangle(Rectangle r) {
        rect = r;
        c = Color.black;
     }
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

        lineWidth = 5;
        filled = false;
        //Color(int r,int g,int b,int a) values are in range of 0-255
        
        System.out.println(ColourActions.transparencyNum);
        Graphics2D g = output.createGraphics();
        g.drawImage(input,0,0,null);
        g.setColor(c);
        // to change thickness
        //g.setStroke(new BasicStroke(THICKNESSVARIABLE));
        g.setStroke(new BasicStroke(lineWidth));
        //fills rectangle w colour above
       // g.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());

        //shows outline
        if(filled) g.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
        else g.drawRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
        g.dispose();
        return output;
    }
    }
    

