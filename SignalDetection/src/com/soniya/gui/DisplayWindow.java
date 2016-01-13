package com.soniya.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import com.soniya.captureVideo.CaptureVideo;
import com.soniya.gui.component.StatusBar;

import java.awt.Window.Type;
import java.io.IOException;

public class DisplayWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5301431698259422213L;

	private String title;
	private int width;
	private int height;
	private StatusBar statusBar;
	private JFrame frame;

	public DisplayWindow(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public void showJFrameDemo() {
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(width, height);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.out.println("Exit System!");
				frame.dispose();
			}
		});

		statusBar = new StatusBar(width);
		statusBar.setMessage("Test");
		frame.getContentPane().add(statusBar, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	public void setStatusBarMessage(String message) {
		statusBar.setMessage(message);
	}
	CaptureVideo videoCap = new CaptureVideo();
	
	public void paint(Graphics g){
		g = frame.getContentPane().getGraphics();
		try {
			g.drawImage(videoCap.getOneFrame(), 0, 0, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
