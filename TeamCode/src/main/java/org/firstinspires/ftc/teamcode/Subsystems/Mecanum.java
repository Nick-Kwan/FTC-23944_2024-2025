package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.Auto.RRdrives.PinpointDrive;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Mecanum {
    private DcMotorEx left_front, left_back, right_front, right_back;
    private double frontLeftPower, backLeftPower, frontRightPower, backRightPower, rotY, rotX, rx, x, y, denominator;
    private double offset = 1.1;

    GoBildaPinpointDriver imu;


    public Mecanum(HardwareMap hardwareMap) {
        left_front = hardwareMap.get(DcMotorEx.class, "left_front");
        left_back = hardwareMap.get(DcMotorEx.class, "left_back");
        right_front = hardwareMap.get(DcMotorEx.class, "right_front");
        right_back = hardwareMap.get(DcMotorEx.class, "right_back");


        left_front.setDirection(DcMotorEx.Direction.REVERSE);
        left_back.setDirection(DcMotorEx.Direction.REVERSE);


        // this is making a new object called 'parameters' that we use to hold the angle the imu is at
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        imu.initialize(parameters);
        imu = hardwareMap.get(GoBildaPinpointDriver.class, "imu");
    }

    public void drive(Gamepad gamepad1) {
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_x;

        double botHeading = -imu.getHeading();

        rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
        rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        frontLeftPower = (rotY + rotX + rx) / denominator;
        backLeftPower = (rotY - rotX + rx) / denominator;
        frontRightPower = (rotY - rotX - rx) / denominator;
        backRightPower = (rotY + rotX - rx) / denominator;
    }

    public void setMotorPower() {
        left_front.setPower(frontLeftPower * offset);
        left_back.setPower(backLeftPower * offset);
        right_front.setPower(frontRightPower * offset);
        right_back.setPower(backRightPower * offset);
    }

    public void setSlowMode() {
        left_front.setPower(frontLeftPower * 0.35);
        left_back.setPower(backLeftPower * 0.35);
        right_front.setPower(frontRightPower * 0.35);
        right_back.setPower(backRightPower * 0.35);
    }

    public double getHeading(){
        return imu.getHeading();
    }

    public void resetHeading(){
    }

    public double[] getMotorPowers(){
        return new double[] {
                frontLeftPower,
                backLeftPower,
                frontRightPower,
                backRightPower
        };
    }

    public double getMotorPower(){
        return frontLeftPower;
    }
    public void resetYaw(){
        imu.recalibrateIMU();
    }

}
