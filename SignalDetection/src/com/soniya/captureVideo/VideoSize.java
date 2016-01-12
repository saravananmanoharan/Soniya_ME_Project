package com.soniya.captureVideo;

public class VideoSize {
	private int width;
	private int height;

	private static VideoSize instance;

	public static VideoSize getInstance() {
		if (instance == null) {
			instance = new VideoSize();
		}
		return instance;
	}

	private VideoSize() {

	}

	public void setVideoSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
