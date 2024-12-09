package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.State;
import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.VoltageScaler;

public class Sr {

    private final DcMotorEx rightRSlide, leftRSlide;

    private final VoltageScaler voltageScaler;

    private final PIDFController srPID;
    private final PIDFController loweringSrPID;

    private State robotState;

    int target, prevTarget;

    public Sr(HardwareMap hardwaremap) {
        voltageScaler = new VoltageScaler(hardwaremap);

        leftRSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.Lift.leftSlide);
        rightRSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.Lift.rightSlide);

        leftRSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftRSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        //rightSlide.setTargetPositionTolerance(5);
        //leftSlide.setTargetPositionTolerance(5);

        srPID = new PIDFController(RobotConstants.Lift.P, RobotConstants.Lift.I, RobotConstants.Lift.D, RobotConstants.Lift.F);
        loweringSrPID = new PIDFController(RobotConstants.Lift.loweringP, RobotConstants.Lift.loweringI, RobotConstants.Lift.loweringD, RobotConstants.Lift.loweringF);

        leftRSlide.setPower(0);
        rightRSlide.setPower(0);

        setTarget(0);
    }

    public void setPosition(State state) {
        this.robotState = state;
        switch(state){
            case IDLE:
                setTarget(RobotConstants.Sr.idle);
                break;
            case HIGH_BUCKET:
                setTarget(RobotConstants.Sr.highBucket);
                break;
            case LOW_BUCKET:
                setTarget(RobotConstants.Sr.lowBucket);
                break;
            case HIGH_BAR:
                setTarget(RobotConstants.Sr.highBar);
                break;
            case SUB_INTAKING:
                setTarget(RobotConstants.Sr.submersible);
                break;
        }
    }

    public void setTarget(int position) {
        prevTarget = target;
        target = position;
    }

    public void powerRSlides(){
        double voltageCorrection = voltageScaler.getVoltageCorrection();

        double correction;

        if (prevTarget > target){
            correction = loweringSrPID.calculate(rightRSlide.getCurrentPosition(), target + voltageCorrection);
        } else {
            correction = srPID.calculate(rightRSlide.getCurrentPosition(), target + voltageCorrection);
        }

        if ((robotState == State.IDLE || robotState == State.SUB_GRABBING || robotState == State.SUB_INTAKING) && Math.abs(loweringSrPID.getPositionError()) < 30){
            rightRSlide.setPower(0);
            leftRSlide.setPower(0);
        } else {
            rightRSlide.setPower(correction);
            leftRSlide.setPower(correction);
        }
    }

    public void resetEncoder(){
        leftRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftRSlide.setPower(0);
        rightRSlide.setPower(0);

        rightRSlide.setDirection(DcMotorEx.Direction.REVERSE);
    }

    /*public void incrementSlides(double input) {
        setTarget((int)(rightRSlide.getCurrentPosition()+input));
    }*/

    public void altZeroPowerBehavior(){
        if (leftRSlide.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.BRAKE){
            setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } else {
            setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public DcMotor.ZeroPowerBehavior getZeroPowerBehavior(){
        return leftRSlide.getZeroPowerBehavior();
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        leftRSlide.setZeroPowerBehavior(behavior);
        rightRSlide.setZeroPowerBehavior(behavior);
    }

    public int getPosition(){
        return rightRSlide.getCurrentPosition();
    }

    public double getPositionError(){
        return loweringSrPID.getPositionError();
    }

    public double getSrPower(){
        return rightRSlide.getPower();
    }
}