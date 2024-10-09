package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class Claw {
    private Servo servoC;
    private double pos = 0.75;

    public Claw (HardwareMap hardwareMap){
        servoC = hardwareMap.get(Servo.class, "servoC");


    }
    public void setClawPosition (){
        servoC.setPosition(pos);
        telemetry.addData("servoC", servoC.getPosition());
    }
}
