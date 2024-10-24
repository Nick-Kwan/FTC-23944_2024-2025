package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class RClaw {
    private Servo servoRC;
    private double pos0 = 0.25;
    private double mid = 0.5;
    private double pos1 = 0.75;

    public RClaw (HardwareMap hardwareMap)
    {
        servoRC = hardwareMap.get(Servo.class, "servoRC");
    }
    public void setClawPosition0() {servoRC.setPosition(pos0);}
    public void setClawPositionMID() {servoRC.setPosition(mid);}
    public void setClawPosition1(){servoRC.setPosition(pos1);}
}
