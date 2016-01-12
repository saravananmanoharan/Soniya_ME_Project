package edu.cit.student.ES401;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ProjectUI extends JFrame{
	private JPanel contentPane;
	public ProjectUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
  
        new MyThread().start();
    }
	
	public void paint(Graphics g){
        g = contentPane.getGraphics();
        BufferedImage image = new WebCam().getImage();
        g.drawImage(image, 0, 0, this);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString("TEST SYSTEM", 5, image.getHeight() - 10);
    }
 
    class MyThread extends Thread{
        @Override
        public void run() {
            for (;;){
                repaint();
                try { 
                	Thread.sleep(1);
                } catch (InterruptedException e) {
                	
                }
            }  
        } 
    }
}
