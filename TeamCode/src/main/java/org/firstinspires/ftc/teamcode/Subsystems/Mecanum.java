package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.text.DecimalFormat;

public class Mecanum {
    private DcMotorEx left_front, left_back, right_front, right_back;
    private double frontLeftPower, backLeftPower, frontRightPower, backRightPower, rotY, rotX, rx, x, y, denominator;
    private double offset = 1.1;

    DecimalFormat df = new DecimalFormat("#.##");
    // This rounds to two decimal places
    IMU imu;


    public Mecanum(HardwareMap hardwareMap) {
        left_front = hardwareMap.get(DcMotorEx.class, "left_front");
        left_back = hardwareMap.get(DcMotorEx.class, "left_back");
        right_front = hardwareMap.get(DcMotorEx.class, "right_front");
        right_back = hardwareMap.get(DcMotorEx.class, "right_back");


        left_front.setDirection(DcMotorEx.Direction.REVERSE);
        left_back.setDirection(DcMotorEx.Direction.REVERSE);


        imu = hardwareMap.get(IMU.class, "imu");
        // this is making a new object called 'parameters' that we use to hold the angle the imu is at
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        imu.initialize(parameters);
    }

    public void drive(GamepadEx gamepad1) {
        y = gamepad1.getLeftY();
        x = gamepad1.getLeftX();
        rx = gamepad1.getRightX();

        double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

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
    public void resetIMU() {resetIMU();}

    public double getHeading(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void resetHeading(){
        imu.resetYaw();
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
        imu.resetYaw();
    }

}
