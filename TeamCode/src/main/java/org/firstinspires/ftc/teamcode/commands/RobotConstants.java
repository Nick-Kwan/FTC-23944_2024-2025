package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.config.Config;

public class RobotConstants {

    @Config
    public static class Mecanum {
        public static String leftFront = "FL";
        public static String leftRear = "BL";
        public static String rightRear = "BR";
        public static String rightFront = "FR";
    }

    @Config
    public static class S {
        public static String leftSlide = "leftSlide";
        public static String rightSlide = "rightSlide";

        /*public static double P = 0.0;
        public static double I = 0.0;
        public static double D = 0.0;
        public static double F = 0.0;*/


        // POSITIONS
        public static int idle = 0;
        public static int highBucket = 2500;
        public static int lowBucket = 1500;
        public static int highBar = 1720;
        public static int submersible = 1500;
    }

    @Config
    public static class Sr {
        public static String leftRSlide = "leftRSlide";
        public static String rightRSlide = "rightRSlide";

        /*public static double P = 0.000;
        public static double I = 0.00000;
        public static double D = 0.0;
        public static double F = 0.00000;*/



        // POSITIONS
        public static int idle = 0;
        public static int highBucket = 690;
        public static int lowBucket = 550;
        public static int highBar = 500;
        public static int submersible = 0;
    }

}