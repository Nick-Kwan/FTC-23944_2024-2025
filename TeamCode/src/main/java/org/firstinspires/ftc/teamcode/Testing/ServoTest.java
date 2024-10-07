package org.firstinspires.ftc.teamcode.Testing;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoTest
{
    public void runServo() {
        //Declare servo
        Servo edge = hardwareMap.get(Servo.class, "Release Servo");
        edge.setPosition(.69);
        //Adds position data of servo to control hub
        telemetry.addData("Release Servo", edge.getPosition());
    }
}