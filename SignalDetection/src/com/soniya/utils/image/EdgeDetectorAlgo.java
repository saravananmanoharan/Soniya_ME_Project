package com.soniya.utils.image;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class EdgeDetectorAlgo {
	private Mat imageMat;

	private final int[][] filter1 = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };

	private final int[][] filter2 = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };

	private int width = 0;

	private int height = 0;

	public EdgeDetectorAlgo(final Mat imageMat) {
		this.imageMat = imageMat;
		this.width = imageMat.width();
		this.height = imageMat.height();
	}

	// return the monochrome luminance of given color
	public int lum(double[] color) {
		if (color != null) {
			if (color.length == 3) {
				return (int) (0.299 * color[0] + 0.587 * color[1] + 0.114 * color[2]);
			}
		}
		return 0;
	}

	public Mat detectEdge() {
		Mat edgeDetectedImage = new Mat(width, height, CvType.CV_8UC3);
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {

				// get 3-by-3 array of colors in neighborhood
				int[][] gray = new int[3][3];
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						gray[i][j] = (int) lum(imageMat.get(x - 1 + i, y - 1+ j));
					}
				}

				// applying filters
				int gray1 = 0, gray2 = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						gray1 += gray[i][j] * filter1[i][j];
						gray2 += gray[i][j] * filter2[i][j];
					}
				}

				//find the magitude
				//int magnitude = 255 - truncate((int) Math.sqrt(gray1 * gray1 + gray2 * gray2));
				int magnitude = truncate((int) Math.sqrt(gray1 * gray1 + gray2 * gray2));

				edgeDetectedImage.put(x, y, new double[]{magnitude,magnitude,magnitude});
			}
		}
		return edgeDetectedImage;
	}

	// truncate color component to be between 0 and 255
    public static int truncate(int a) {
        if (a < 0)
            return 0;
        else if (a > 255)
            return 255;
        else
            return a;
    }
}
