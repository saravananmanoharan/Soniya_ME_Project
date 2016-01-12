package edu.cit.student.ES401;

import java.awt.image.BufferedImage;

public class EdgeDetector {
	// truncate color component to be between 0 and 255
    public static int truncate(int a) {
        if (a < 0)
            return 0;
        else if (a > 255)
            return 255;
        else
            return a;
    }
	public void processForEdgeDetection(BufferedImage image) {
		int[][] filter1 = {
	            { -1, 0, 1 },
	            { -2, 0, 2 },
	            { -1, 0, 1 }
	        };

	        int[][] filter2 = {
	            { 1, 2, 1 },
	            { 0, 0, 0 },
	            { -1, -2, -1 }
	        };
	        
	        int width = image.getWidth();
	        int height = image.getHeight();
	        
	        
	}

	
}
