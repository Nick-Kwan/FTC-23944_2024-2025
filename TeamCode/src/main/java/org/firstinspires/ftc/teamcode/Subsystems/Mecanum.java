package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


public class Mecanum {
    private DcMotorEx left_front, left_back, right_front, right_back;
    private double frontLeftPower, backLeftPower, frontRightPower, backRightPower, rotY, rotX, rx, x, y, denominator;
    private double offset = 1.1;


    BNO055IMU imu;
    BNO055IMU.Parameters parameters;

    public Mecanum(HardwareMap hardwareMap) {
        left_front = hardwareMap.get(DcMotorEx.class, "left_front");
        left_back = hardwareMap.get(DcMotorEx.class, "left_back");
        right_front = hardwareMap.get(DcMotorEx.class, "right_front");
        right_back = hardwareMap.get(DcMotorEx.class, "right_back");


        left_front.setDirection(DcMotorEx.Direction.REVERSE);
        left_back.setDirection(DcMotorEx.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
    }

    public void drive(Gamepad gamepad1) {
        y = gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_x;

        double botHeading = imu.getAngularOrientation().firstAngle;

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
    public void setMidMode() {
        left_front.setPower(frontLeftPower * 0.5);
        left_back.setPower(backLeftPower * 0.5);
        right_front.setPower(frontRightPower * 0.5);
        right_back.setPower(backRightPower * 0.5);
    }

    public double getHeading(){
        return imu.getAngularOrientation().firstAngle;
    }

    public double[] getMotorPowers(){
        return new double[] {
                frontLeftPower,
                backLeftPower,
                frontRightPower,
                backRightPower
        };
    }
    public double[] getCurrent() {
        return new double[]{
                left_front.getCurrent(CurrentUnit.AMPS),
                left_back.getCurrent(CurrentUnit.AMPS),
                right_front.getCurrent(CurrentUnit.AMPS),
                right_back.getCurrent(CurrentUnit.AMPS),
        };
    }
    public double getMotorPower(){
        return frontLeftPower;
    }

    public void resetIMU(){
        imu.initialize(parameters);
    }

}
