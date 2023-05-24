package cosc202.andie;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a point on the image.
 * </p>
 * 
 * <p>
 * This class uses a Point object to specify the location of the point to be
 * drawn.
 * The user can then drag the cursor to create a line of points to be drawn
 * It also allows customization of the point's color and radius.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Liam Kerr
 * @version 3.0
 */
public class Draw implements ImageOperation, java.io.Serializable {
    private BufferedImage output;
    private Point p;
    private int radius = 4;
    private Color shapeFillColour;
    private boolean eyeDropper;

    /**
     * Construct a Draw object with the given point.
     * The default color is red and the default radius is 4.
     * 
     * @param p The Point object specifying the location of the point.
     */
    public Draw(Point p) {
        this.p = p;
        this.shapeFillColour = Color.red;
    }

    /**
     * Construct a Draw object with the given point, color, and radius.
     * 
     * @param p          The Point object specifying the location of the point.
     * @param c          The color of the point.
     * @param radius     The radius of the point.
     * @param eyeDropper whether to use the eye dropper colour or not
     */
    public Draw(Point p, Color c, int radius, boolean eyeDropper) {
        this.p = p;
        this.shapeFillColour = c;
        this.radius = radius;
        this.eyeDropper = eyeDropper;
    }

    /**
     * Apply the draw point operation to the input image.
     * 
     * @param input The image to apply the draw point operation to.
     * @return The resulting image with the point drawn.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        output = new BufferedImage(width, height, input.TYPE_INT_ARGB);

        adjustPoint();

        Ellipse2D e2d = new Ellipse2D.Double(p.getX(), p.getY(), 2 * (double) radius + 1, 2 * (double) radius + 1);
        Graphics2D g2 = output.createGraphics();
        if (eyeDropper) {
            g2.setColor(ShapeActions.eyeDropperColour);
        } else {
            g2.setColor(shapeFillColour);
        }
        g2.drawImage(input, 0, 0, null);
        g2.fillOval((int) e2d.getX(), (int) e2d.getY(), (int) e2d.getWidth(), (int) e2d.getHeight());
        g2.dispose();
        return output;
    }

    private Point adjustPoint() {
        double x = this.p.getX();
        double y = this.p.getY();
        x = x - radius;
        y = y - radius;
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;

        this.p = new Point((int) x, (int) y);
        return p;
    }

}