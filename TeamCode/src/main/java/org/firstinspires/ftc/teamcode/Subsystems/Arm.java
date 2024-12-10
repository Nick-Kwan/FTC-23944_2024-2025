package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private Servo servoAL;
    private Servo servoAR;
    private double mid = 0.92;
    private double wall = 0.60;
    private double init = 0.85;
    private double hb = 0.75; // hb = high bucket
    private double upAbit = 0.45;
    private double sub = 0.8;
    private double pos1 = 1;

    public Arm(HardwareMap hardwareMap)
    {
        servoAL = hardwareMap.get(Servo.class, "servoAL");
        servoAR = hardwareMap.get(Servo.class, "servoAR");
    }


    public void setArmPosMID(){servoAL.setPosition(mid); servoAR.setPosition(mid);}
    public void setArmPosWall(){servoAL.setPosition(wall); servoAR.setPosition(wall);}
    public void setArmPosInit(){servoAL.setPosition(init); servoAR.setPosition(init);}
    public void setArmPosHB(){servoAL.setPosition(hb); servoAR.setPosition(hb);}
    public void setArmPosUPaBIT(){servoAL.setPosition(upAbit); servoAR.setPosition(upAbit);}
    public void setArmPosSUB(){servoAL.setPosition(sub); servoAR.setPosition(sub);}
    public void setArmPos1(){servoAL.setPosition(pos1); servoAR.setPosition(pos1);}










}
