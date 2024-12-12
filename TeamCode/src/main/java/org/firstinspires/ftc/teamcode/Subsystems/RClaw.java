package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RClaw {
    private Servo servoRC;
    private double mid = 0.2; // prev 0.17
    private double flip = 0.85; // prev 0.92 left of North
    private double ninety = 0.5;
    private double offset = 0.05;
    private double rotationTarget;

    public RClaw (HardwareMap hardwareMap)
    {
        servoRC = hardwareMap.get(Servo.class, "servoRC");
    }

    public void setRClawPositionMID() {servoRC.setPosition(mid);}
    public void setRClawPosFlip(){servoRC.setPosition(flip);}
    public void setRClawPosNine(){servoRC.setPosition(ninety);}

}
