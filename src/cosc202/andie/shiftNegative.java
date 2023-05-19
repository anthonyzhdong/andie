package cosc202.andie;

import java.awt.image.BufferedImage;


public class shiftNegative {

    public shiftNegative() {
    }



    public static BufferedImage fixNegative (BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                a = (a + 255) / 2;
                r = (r + 255) / 2;
                g = (g + 255) / 2;
                b = (b + 255) / 2;

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        return input;
    }


}