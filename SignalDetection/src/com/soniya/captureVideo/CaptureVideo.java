package com.soniya.captureVideo;

import com.soniya.captureVideo.Mat2Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.opencv.videoio.*;

//import org.opencv.highgui.VideoCapture;

public class CaptureVideo {

	VideoCapture cap;
	Mat2Image mat2Img = new Mat2Image();

	public CaptureVideo() {
		try {
        cap = new VideoCapture();
        cap.open(0);
    } catch (Exception e) {
        System.out.println("Unable to Connect to Camera!");
        System.exit(-1);
    }
	}

	public BufferedImage getOneFrame() throws IOException {
		cap.read(mat2Img.mat);
		return mat2Img.getImage(mat2Img.mat);
	}
}