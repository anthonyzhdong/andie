package cosc202.andie;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.LineEvent;
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
public class DrawLine implements ImageOperation, java.io.Serializable  {
    /**
     * The percentage to scale the image by.
     */

     private BufferedImage output;
     private Line2D line2d;
     private Color c;
     private float lineWidth = 1;
 
     public DrawLine(Line2D line2d) {
         this.line2d = line2d;
         this.c = Color.white;
     }

     public DrawLine(Line2D line2d, Color c, float lineWidth) {
        this.line2d = line2d;
        this.c = c;
        this.lineWidth = lineWidth;
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
        //g.setStroke(new BasicStroke(THICKNESSVARIABLE));
        g.setStroke(new BasicStroke(lineWidth));
        //fills rectangle w colour above
       // g.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
        //shows outline
        g.drawLine((int)line2d.getX1(), (int)line2d.getY1(), (int)line2d.getX2(), (int)line2d.getY2());
        g.dispose();
        return output;
    }
    }
    

