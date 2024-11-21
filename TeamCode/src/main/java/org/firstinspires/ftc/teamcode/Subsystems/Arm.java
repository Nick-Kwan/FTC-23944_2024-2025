package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private Servo servoAL;
    private Servo servoAR;
    private double pos0 = 0;
    private double mid = 0.92;
    private double pos1 = 1;

    public Arm(HardwareMap hardwareMap)
    {
        servoAL = hardwareMap.get(Servo.class, "servoAL");
        servoAR = hardwareMap.get(Servo.class, "servoAR");
    }

    public void setArmPosition0(){servoAR.setPosition(pos0); servoAL.setPosition(pos0);}
    public void setArmPosition1(){servoAR.setPosition(pos1); servoAL.setPosition(pos1);}
    public void setArmPositionMID(){servoAL.setPosition(mid);}
    public void setALPosition0(){servoAL.setPosition(pos0);}
    public void setALPosition1(){servoAL.setPosition(pos1);}
    public void setARPosition0(){servoAR.setPosition(pos0);}
    public void setARPosition1(){servoAR.setPosition(pos1);}


}
