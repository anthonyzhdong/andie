package cosc202.andie;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw an oval on the image.
 * </p>
 * 
 * <p>
 * This class uses an Ellipse2D object to specify the dimensions of the oval to be drawn.
 * It also allows customization of the oval's outline color, fill color, line size, and fill/outline options.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Liam Kerr
 * @version 3.0
 */
public class DrawOval implements ImageOperation, java.io.Serializable  {

    private BufferedImage output;
    private Ellipse2D ellipse2d;
    private Color shapeOutlineColour;
    private Color shapeFillColour;
    private float lineSize;
    private boolean shapeFill;
    private boolean shapeOutline;
    private boolean outlineEyeDropper;
    private boolean fillEyeDropper;


    /**
     * Construct a DrawOval object with the given ellipse, outline color, fill color, line size, fill option, and outline option.
     * 
     * @param ellipse2d The Ellipse2D object specifying the dimensions of the oval.
     * @param shapeOutlineColour The color of the oval's outline.
     * @param shapeFillColour The color of the oval's fill.
     * @param lineSize The size of the line used for the outline.
     * @param shapeFill Whether to fill the oval or not.
     * @param shapeOutline Whether to draw the outline of the oval or not.
     */
    public DrawOval(Ellipse2D ellipse2d, Color shapeOC, Color shapeFC, float ls, boolean sf, boolean so, boolean oed, boolean fed){
        this.ellipse2d = ellipse2d;
        this.shapeOutlineColour = shapeOC;
        this.shapeFillColour = shapeFC;
        this.lineSize = ls;
        this.shapeFill = sf;
        this.shapeOutline = so;
        this.outlineEyeDropper = oed;
        this.fillEyeDropper = fed;
    }
 
    /**
     * Construct a DrawOval object with the given ellipse.
     * 
     * @param ellipse2d The Ellipse2D object specifying the dimensions of the oval.
     */
    public DrawOval(Ellipse2D ellipse2d) {
        this.ellipse2d = ellipse2d;
    }
    
    /**
     * Apply the draw oval operation to the input image.
     * 
     * @param input The image to apply the draw oval operation to.
     * @return The resulting image with the oval drawn.
     */
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight();

        output = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics();
        g.drawImage(input,0,0,null);
        g.setStroke(new BasicStroke(lineSize));

        if(shapeFill){
            if(fillEyeDropper){
                g.setColor(ShapeActions.eyeDropperColour);
            }else{    
            g.setColor(shapeFillColour);
            }
            g.fillOval((int)ellipse2d.getX(),(int)ellipse2d.getY(),(int)ellipse2d.getWidth(),(int)ellipse2d.getHeight());
        }
        if(shapeOutline){
            if (outlineEyeDropper) {
                g.setColor(ShapeActions.eyeDropperColour);
            } else {
                g.setColor(shapeOutlineColour);
            }
            g.drawOval((int)ellipse2d.getX(),(int)ellipse2d.getY(),(int)ellipse2d.getWidth(),(int)ellipse2d.getHeight());
        }
        
        g.dispose();
        return output;
    }
}
