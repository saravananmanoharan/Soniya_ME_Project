/**
 * Copyright (c) 2015 GT Nexus. All Rights Reserved.
 */
package eg7;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Main {

    public static void main(String[] args)
    {
        try
        {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            String fileName = "idel_traffic_signal.jpg";
            File input = new File(fileName);
            BufferedImage image = ImageIO.read(input);

            byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            mat.put(0, 0, data);

            Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2HSV);

            byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
            mat1.get(0, 0, data1);
            BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), 5);
            image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

            File ouptut = new File("output\\hsv_".concat(fileName).concat(Long.toString(new Date().getTime())).concat(".jpg"));
            ImageIO.write(image1, "jpg", ouptut);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}