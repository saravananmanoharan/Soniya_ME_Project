/**
 * Copyright (c) 2015 GT Nexus. All Rights Reserved.
 */
package edu.princeton;

import java.awt.Color;

public class Spectrum {

    public static void main(String[] args) {

        // set scale and turn on animation mode
        StdDraw.setXscale(-1, 256);
        StdDraw.setYscale(-1, 256);
        StdDraw.show(0);

        // draw the color chips
        for (int r = 0; r < 255; r++) {
            StdOut.println("red = " + r);
            for (int g = 0; g < 255; g++) {
                for (int b = 0; b < 255; b++) {
                    Color c = new Color(r, g, b);
                    StdDraw.setPenColor(c);
                    StdDraw.filledSquare(g, b, 0.5);
                }
            }
            StdDraw.show(10);
        }
    }
}