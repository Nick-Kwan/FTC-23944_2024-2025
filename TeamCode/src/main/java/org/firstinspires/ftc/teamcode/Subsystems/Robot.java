package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public Mecanum driveTrain;
    public Claw servoClaw;
    public SlideRotation sr;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){

        this.telemetry = telemetry;

        driveTrain = new Mecanum(hardwareMap);
        servoClaw = new Claw(hardwareMap);
        sr = new SlideRotation(hardwareMap);
    }
}
