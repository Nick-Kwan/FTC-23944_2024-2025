package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Commands.RotationState;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class SlideRotation {
    private DcMotorEx leftRSlide, rightRSlide;
    private double power = .05;

    public SlideRotation(HardwareMap hardwareMap) {
        rightRSlide = hardwareMap.get(DcMotorEx.class, "rightRSlide");
        leftRSlide = hardwareMap.get(DcMotorEx.class, "leftRSlide");

        rightRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        rightRSlide.setTargetPositionTolerance(5);
        leftRSlide.setTargetPositionTolerance(5);
    }

    public void setPosition(int pos) {
        leftRSlide.setTargetPosition(pos);
        rightRSlide.setTargetPosition(pos);

        leftRSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftRSlide.setPower(power);
        rightRSlide.setPower(power);
    }
}