package edu.cit.student.ES401;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import org.opencv.core.Core;

public class Main {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ProjectUI frame = new ProjectUI();
				frame.setVisible(true);
			}
		});
	}
}
