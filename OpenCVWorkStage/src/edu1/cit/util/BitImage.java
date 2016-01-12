package edu1.cit.util;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class BitImage {
	private Mat imageMat;
	private int height;
	private int width;

	public BitImage(final Mat imageMat) {
		this.imageMat = imageMat;
		this.width = imageMat.width();
		this.height = imageMat.height();
	}



	public Mat getBitImage(){
		double magnitude = 255.0D;
		Mat edgeDetectedImage = new Mat(width, height, CvType.CV_8UC1);
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x < width ; x++){
				System.out.println(ImageUtil.printImageDataWithXY(edgeDetectedImage.get(x, y),x,y));
				if(ImageUtil.lum(edgeDetectedImage.get(x, y)) >= 50){
					magnitude = 0;
				}
				edgeDetectedImage.put(x, y, new double[]{magnitude});
			}
		}
		return edgeDetectedImage;
	}
}
