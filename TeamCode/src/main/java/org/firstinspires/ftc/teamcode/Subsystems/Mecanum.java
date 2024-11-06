package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.text.DecimalFormat;

public class Mecanum {
    private DcMotorEx leftFront, leftBack, rightFront, rightBack;
    private double frontLeftPower, backLeftPower, frontRightPower, backRightPower, rotY, rotX, rx, x, y, denominator;
    private double offset = 1.1;

    DecimalFormat df = new DecimalFormat("#.##");
    // This rounds to two decimal places
    IMU imu;


    public Mecanum(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");


        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.REVERSE);


        imu = hardwareMap.get(IMU.class, "imu");
        // this is making a new object called 'parameters' that we use to hold the angle the imu is at
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
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
        leftFront.setPower(frontLeftPower * offset);
        leftBack.setPower(backLeftPower * offset);
        rightFront.setPower(frontRightPower * offset);
        rightBack.setPower(backRightPower * offset);
    }
    public void resetIMU()
    {
        resetIMU();
    }
}
