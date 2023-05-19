package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.Line2D;


import javax.swing.event.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;


/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public class DrawListener extends ShapeListener {

    
    private Point pointToDraw = new Point(0, 0);
    private Color shapeColor;
    private int radius;
    private Collection<Point> points = new ArrayList<>(); 

    public DrawListener(ImagePanel target, Color shapeColor, int radius) {
        super(target);
        this.shapeColor = shapeColor;
        this.radius = radius;
    }

    public DrawListener(ImagePanel target) {
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
        updateSize(e);
    }

    public void mouseReleased(MouseEvent e) {
        updateSize(e);
        this.setShapesToZero();
        target.removeShapeListener();
        target.repaint();
    }

    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pointToDraw.setLocation(x, y);
        target.getImage().tempApplyBrightnessContrast(new Draw(pointToDraw));
        points.add(pointToDraw);
        target.repaint();
    }

    protected void updateDrawableShape(double compWidth, double compHeight) {
    }

    public Point getPointToDraw() {
        return pointToDraw;
    }

    public void setShapesToZero() {
        pointToDraw = new Point(0, 0);
    }

    protected void paintShape(Graphics2D g2) {
    }
}
        

        