package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;

public class Claw {
    private ServoEx servoC;
    private double pos = 0.75;

    public Claw (HardwareMap hardwareMap){
        servoC = hardwareMap.get(ServoEx.class, "servoC");


    }
    public void setClawPosition (){
        servoC.setPosition(pos);
        telemetry.addData("servoC", servoC.getPosition());
    }
}
