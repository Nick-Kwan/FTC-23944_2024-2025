package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public DriveTrain driveTrain;
    public Claw servoClaw;
    public ClawRotation servoRClaw;
    public Slides s;
    public SlideRotation sr;
    public Arm aX;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){

        this.telemetry = telemetry;

        driveTrain = new DriveTrain(hardwareMap);
        aX = new Arm(hardwareMap);
        servoClaw = new Claw(hardwareMap);
        servoRClaw = new ClawRotation(hardwareMap);
        s = new Slides(hardwareMap);
        sr = new SlideRotation(hardwareMap);

    }
}
