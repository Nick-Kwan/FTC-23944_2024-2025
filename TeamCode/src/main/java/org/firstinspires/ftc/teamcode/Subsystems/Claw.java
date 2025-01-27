package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    private Servo servoC;
    private double pos0 = 0.63;
    private double pos1 = 0.26;
    private double loose = 0.3;

    public Claw (HardwareMap hardwareMap)
    {
        servoC = hardwareMap.get(Servo.class, "servoC");
    }
    public void setClawPosition0() {servoC.setPosition(pos0);}
    public void setClawPosition1(){servoC.setPosition(pos1);}
    public void setClawPosLoose(){servoC.setPosition(loose);}
}
