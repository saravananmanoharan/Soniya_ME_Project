package edu1.cit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageUtil {

	private String fileName = new String();

	public ImageUtil(String fileName) {
		this.fileName = fileName;
	}

	public Mat getCommonImage() {
		return Imgcodecs.imread(fileName, CvType.CV_8UC3);
	}

	public Mat getColorImage() {
		return Imgcodecs.imread(fileName, Imgcodecs.IMREAD_COLOR);
	}

	public Mat getGreyImage() {
		return Imgcodecs.imread(fileName, Imgcodecs.IMREAD_GRAYSCALE);
	}

	public void pintImageMat(Mat image) {
		for (int y = 0; y < image.height(); y++) {
			for (int x = 0; x < image.width(); x++) {
				double[] data = image.get(y, x);
				//System.out.print(printImageDataWithXY(data,x,y));
				System.out.print(printImageData(data));
			}
			System.out.println();
		}
	}

	public static String printImageDataWithXY(double[] data, int x, int y) {
		return new StringBuilder().append("X: ").append(x).append(" Y: ")
				.append(y).append(" ").append(printImageData(data)).toString();
	}

	public static String printImageData(double[] data) {
		StringBuilder str = new StringBuilder();
		str.append('[');
		for (int i = 0; i < data.length; i++) {
			if (i < data.length) {
				str.append(data[i]).append(' ');
			} else {
				str.append(data[i]);
			}
		}
		str.append(']');
		return str.toString();
	}

	public void saveImage(Mat image) {
		Imgcodecs.imwrite(
				"output\\".concat(fileName)
						.concat(Long.toString(new Date().getTime()))
						.concat(".jpg"), image);
	}
	
	// return the monochrome luminance of given color
	public static int lum(double[] color) {
		if (color != null) {
			if (color.length == 3) {
				return (int) (0.299 * color[0] + 0.587 * color[1] + 0.114 * color[2]);
			}else if(color.length == 1){
				return (int) color[0];
			}
				
		}
		return 0;
	}

}
