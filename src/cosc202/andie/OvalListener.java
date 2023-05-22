package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.security.spec.EllipticCurve;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.event.*;

 
/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital oval selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public class OvalListener extends ShapeListener {

    
    private Ellipse2D initialOval = new Ellipse2D.Double(0, 0, 0, 0);
    private Ellipse2D ovalToDraw = new Ellipse2D.Double(0, 0, 0, 0);
    private Ellipse2D currentOval = new Ellipse2D.Double(0, 0, 0, 0);
    private Color shapeOutlineColour;
    private Color shapeFillColour;
    private float lineWidth;
    private boolean shapeFill;

    public OvalListener(ImagePanel target, Color shapeOutlineColour, Color shapeFillColour, float lineWidth,boolean shapeFill) {
        super(target);
        this.shapeOutlineColour = shapeOutlineColour;
        this.shapeFillColour = shapeFillColour;
        this.lineWidth = lineWidth;
        this.shapeFill = shapeFill;
    }

    public OvalListener(ImagePanel target) {
        super(target);
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
        initialOval = new Ellipse2D.Double(x, y, 0, 0);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        currentOval.setFrame(ovalToDraw.getBounds());
        target.getImage().apply(new DrawOval(currentOval,shapeOutlineColour,shapeFillColour,lineWidth, shapeFill));
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }
    
    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        initialOval.setFrame(initialOval.getX(), initialOval.getY(), x - initialOval.getX(), y - initialOval.getY());
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
        currentOval.setFrame(ovalToDraw.getBounds());
    }

    protected void updateDrawableShape(double compWidth, double compHeight) {
        double x = initialOval.getX();
        double y = initialOval.getY();
        double width = initialOval.getWidth();
        double height = initialOval.getHeight();

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
        //The oval shouldn't extend past the drawing area.
        if ((x + width) > compWidth) {
            width = compWidth - x;
        }
        if ((y + height) > compHeight) {
            height = compHeight - y;
        }
        ovalToDraw.setFrame(x, y, width, height);
        //Update ovalToDraw after saving old value
    }

    public Ellipse2D getCurrentOval() {
        return currentOval;
    }

    public Ellipse2D getInitialOval() {
        return initialOval;
    }

    public Ellipse2D getOvalToDraw() {
        return ovalToDraw;
    }

    public void setShapesToZero() {
        currentOval = new Ellipse2D.Double(0, 0, 0, 0);
        ovalToDraw = new Ellipse2D.Double(0, 0, 0, 0);
        initialOval = new Ellipse2D.Double(0, 0, 0, 0);
    }

    protected void paintShape(Graphics2D g2) {
        if (initialOval != null) {
            //Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); //Color of line varies depending on image colors
            g2.drawOval((int)getOvalToDraw().getX(), (int)getOvalToDraw().getY(), (int)getOvalToDraw().getWidth() - 1, (int)getOvalToDraw().getHeight() - 1);
        }
    }
}
        

        