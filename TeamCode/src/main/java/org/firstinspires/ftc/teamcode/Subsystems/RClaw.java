package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RClaw {
    private Servo servoRC;
    private double pos0 = 0.25;
    private double mid = 0.5;
    private double pos1 = 0.75;
    private double offset = 0.05;

    public RClaw (HardwareMap hardwareMap)
    {
        servoRC = hardwareMap.get(Servo.class, "servoRC");
    }
    public void setRClawPositionR() {servoRC.setPosition(servoRC.getPosition() + offset);}
    public void setRClawPositionL() {servoRC.setPosition(servoRC.getPosition() - offset);}

    public void setClawPosition0() {servoRC.setPosition(pos0);}
    public void setRClawPositionMID() {servoRC.setPosition(mid);}
    public void setClawPosition1(){servoRC.setPosition(pos1);}
}
