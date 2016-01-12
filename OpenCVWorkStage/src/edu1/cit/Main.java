package edu1.cit;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import edu1.cit.util.BitImage;
import edu1.cit.util.EdgeDetectorAlgo;
import edu1.cit.util.ImageUtil;

public class Main {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		ImageUtil imageUtil = new ImageUtil("idel_traffic_signal.jpg");
		Mat imageMat = imageUtil.getColorImage();
		//imageUtil.pintImageMat(imageMat);
		
		EdgeDetectorAlgo edgeDetectorAlgo = new EdgeDetectorAlgo(imageMat);
		Mat imageEdgeDetectedMat = edgeDetectorAlgo.detectEdge();
		imageUtil.saveImage(imageEdgeDetectedMat);
		
		BitImage bitImage = new BitImage(imageEdgeDetectedMat);
		//imageUtil.saveImage(bitImage.getBitImage());
		System.out.print(imageEdgeDetectedMat.dump());
		/*List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(imageEdgeDetectedMat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		for (MatOfPoint contour :contours) {
			//System.out.println(Imgproc.contourArea(contour));
			contour.w
			if (Imgproc.contourArea(contour) > 50) {
				System.out.print(Imgproc.contourArea(contour)+ " ");
				Rect rect = Imgproc.boundingRect(contour);
				System.out.println("rect.x: "+rect.x+" rect.width: "+rect.width+" rect.y: "+rect.y+" rect.height "+rect.height);
				if(rect.height > 28){
					Imgproc.rectangle(imageEdgeDetectedMat, 
							new Point(rect.x,rect.y), 
							new Point(rect.x+rect.width,rect.y+rect.height),
							new Scalar(0,0,255));
				}
			}
		}
*/		//imageUtil.saveImage(imageEdgeDetectedMat);

	}

}
