package com.example.statki;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Screenshot {
    protected static String fileSource;

    public Screenshot() {
        setFileSource();;
    }

    public static void setFileSource() {

        String currentTimestamp = String.valueOf(System.nanoTime());
        String format = ".jpg";

        fileSource =  currentTimestamp + format;
    }
    public static String getFileSource() {
        return fileSource;
    }

    public static void takeScreenShot(){
        Screenshot.setFileSource();
        System.out.println("Try to do screenshoot");
        System.out.println(Screenshot.getFileSource());
        //String currentTimestamp = new SimpleDateFormat("yyyymmddHHMMss").format(now());
        try {
            Robot robot = new Robot();
            Rectangle rect = new Rectangle(660,300,800,340); // x y width height
            //Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenShot = robot.createScreenCapture(rect);
            ImageIO.write(screenShot, "JPG", new File(Screenshot.getFileSource()));
            System.out.println("Screen shot was taken for: " + Screenshot.getFileSource());
            ;} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
