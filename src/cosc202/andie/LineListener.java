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
    private Line2D currentLine = new Line2D.Double(initialPoint, pointToDraw);
    private Color shapeOutlineColour;
    private float lineWidth;

    public LineListener(ImagePanel target, Color shapeOutlineColour, float lineWidth) {
        super(target);
        this.shapeOutlineColour = shapeOutlineColour;
        this.lineWidth = lineWidth;
    }

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
    }

    public void mouseReleased(MouseEvent e) {
        finalPoint.setLocation(pointToDraw.getX(), pointToDraw.getY());
        currentLine.setLine(initialPoint, finalPoint);
        target.getImage().apply(new DrawLine(currentLine, shapeOutlineColour,lineWidth));
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }

    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pointToDraw.setLocation(x, y);
        currentLine = new Line2D.Double(initialPoint, pointToDraw);
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

    protected void paintShape(Graphics2D g2) {
        if (initialPoint != null) {
            //Draw a rectangle on top of the image.
            g2.setXORMode(Color.white); //Color of line varies depending on image colors
            g2.drawLine((int)getInitialPoint().getX(), (int)getInitialPoint().getY(), (int)getPointToDraw().getX(), (int)getPointToDraw().getY());
        }
    }
}
        

        