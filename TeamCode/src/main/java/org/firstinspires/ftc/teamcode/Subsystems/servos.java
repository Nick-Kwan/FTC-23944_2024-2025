package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class servos {

    @Override
    public void init() {
        public CRServo servo;
        servo = HardwareMap.get(CRServo.class, "servos");
    }
    @Override
    public void loop() {
        if (gamepad1.a){
            servo.setpower(-1);
        }
    }
}

