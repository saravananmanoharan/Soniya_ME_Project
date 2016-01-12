package com.soniya.captureVideo;

import com.soniya.captureVideo.Mat2Image;

import java.awt.image.BufferedImage;

import org.opencv.videoio.*;

//import org.opencv.highgui.VideoCapture;

public class CaptureVideo {

	VideoCapture cap;
	Mat2Image mat2Img = new Mat2Image();

	public CaptureVideo() {
		cap = new VideoCapture();
		cap.open(0);
	}

	public BufferedImage getOneFrame() {
		cap.read(mat2Img.mat);
		return mat2Img.getImage(mat2Img.mat);
	}
}