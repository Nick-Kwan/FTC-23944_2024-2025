package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Commands.MecanumStates;


import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public Mecanum driveTrain;
    public Claw servoClaw;
    public RClaw servoRClaw;
    public Slides s;
    public SlideRotation sr;
    public Arm aX;

    Telemetry telemetry;

    public MecanumStates mecanumState;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){

        this.telemetry = telemetry;

        driveTrain = new Mecanum(hardwareMap);
        aX = new Arm(hardwareMap);
        servoClaw = new Claw(hardwareMap);
        servoRClaw = new RClaw(hardwareMap);
        s = new Slides(hardwareMap);
        sr = new SlideRotation(hardwareMap);

    }

    public void setMecanumState(MecanumStates mecanumState){
        // driveTrain.driveAngleLock(mecanumState, gamepad1);
        this.mecanumState = mecanumState;
    }
    public MecanumStates getMecanumState(){
        return mecanumState;
    }
    public void runMecanum(MecanumStates mecanumState, Gamepad gamepad1){
        driveTrain.driveAngleLock(mecanumState, gamepad1);
    }
}

