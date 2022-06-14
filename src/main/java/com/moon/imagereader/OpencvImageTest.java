package com.moon.imagereader;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author yujiangtao
 * @date 2021/8/13 上午11:44
 */
public class OpencvImageTest {
    public static void main(String[] args) {
        //加载动态库，main方法无法通过配置文件加载
        System.load("/home/yujt/software/opencv-4.5.3/build/lib/libopencv_java453.so");
        String markContent = "测试生成水印";
        String waterPath = "/home/yujt/Desktop/0.jpg";
        String imagePath = "/home/yujt/Desktop/1.jpg";
        //opencv读取水印图
        Mat water = Imgcodecs.imread(waterPath);
        //opencv读取图片
        Mat image = Imgcodecs.imread(imagePath, CvType.CV_8UC1);
        //Imgproc.line(image, new Point(0, 0), new Point(30, 40), new Scalar(0, 0, 255), 2);

        Mat dst = new Mat();
        Imgproc.adaptiveThreshold(image, dst, 125, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 11, 12);


        HighGui.imshow("原图", image);
        HighGui.imshow("水印", water);
        HighGui.imshow("自适应", dst);
        HighGui.waitKey();
    }


}
