package com.soniya.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.soniya.captureVideo.CaptureVideo;

public class DisplayWindow2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5301431698259422213L;

	private JPanel contentPane;

	public DisplayWindow2(String title, int width, int height) {
		createFrame();
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new ImageCaptureThread(this).start();
	}

	CaptureVideo videoCap = new CaptureVideo();

	public void paint(Graphics g) {
		g = contentPane.getGraphics();
		
		g.drawImage(videoCap.getOneFrame(), 0, 0, this);
	}
}
