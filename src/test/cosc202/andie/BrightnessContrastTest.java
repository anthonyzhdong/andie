package test.cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

import cosc202.andie.BrightnessContrast;

public class BrightnessContrastTest {
    @Test
    void getBrightnessInitialValue() {
        BrightnessContrast testBC = new BrightnessContrast();
        Assertions.assertEquals(0, testBC.getBrightnessDegree());
    }

    @Test
    void getContrastInitialValue() {
        BrightnessContrast testBC = new BrightnessContrast();
        Assertions.assertEquals(0, testBC.getContrastDegree());
    }

    @Test
    void testTruncate() {
        BrightnessContrast testBC = new BrightnessContrast();
        int truncateResultUpper = testBC.Truncate(256);
        int truncateResultLower = testBC.Truncate(-1);
        Assertions.assertEquals(255, truncateResultUpper);
        Assertions.assertEquals(0, truncateResultLower);
    }   

    @Test
    void testBrightness() {
        BrightnessContrast testBC = new BrightnessContrast(50, 0);
        String filePath = "kisspng-mexico-cinco-de-mayo-battle-of-puebla-clip-art-cactus-png-transparent-image-5a76395ade6596.6807686615176973709109";
        try {
            File imageFile = new File(filePath);
            BufferedImage original = ImageIO.read(imageFile);
            original = testBC.apply(original);
            int changedRGB = original.getRGB(original.getHeight() / 2, original.getWidth() / 2);
            Assertions.assertEquals(-8536211, changedRGB);
            
        } catch (Exception e) {
            
        }
        
        

        
    }

    @Test
    void testContrast() {
        BrightnessContrast testBC = new BrightnessContrast(0, 50);
        String filePath = "kisspng-mexico-cinco-de-mayo-battle-of-puebla-clip-art-cactus-png-transparent-image-5a76395ade6596.6807686615176973709109";
        try {
            File imageFile = new File(filePath);
            BufferedImage original = ImageIO.read(imageFile);
            original = testBC.apply(original);
            int changedRGB = original.getRGB(original.getHeight() / 2, original.getWidth() / 2);
            Assertions.assertEquals(-8536211, changedRGB);
            
        } catch (Exception e) {
            
        }
        
        

        
    }
    

}
