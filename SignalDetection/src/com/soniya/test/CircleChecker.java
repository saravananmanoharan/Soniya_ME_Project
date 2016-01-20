package com.soniya.test;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Checks weather an image contains a single non black filled black outlined circle<br />
 * This class is experimental, it does not provide exact results all the time, feel free to edit it and to correct
 * the bugs you might encounter.
 * @author      Ahmed KRAIEM
 * @version     0.9 alpha
 * @since       2013-04-03
 */
public class CircleChecker {

    private BufferedImage image;

    /**
     * Points that are equal to the calculated radius±<code>radiusesErrorMargin%</code> are not considered rogue points.<br />
     * <code>radiusesErrorMargin</code> must be <code>>0 && <1</code>
     */
    private double radiusesErrorMargin = 0.2;

    /**
     * A shape that has fewer than roguePointSensitivity% of rogue points is considered a circle.<br />
     * <code>roguePointSensitivity</code> must be <code>>0 && <1</code>
     */
    private double roguePointSensitivity = 0.05;
    /**
     * The presumed circle is divided into <code>angleCompartimentPrecision</code> parts,<br />
     * each part must have <code>minPointsPerCompartiment</code> points
     * <code>angleCompartimentPrecision</code> must be <code>> 0</code>
     */
    private int angleCompartimentPrecision = 50;
    /**
     * The minimum number of points requiered to declare a part valid.<br />
     * <code>minPointsPerCompartiment</code> must be <code>> 0</code>
     */
    private int minPointsPerCompartiment = 20;


    public CircleChecker(BufferedImage image) {
        super();
        this.image = image;
    }

    public CircleChecker(BufferedImage image, double radiusesErrorMargin,
            int minPointsPerCompartiment, double roguePointSensitivity,
            int angleCompartimentPrecision) {
        this(image);
        this.radiusesErrorMargin = radiusesErrorMargin;
        this.minPointsPerCompartiment = minPointsPerCompartiment;
        this.roguePointSensitivity = roguePointSensitivity;
        this.angleCompartimentPrecision = angleCompartimentPrecision;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public double getRadiusesErrorMargin() {
        return radiusesErrorMargin;
    }

    public void setRadiusesErrorMargin(double radiusesErrorMargin) {
        this.radiusesErrorMargin = radiusesErrorMargin;
    }

    public double getMinPointsPerCompartiment() {
        return minPointsPerCompartiment;
    }

    public void setMinPointsPerCompartiment(int minPointsPerCompartiment) {
        this.minPointsPerCompartiment = minPointsPerCompartiment;
    }

    public double getRoguePointSensitivity() {
        return roguePointSensitivity;
    }

    public void setRoguePointSensitivity(double roguePointSensitivity) {
        this.roguePointSensitivity = roguePointSensitivity;
    }

    public int getAngleCompartimentPrecision() {
        return angleCompartimentPrecision;
    }

    public void setAngleCompartimentPrecision(int angleCompartimentPrecision) {
        this.angleCompartimentPrecision = angleCompartimentPrecision;
    }

    /**
     * 
     * @return true if the image contains no more than <code>roguePointSensitivity%</code> rogue points
     * and all the parts contain at least <code>minPointsPerCompartiment</code> points.
     */
    class Pixel{
    	int x;
    	int y;
    	Color color;
		public Pixel(int x, int y, int pixel) {
			super();
			this.x = x;
			this.y = y;
			this.color = new Color(pixel);
		}
		@Override
		public String toString() {
			return "Pixel [x=" + x + ", y=" + y + ", pixel=" + color.toString() + "]";
		}
    	
    }
    public boolean isCircle() {
        List<Point> list = new ArrayList<>();
        List<Pixel> pixleVal = new ArrayList<Pixel>();
        final int xmin = image.getMinX();
        final int ymin = image.getMinY();

        final int ymax = ymin + image.getHeight();
        final int xmax = xmin + image.getWidth();

        for (int i = xmin; i < xmax; i++) {
            for (int j = ymin; j < ymax; j++) {

            	int pixel = image.getRGB(i, j) ;
                Color color = new Color(image.getRGB(i, j));
                

                if (Color.WHITE.equals(color)) {
                    list.add(new Point(i, j));
                    pixleVal.add(new Pixel(i, j, pixel));
                }
            }
        }
        if (list.size() == 0)
            return false;
        double diameter = -1;
        Point p1 = list.get(0);
        Point across = null;
        for (Point p2 : list) {
            double d = distance(p1, p2);
            if (d > diameter) {
                diameter = d;
                across = p2;
            }
        }
        double radius = diameter / 2;
        Point center = center(p1, across);
        int diffs = 0;

        int diffsUntilError = (int) (list.size() * roguePointSensitivity);
        double minRadius = radius - radius * radiusesErrorMargin;
        double maxRadius = radius + radius * radiusesErrorMargin;

        int[] compartiments = new int[angleCompartimentPrecision];


        for (int i=0; i<list.size(); i++) {
            Point p = list.get(i);
             double calRadius = distance(p, center);
             if (calRadius>maxRadius || calRadius < minRadius)
                 diffs++;
             else{
                 //Angle
                 double angle = Math.atan2(p.y -center.y,p.x-center.x);
                 //angle is between -pi and pi
                 int index = (int) ((angle + Math.PI)/(Math.PI * 2 / angleCompartimentPrecision));
                 compartiments[index]++;
             }
             if (diffs >= diffsUntilError){
                 return false;
             }
        }
        int sumCompartiments = list.size() - diffs;
        for(int comp : compartiments){
            if (comp < minPointsPerCompartiment){
                return false;
            }
        }

        return true;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private Point center(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    /*public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("image.bmp"));

        CircleChecker cc = new CircleChecker(image);

        System.out.println(cc.isCircle());
    }*/
}