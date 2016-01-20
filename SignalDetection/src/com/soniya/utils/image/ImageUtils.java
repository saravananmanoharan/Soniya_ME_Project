package com.soniya.utils.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

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

	public static BufferedImage getBufferedImageFromMat(Mat mat) {
		int type;
		byte[] dat = new byte[mat.rows() * mat.cols() * 3 ];
    	mat.get(0, 0, dat);

    	if(mat.channels() == 1){
    		type = BufferedImage.TYPE_BYTE_GRAY;
    	}else{
    		type = BufferedImage.TYPE_3BYTE_BGR;
    	}
    	BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), dat);
		return image;
	}
	/**
	 * Converts/writes a Mat into a BufferedImage.
	 * 
	 * @param bgr Mat of type CV_8UC3 or CV_8UC1
	 * @return BufferedImage of type TYPE_INT_RGB or TYPE_BYTE_GRAY
	 */
	public static BufferedImage matToBufferedImage(Mat bgr) {
	    int width = bgr.width();
	    int height = bgr.height();
	    BufferedImage image;
	    WritableRaster raster;

	    if (bgr.channels()==1) {
	        image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	        raster = image.getRaster();

	        byte[] px = new byte[1];

	        for (int y=0; y<height; y++) {
	            for (int x=0; x<width; x++) {
	                bgr.get(y,x,px);
	                raster.setSample(x, y, 0, px[0]);
	            }
	        }
	    } else {
	        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        raster = image.getRaster();

	        byte[] px = new byte[3];
	        int[] rgb = new int[3];

	        for (int y=0; y<height; y++) {
	            for (int x=0; x<width; x++) {
	                bgr.get(y,x,px);
	                rgb[0] = px[2];
	                rgb[1] = px[1];
	                rgb[2] = px[0];
	                raster.setPixel(x,y,rgb);
	            }
	        }
	    }

	    return image;
	}
}
