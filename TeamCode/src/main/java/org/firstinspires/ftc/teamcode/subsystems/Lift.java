package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.State;
import org.firstinspires.ftc.teamcode.commands.LiftPID;
import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.VoltageReader;
import org.firstinspires.ftc.teamcode.commands.VoltageScaler;

public class Lift {

    private final DcMotorEx rightSlide, leftSlide;

    private final VoltageScaler voltageScaler;

    private final PIDFController liftPID;
    private final PIDFController loweringLiftPID;

    private State robotState;

    int target, prevTarget;

    public Lift(HardwareMap hardwaremap) {
        voltageScaler = new VoltageScaler(hardwaremap);

        leftSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.Lift.leftSlide);
        rightSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.Lift.rightSlide);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        //rightSlide.setTargetPositionTolerance(5);
        //leftSlide.setTargetPositionTolerance(5);

        liftPID = new PIDFController(RobotConstants.Lift.P, RobotConstants.Lift.I, RobotConstants.Lift.D, RobotConstants.Lift.F);
        loweringLiftPID = new PIDFController(RobotConstants.Lift.loweringP, RobotConstants.Lift.loweringI, RobotConstants.Lift.loweringD, RobotConstants.Lift.loweringF);

        leftSlide.setPower(0);
        rightSlide.setPower(0);

        setTarget(0);
    }

    public void setPosition(State state) {
        this.robotState = state;
        switch(state){
            case IDLE:
                setTarget(RobotConstants.Lift.idle);
                break;
            case HIGH_BUCKET:
                setTarget(RobotConstants.Lift.highBucket);
                break;
            case LOW_BUCKET:
                setTarget(RobotConstants.Lift.lowBucket);
                break;
            case HIGH_BAR:
                setTarget(RobotConstants.Lift.highBar);
                break;
            case SUB_INTAKING:
                setTarget(RobotConstants.Lift.submersible);
                break;
        }
    }

    public void setTarget(int position) {
        prevTarget = target;
        target = position;
    }

    public void powerSlides(){
        double voltageCorrection = voltageScaler.getVoltageCorrection();

        double correction;

        if (prevTarget > target){
            correction = loweringLiftPID.calculate(rightSlide.getCurrentPosition(), target + voltageCorrection);
        } else {
            correction = liftPID.calculate(rightSlide.getCurrentPosition(), target + voltageCorrection);
        }

        if ((robotState == State.IDLE || robotState == State.SUB_GRABBING || robotState == State.SUB_INTAKING) && Math.abs(loweringLiftPID.getPositionError()) < 30){
            rightSlide.setPower(0);
            leftSlide.setPower(0);
        } else {
            rightSlide.setPower(correction);
            leftSlide.setPower(correction);
        }
    }

    public void resetEncoder(){
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftSlide.setPower(0);
        rightSlide.setPower(0);

        rightSlide.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void incrementSlides(double input) {
        setTarget((int)(rightSlide.getCurrentPosition()+input));
    }

    public void altZeroPowerBehavior(){
        if (leftSlide.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.BRAKE){
            setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } else {
            setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public DcMotor.ZeroPowerBehavior getZeroPowerBehavior(){
        return leftSlide.getZeroPowerBehavior();
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        leftSlide.setZeroPowerBehavior(behavior);
        rightSlide.setZeroPowerBehavior(behavior);
    }

    public int getPosition(){
        return rightSlide.getCurrentPosition();
    }

    public double getPositionError(){
        return loweringLiftPID.getPositionError();
    }

    public double getLiftPower(){
        return rightSlide.getPower();
    }
}