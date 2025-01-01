package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class Claw {
    private Servo servoC;
    private double pos0 = 1;
    private double pos1 = 0.75;

    public Claw (HardwareMap hardwareMap)
    {
        servoC = hardwareMap.get(Servo.class, "servoC");
    }
    public void setClawPosition0() {servoC.setPosition(pos0);}
    public void setClawPosition1(){servoC.setPosition(pos1);}
}
