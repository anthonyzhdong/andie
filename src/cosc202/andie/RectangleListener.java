package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;


/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public class RectangleListener extends ShapeListener {

    
    private Rectangle initialRectangle = new Rectangle(0, 0, 0, 0);
    private Rectangle rectToDraw = new Rectangle(0, 0, 0, 0);
    private Rectangle currentRectangle = new Rectangle(0, 0, 0, 0);

    private static boolean select;

    public RectangleListener(ImagePanel target) {
        super(target);
    }


    public void mouseClicked(MouseEvent e) {
        setShapesToZero();
        target.repaint();
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
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if(select) {
            updateSize(e);
        } else {
            setShapesToZero();
            target.repaint();
        }
    }
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialRectangle.setSize(x - initialRectangle.x, y - initialRectangle.y);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
        currentRectangle = new Rectangle(rectToDraw);
    }

    protected void updateDrawableShape(double compWidth, double compHeight) {
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
            width = (int)compWidth - x;
        }
        if ((y + height) > compHeight) {
            height = (int)compHeight - y;
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

    public static void setSelect(boolean b) {
        select = b;
    }

    public static boolean getSelect() {
        return select;
    }

    public void setShapesToZero() {
        currentRectangle = new Rectangle(0, 0, 0, 0);
        rectToDraw = new Rectangle(0, 0, 0, 0);
        initialRectangle = new Rectangle(0, 0, 0, 0);
    }

    

    protected void paintShape(Graphics2D g2) {
        if (initialRectangle != null) {
            //Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); //Color of line varies depending on image colors
            g2.drawRect(rectToDraw.x, rectToDraw.y, (int)rectToDraw.getWidth(), (int)rectToDraw.getHeight());
        }
    }
}
        

        