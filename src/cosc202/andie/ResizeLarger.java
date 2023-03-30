package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;



public class ResizeLarger implements ImageOperation, java.io.Serializable  {

    ResizeLarger(){

    
    }
    public BufferedImage apply(BufferedImage input){


        //BufferedImage bufferedImage = input;
        // create buffered image version of the input
        // change the scales of it
        Image enlarged = input.getScaledInstance(800,500, Image.SCALE_DEFAULT);
        BufferedImage bufferedImage = new BufferedImage(2000,1000,BufferedImage.TYPE_INT_ARGB);
        
        

        return bufferedImage; //enlarged;
    }

    }
    

