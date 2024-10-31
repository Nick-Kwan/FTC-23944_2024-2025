package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private Servo servoAL;
    private Servo servoAR;
    private double pos0 = 0.25;
    private double mid = 0.5;
    private double pos1 = 0.75;

    public Arm(HardwareMap hardwareMap)
    {
        servoAL = hardwareMap.get(Servo.class, "servoAL");
        servoAR = hardwareMap.get(Servo.class, "servoAR");
    }

    public void setClawPosition0(){servoAR.setPosition(pos0); servoAL.setPosition(pos0);}
    public void setClawPosition1(){servoAR.setPosition(pos1); servoAL.setPosition(pos1);}
    public void setClawPositionRMID(){servoAL.setPosition(mid);}


}
