package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;


/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public abstract class ShapeListener extends MouseInputAdapter {

    protected boolean select;
    protected ImagePanel target;
    protected Shape initialShape;
    protected Shape shapeToDraw;
    protected Shape currentShape;

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

    public void updateSelect(boolean b) {
        this.select = b;
    }

    public boolean getSelect() {
        return select;
    }

    public abstract void setShapesToZero();
}
        

        