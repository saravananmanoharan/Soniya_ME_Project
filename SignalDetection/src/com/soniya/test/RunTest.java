package com.soniya.test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class RunTest {

	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	public static void main(String[] args) throws IOException {
		File input = new File("resources/idel_traffic_signal.jpg");
        BufferedImage image = ImageIO.read(input);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);
        System.out.println(image.getData());
		TestWindow testWindow = new TestWindow(input.getAbsolutePath(),mat);
		testWindow.showJFrameDemo();
		//testWindow.repaint();
	}

}
