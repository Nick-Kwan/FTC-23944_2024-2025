package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.State;

public class Slides {
    private DcMotorEx rightSlide, leftSlide;
    private double power = 0.75;
    private State robotState;
    int target, prevTarget;

    public Slides(HardwareMap hardwaremap) {
        rightSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.S.rightSlide);
        leftSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.S.leftSlide);

        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        rightSlide.setTargetPositionTolerance(5);
        leftSlide.setTargetPositionTolerance(5);

    }

    public void setPosition(State state){
        this.robotState = state;
        switch(state){
            case IDLE:
                setTarget(RobotConstants.S.idle);

                break;
            case HIGH_BUCKET:
                setTarget(RobotConstants.S.highBucket);
                break;
            case LOW_BUCKET:
                setTarget(RobotConstants.S.lowBucket);
                break;
            case HIGH_SPECIMEN:
                setTarget(RobotConstants.S.highBar);
                break;
            case IN_SUB:
                setTarget(RobotConstants.S.submersible);
                break;
        }
    }
    public void setTarget(int position) {
        prevTarget = target;
        target = position;
    }

    public int getPosition(){
        return (rightSlide.getCurrentPosition());
    }

    public void setPower0(){
        leftSlide.setPower(0);
        rightSlide.setPower(0);
    }

    public void powerSlides() {
        if ((robotState == State.IDLE || robotState == State.IN_SUB)) {
            rightSlide.setPower(0);
            leftSlide.setPower(0);
        } else {
            rightSlide.setPower(power);
            leftSlide.setPower(power);
        }
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

}
