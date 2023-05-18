package cosc202.andie;

import java.awt.*;
import java.awt.geom.Ellipse2D;
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
public class DrawOval implements ImageOperation, java.io.Serializable  {

     private BufferedImage output;
     private Ellipse2D ellipse2d;
     private Color c;
     private float lineWidth = 1;
     private boolean filled = true;
 
     public DrawOval(Ellipse2D ellipse2d, Color c, float lineWidth, boolean filled) {
         this.ellipse2d = ellipse2d;
         this.c = c;
         this.lineWidth = lineWidth;
         this.filled = filled;
     }

     public DrawOval(Ellipse2D ellipse2d) {
        this.ellipse2d = ellipse2d;
        this.c = Color.white;
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

        Graphics2D g = output.createGraphics();
        g.drawImage(input,0,0,null);
        g.setColor(c);
        // to change thickness
        g.setStroke(new BasicStroke(lineWidth));
        //fills rectangle w colour above
        if(filled) g.fillOval((int)ellipse2d.getX(),(int)ellipse2d.getY(),(int)ellipse2d.getWidth(),(int)ellipse2d.getHeight());
        else g.drawOval((int)ellipse2d.getX(),(int)ellipse2d.getY(),(int)ellipse2d.getWidth(),(int)ellipse2d.getHeight());
        g.dispose();
        return output;
    }
    }
    

