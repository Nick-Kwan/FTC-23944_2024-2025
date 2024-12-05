package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private Servo servoAL;
    private Servo servoAR;
    private double mid = 0.92;
    private double wall = 0.65;
    private double init = 0.85;

    public Arm(HardwareMap hardwareMap)
    {
        servoAL = hardwareMap.get(Servo.class, "servoAL");
        servoAR = hardwareMap.get(Servo.class, "servoAR");
    }


    public void setArmPosMID(){servoAL.setPosition(mid); servoAR.setPosition(mid);}
    public void setArmPosWall(){servoAL.setPosition(wall); servoAR.setPosition(wall);}
    public void setArmPosInit(){servoAL.setPosition(init); servoAR.setPosition(init);}




}
