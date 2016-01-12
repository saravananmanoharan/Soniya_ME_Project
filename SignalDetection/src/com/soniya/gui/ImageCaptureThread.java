package com.soniya.gui;

class ImageCaptureThread extends Thread {
	/**
	 * 
	 */
	private final DisplayWindow2 displayWindow2;

	/**
	 * @param displayWindow2
	 */
	ImageCaptureThread(DisplayWindow2 displayWindow2) {
		this.displayWindow2 = displayWindow2;
	}

	@Override
	public void run() {
		for (;;) {
			this.displayWindow2.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
}