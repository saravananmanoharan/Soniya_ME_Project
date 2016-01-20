package com.soniya.captureVideo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.soniya.utils.common.PropertyValuesFinder;
import com.soniya.utils.image.EdgeDetectorAlgo;
import com.soniya.utils.image.ImageUtils;

public class Mat2Image {

    Mat mat = new Mat();
    BufferedImage img;
    byte[] dat;
    int w = 0;
    int h = 0;
    PropertyValuesFinder propertyValuesFinder = PropertyValuesFinder.getInstance();

    public Mat2Image() {
    }

    public Mat2Image(Mat mat) {
        //getSpace(mat);
    }

    public void getSpace(Mat mat) {
        if (mat.cols() > 0 & mat.rows() > 0) {
            this.w = mat.cols();
            this.h = mat.rows();
        }

        if (dat == null || dat.length != w * h * 3) {
            dat = new byte[w * h * 3];
        	//dat = new byte[w * h];
        }

        if (img == null || img.getWidth() != w || img.getHeight() != h || img.getType() != BufferedImage.TYPE_3BYTE_BGR) {
            img = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        }
    }

    BufferedImage getImage(Mat mat) throws IOException {
    	this.mat = mat;
        return processImage();
    }

	private BufferedImage processImage() {
/*		Mat src = new Mat(mat.rows(),mat.cols(), CvType.CV_8UC3);
    	Mat grey = new Mat(mat.rows(),mat.cols(), CvType.CV_8UC3);
    	Mat bw = new Mat(mat.rows(),mat.cols(), CvType.CV_8UC3);
    	Mat dest = new Mat(mat.rows(),mat.cols(), CvType.CV_8UC3);
*/
		Mat src = new Mat();
    	Mat gray = new Mat();
    	Mat bw = new Mat();
    	Mat dest = new Mat();

    	dest = ImageUtils.convert2GrayScale(mat);
    	EdgeDetectorAlgo edgeDetectorAlgo = new EdgeDetectorAlgo(mat);
    	dest = edgeDetectorAlgo.detectEdge();

    	//dest = detectShape(edgeDetectorAlgo.detectEdge());
    	//Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGB2GRAY);
    	//Imgproc.blur(gray, bw, new Size(3,3));
    	//Imgproc.Canny(gray, bw, 80, 240);
    	//Imgproc.Canny(grey, bw, 80, 80);
      
    	/**
    	 * find the contours
    	 * 
    	 */
//    	List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
//    	Mat hierarchy = new Mat();
//    	dest.convertTo(dest, CvType.CV_8UC1);
//    	Imgproc.findContours(dest.clone(), contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
      	//mat.copyTo(dest);
    	
    	//getSpace(dest);
    	
    	//byte[] dat = new byte[bw.rows() * bw.cols() * (int) (bw.elemSize())];
        //img.getRaster().setDataElements(0, 0, dest.width(), dest.height(), dat);
		 
    	/*for (int i = 0; i < contours.size(); i++){
    		System.out.println(i+"\n"+contours.get(i).dump());
    		// Approximate contour with accuracy proportional
    		// to the contour perimeter
    		//Imgproc.ap
    	}*/

    	byte[] dat = new byte[dest.rows() * dest.cols() * 3 ];
    	dest.get(0, 0, dat);

    	BufferedImage image = new BufferedImage(dest.cols(), dest.rows(), BufferedImage.TYPE_3BYTE_BGR);;
        image.getRaster().setDataElements(0, 0, dest.cols(), dest.rows(), dat);
        
        return image;
	}

	private Mat detectShape(Mat detectEdge) {
		Mat shapeDetect = detectEdge.clone();

		System.out.println(shapeDetect.dump());
		return shapeDetect;
	}
}