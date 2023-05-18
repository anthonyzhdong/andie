package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

import javax.swing.event.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;


/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public class LineListener extends ShapeListener {

    
    private Point initialPoint = new Point(0, 0);
    private Point pointToDraw = new Point(0, 0);
    private Point finalPoint = new Point(0, 0);
    Line2D currentLine = new Line2D.Double(initialPoint, pointToDraw);

    public LineListener(ImagePanel target) {
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
        initialPoint = new Point(x, y);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        finalPoint.setLocation(pointToDraw.getX(), pointToDraw.getY());
        currentLine.setLine(initialPoint, finalPoint);
        target.getImage().apply(new DrawLine(currentLine));
        this.setShapesToZero();
        target.removeLineListener();
        target.repaint();
        select = false;
    }

    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pointToDraw.setLocation(x, y);
        currentLine = new Line2D.Double(initialPoint, pointToDraw);
        updateDrawableShape(target.getImage().getCurrentImage().getWidth(), target.getImage().getCurrentImage().getHeight());
        target.repaint();
    }

    protected void updateDrawableShape(double compWidth, double compHeight) {
    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public Point getPointToDraw() {
        return pointToDraw;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }


    public void setShapesToZero() {
        initialPoint = new Point(0, 0);
        pointToDraw = new Point(0, 0);
        finalPoint = new Point(0, 0);
        currentLine = new Line2D.Double(0, 0, 0, 0);
    }
}
        

        