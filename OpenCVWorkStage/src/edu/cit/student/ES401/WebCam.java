package edu.cit.student.ES401;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.*;
import org.opencv.videoio.*;

public class WebCam {

	public BufferedImage getImage() {
		BufferedImage image = null;
		VideoCapture camera = new VideoCapture();
		camera.open(0);
		Mat frame = new Mat();
		camera.read(frame);
		if (!camera.isOpened()) {
			System.out.println("Error");
		} else {
			if (camera.read(frame)) {
				image = MatToBufferedImage(frame);
				new EdgeDetector().processForEdgeDetection(image);
			}
		}
		camera.release();
		return image;
	}

	public BufferedImage MatToBufferedImage(Mat frame) {
		// Mat() to BufferedImage
		int type = 0;
		if (frame.channels() == 1) {
			type = BufferedImage.TYPE_BYTE_GRAY;
		} else if (frame.channels() == 3) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		BufferedImage image = new BufferedImage(frame.width(), frame.height(),
				type);
		WritableRaster raster = image.getRaster();
		DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
		byte[] data = dataBuffer.getData();
		frame.get(0, 0, data);

		return image;
	}
}
