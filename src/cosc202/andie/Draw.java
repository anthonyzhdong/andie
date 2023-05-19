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
public class Draw implements ImageOperation, java.io.Serializable  {
    /**
     * The percentage to scale the image by.
     */

     private BufferedImage output;
     private Point p;
     private Color c;
     private float lineWidth = 1;
     private int radius = 20;
 
     public Draw(Point p) {
         this.p = p;
         this.c = Color.red;
     }

     public Draw(Point p, Color c, int radius) {
        this.p = p;
        this.c = c;
        this.radius = radius;
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
            
            Ellipse2D e2d = new Ellipse2D.Double(adjustPoint(p.getX() - radius), adjustPoint(p.getY() - radius), 2*(double)radius, 2*(double)radius);
            Graphics2D g2 = output.createGraphics();
            g2.drawImage(input,0,0,null);
            g2.drawOval((int)e2d.getCenterX(), (int)e2d.getCenterY(), (int)e2d.getWidth(), (int)e2d.getHeight());
            g2.dispose();
            return output;
        }

        private double adjustPoint(double value) {
            if(value > input.getWidth-1);
            if(value > input.getHeight-1);
            if(value < )
        }
    }
    

