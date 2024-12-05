package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RClaw {
    private Servo servoRC;
    private double mid = 0.32;
    private double offset = 0.05;
    private double rotationTarget;

    public RClaw (HardwareMap hardwareMap)
    {
        servoRC = hardwareMap.get(Servo.class, "servoRC");
    }

    public void setRClawPositionMID() {servoRC.setPosition(mid);}

    /*public void incrementRotation(double increment){
        rotationTarget += increment;
    }

    public void setRotation(double rotationTarget) {
        servoRC.setPosition(rotationTarget);
    }*/
}
