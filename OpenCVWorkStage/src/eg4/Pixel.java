/**
 * Copyright (c) 2015 GT Nexus. All Rights Reserved.
 */
package eg4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Pixel {

    BufferedImage image;
    int width;
    int height;

    public Pixel() {
        try {
            //File input = new File("traffic signal.jpg");
            File input = new File("Simple10x10.jpg");
            //File input = new File("photo.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            int count = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    System.out.println("S.No: " + count + " Red: " + c.getRed() + "  Green: " + c.getGreen() + " Blue: " + c.getBlue());
                }
            }
        } catch (Exception e) {
        }
    }

    static public void main(String args[]) throws Exception
    {
        Pixel obj = new Pixel();
    }
}