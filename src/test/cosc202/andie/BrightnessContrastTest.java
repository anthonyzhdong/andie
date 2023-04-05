package test.cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    

}
