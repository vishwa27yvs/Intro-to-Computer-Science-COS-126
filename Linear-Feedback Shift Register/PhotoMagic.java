/******************************************************************************
 * Compilation: javac-introcs PhotMagic.java
 * Execution: java-introcs PhotoMagic
 * Dependancies: Picture datatype(stdlib.jar),Color datatype
 * PhotoMagic.java is a LFSR client which is used to encrypt and decrypt pictures
 **************************************************************************** */

import java.awt.Color;

public class PhotoMagic {
    /*
     * transform() method returns a transformed picture using LFSR
     * @param picture - the .png file to be encoded or decoded
     * @param lfsr- LFSR argument */
    public static Picture transform(Picture picture, LFSR lfsr) {

        // accessing all pixels using a 2-dimensional array
        Picture newPicture = new Picture(picture.width(), picture.height());
        int width = picture.width();
        int height = picture.height();
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                Color c = picture.get(j, i);
                // arrays that will store the bit representation

                // for each pixel extract the red, green and blue components
                int r = c.getRed() ^ lfsr.generate(8);
                int g = c.getGreen() ^ lfsr.generate(8);
                int b = c.getBlue() ^ lfsr.generate(8);
                // calculating the 8-bit representation of RGB-components of the pixel
                // forming c1 color datatype with new components
                Color c1 = new Color(r, g, b);
                // setting the particular pixel to new color c1
                newPicture.set(j, i, c1);
            }
        }
        return newPicture;
    }


    public static void main(String[] args) {
        // read the picture file '.png' format from command line
        Picture source = new Picture(args[0]);
        // read LFSR object-initial LFSR seed(String),tapPosition(int)
        LFSR lfsr = new LFSR(args[1], Integer.parseInt(args[2]));
        // target stores the encrypted or decrypted picture returned from the transform function
        Picture target = transform(source, lfsr);
        target.show();
    }

}

