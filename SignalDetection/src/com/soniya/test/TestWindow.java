package com.soniya.test;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

import com.soniya.utils.image.EdgeDetectorAlgo;
import com.soniya.utils.image.ImageUtils;

public class TestWindow extends JFrame {

	private static final long serialVersionUID = -6039816816792591103L;
	
	private JFrame frame;
	private String title;
	private int width;
	private int height;
	private BufferedImage image;
	private Mat mat;

	public TestWindow(String title, int width, int height)
			throws HeadlessException {
		super();
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public TestWindow(String title, Mat mat) {
		this.title = title;
		this.mat = mat;
		this.height = mat.height();
		this.width = mat.width();
	}

	public void showJFrameDemo() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.out.println("Exit System!");
				frame.dispose();
			}
		});
		
		Mat dest = ImageUtils.convert2GrayScale(mat);
		EdgeDetectorAlgo edgeDetectorAlgo = new EdgeDetectorAlgo(mat);
    	dest = edgeDetectorAlgo.detectEdge();
    	//System.out.println(dest.dump());
		
    	
    	image = ImageUtils.matToBufferedImage(dest);
    	
    	TestDetector cc = new TestDetector(image);

        System.out.println(cc.isCircle());
        
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		frame.setVisible(true);
	}

}
