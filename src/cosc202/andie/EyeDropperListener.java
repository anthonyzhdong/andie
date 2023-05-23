package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;


/** https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html was used to help create the intital rectangle selection 
     * for imagePanel. From there it was adpated to suit ANDIE.
     */
public class EyeDropperListener extends ShapeListener {

    
    private EditableImage editableImageVersion = target.getImage();
    private BufferedImage bufferedImageVersion = editableImageVersion.getCurrentImage();

    public EyeDropperListener(ImagePanel target) {
        super(target);
    }


    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x < bufferedImageVersion.getWidth() && y < bufferedImageVersion.getHeight() && x >= 0 && y >= 0){
            int rgb = bufferedImageVersion.getRGB(x,y);
            ShapeActions.eyeDropperColour = new Color(rgb);
            System.out.println(ShapeActions.eyeDropperColour);
        }
        
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
    }

    public void mouseReleased(MouseEvent e) {
    }
    void updateSize(MouseEvent e) {
    }

    protected void updateDrawableShape(double compWidth, double compHeight) {
        
    }
    public void setShapesToZero() {
    }
    protected void paintShape(Graphics2D g2) {
   
    }
}
        
