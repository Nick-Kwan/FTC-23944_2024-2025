package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    private Servo servoC;
    private double aPos = 1;
    private double bPos = -1;

    public Claw (HardwareMap hardwareMap){

        servoC = hardwareMap.get(Servo.class, "servoC");

    }
    public void openClaw (){
        servoC.setPosition(aPos);
    }
    public void closeClaw (){
        servoC.setPosition(bPos);
    }
    public double getPosition()
    {
        return servoC.getPosition();
    }
}
