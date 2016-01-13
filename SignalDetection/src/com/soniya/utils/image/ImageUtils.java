package com.soniya.utils.image;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ImageUtils {
	public static Mat convert2GrayScale(Mat mat) {
		int row = mat.rows();
		int col = mat.cols();
		int channels = CvType.CV_8UC3;
		Mat gray = new Mat(row, col, channels);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int magnitude = lum(mat.get(i, j));
				double[] data = new double[]{magnitude,magnitude,magnitude};
				gray.put(i, j, data);
			}
		}
		return gray;
	}

	// return the monochrome luminance of given color
	public static int lum(double[] color) {
		if (color != null) {
			if (color.length == 3) {
				return (int) (0.299 * color[0] + 0.587 * color[1] + 0.114 * color[2]);
			} else if (color.length == 1) {
				return (int) color[0];
			}

		}
		return 0;
	}

}
