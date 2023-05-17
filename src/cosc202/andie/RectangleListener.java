package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;


/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public class RectangleListener extends MouseInputAdapter {

    
    private Rectangle initialRectangle = new Rectangle(0, 0, 0, 0);
    private Rectangle rectToDraw = new Rectangle(0, 0, 0, 0);
    private Rectangle currentRectangle = new Rectangle(0, 0, 0, 0);
    private boolean select;
    private ImagePanel target;

    public RectangleListener(ImagePanel target) {
        this.target = target;
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        updateSize(e);
        
    }

    public void mouseEntered(MouseEvent e) {
            
    }

    public void mouseExited(MouseEvent e) {
            
    }

    public void mouseMoved(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialRectangle = new Rectangle(x, y, 0, 0);
        updateDrawableRect(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if(select) {
            updateSize(e);
        } else {
            updateDrawableRect(0, 0);
            target.repaint();
        }
    }
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialRectangle.setSize(x - initialRectangle.x, y - initialRectangle.y);
        updateDrawableRect(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
        if(select) currentRectangle = new Rectangle(rectToDraw);
    }

    private void updateDrawableRect(int compWidth, int compHeight) {
        int x = initialRectangle.x;
        int y = initialRectangle.y;
        int width = initialRectangle.width;
        int height = initialRectangle.height;

        //Make the width and height positive, if necessary.
        if (width < 0) {
            width = 0 - width;
            x = x - width + 1; 
            if (x < 0) {
                width += x; 
                x = 0;
            }
        }
        if (height < 0) {
            height = 0 - height;
            y = y - height + 1; 
            if (y < 0) {
                height += y; 
                y = 0;
            }
        } 
        //The rectangle shouldn't extend past the drawing area.
        if ((x + width) > compWidth) {
            width = compWidth - x;
        }
        if ((y + height) > compHeight) {
            height = compHeight - y;
        }
        rectToDraw = new Rectangle(x, y, width, height);
        //Update rectToDraw after saving old value.
        
    }

    public Rectangle getCurrentRectangle() {
        return currentRectangle;
    }

    public Rectangle getInitialRectangle() {
        return initialRectangle;
    }

    public Rectangle getRectToDraw() {
        return rectToDraw;
    }

    public void updateSelect(boolean b) {
        select = b;
    }

    public boolean getSelect() {
        return select;
    }

    public void setRectanglesToZero() {
        currentRectangle = new Rectangle(0, 0, 0, 0);
        rectToDraw = new Rectangle(0, 0, 0, 0);
        initialRectangle = new Rectangle(0, 0, 0, 0);
    }
}
        

        