package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.annotation.Target;

import javax.swing.*;

public class regionSelect extends ImageAction {
    Rectangle captureRect;

    regionSelect() {
        
        super();

        BufferedImage bI = target.getImage().getCurrentImage();
        BrightnessContrast bc = new BrightnessContrast(50, 50);
        bI = bc.apply(bI);
        EditableImage ed = new EditableImage();
        ed.setCurrentImage(bI);
        ImagePanel newPanel = new ImagePanel();
        newImage.setImage(ed);
        JScrollPane scrollPane = new JScrollPane(newPanel);
        
        scrollPane.addMouseMotionListener(new MouseMotionAdapter() {

            Point start = new Point();

            @Override
            public void mouseMoved(MouseEvent me) {
                start = me.getPoint();
                repaint(screen, screenCopy);
                selectionLabel.setText("Start Point: " + start);
                screenLabel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                Point end = me.getPoint();
                captureRect = new Rectangle(start,
                        new Dimension(end.x-start.x, end.y-start.y));
                repaint(screen, screenCopy);
                screenLabel.repaint();
                selectionLabel.setText("Rectangle: " + captureRect);
            }
        });
    }

    public void repaint(BufferedImage orig, BufferedImage copy) {
        Graphics2D g = copy.createGraphics();
        g.drawImage(orig,0,0, null);
        if (captureRect!=null) {
            g.setColor(Color.RED);
            g.draw(captureRect);
            g.setColor(new Color(255,255,255,150));
            g.fill(captureRect);
        }
        g.dispose();
    }
}
