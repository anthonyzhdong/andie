package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;


/** 
 * <p>
 * Abstract class that is extended for the listener class for each shape. 
 * It itself extends the MouseInputAdapter class.
 * </p>
 * 
 * <p>
 * This class contains the abstract methods that must be overwritten from the MouseInputAdapter class.
 * It also contains variables that are better to be shared through all of its subclasses, such as the 
 * target imagePanel. It also defines methods that are used to create the on screen preview of the shape 
 * that will be drawn.
 * https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
* for imagePanel. From there it was adpated to suit ANDIE, and generate a more generic and adaptable system.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see javax.swing.event.MouseInputAdapter
 * @author Liam Kerr
 * @version 3.0
 */
public abstract class ShapeListener extends MouseInputAdapter {

    protected ImagePanel target;

    public ShapeListener(ImagePanel target) {
        this.target = target;
    }

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mouseDragged(MouseEvent e); 

    public abstract void mouseEntered(MouseEvent e); 

    public abstract void mouseExited(MouseEvent e); 

    public abstract void mouseMoved(MouseEvent e); 

    public abstract void mousePressed(MouseEvent e); 

    public abstract void mouseReleased(MouseEvent e);

    abstract void updateSize(MouseEvent e);

    protected abstract void updateDrawableShape(double compWidth, double compHeight);

    public abstract void setShapesToZero();

    protected abstract void paintShape(Graphics2D g2);

}
        

        