package edu.cit.student.ES401;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ProjectWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	private BufferedImage image;
	int width;
	int height;
	private static ProjectWindow window;

	private ProjectWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	public static ProjectWindow getInstance() {
		if (window == null) {
			window = new ProjectWindow();
		}
		return window;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ProjectWindow(int width, int height) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setSize(306, 313);
	}

	public ProjectWindow(BufferedImage image, int width, int height) {
		/*
		 * contentPane = new JPanel();
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 * setContentPane(contentPane); contentPane.setLayout(null);
		 */
		this.setSize(width, height);
		this.image = image;
		this.width = width;
		this.height = height;
	}

	@Override
	public void paint(Graphics g) {
		//g = contentPane.getGraphics();
		g.drawImage(image, 0, 0, this);
		g.drawString("TEST SYSTEM", 2, height - 10);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
	}

	public void putImage(BufferedImage image, int width, int height) {
		repaint();
		this.setSize(width + 10, height + 10);
		this.image = image;
		this.width = width;
		this.height = height;
	}
}
