package cosc202.andie;

import java.awt.image.BufferedImage;



public class Flip implements ImageOperation, java.io.Serializable{
    Flip(){}


    /**
     * <p>
     * Flips an image horizontally
     * </p>
     * 
     * 
     * @param input The image to apply the flip to.
     * @return The resulting (flipped) image.
     */
    public BufferedImage apply(BufferedImage input){
        
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage FlippedImage = new BufferedImage(width,height,input.TYPE_INT_ARGB);

        for(int x = 0; x<width;x++){
            for(int y = 0; y<height;y++){
                int rgb = input.getRGB(x,y);
                FlippedImage.setRGB(width-x-1,y,rgb);
            }
        }
    return FlippedImage;
    }

}
