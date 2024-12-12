package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.commands.State;

public class SlideRotation {
    private DcMotorEx rightRSlide, leftRSlide;
    private double power = 0.75;
    private State robotState;
    int target, prevTarget;

    public SlideRotation(HardwareMap hardwaremap) {
        rightRSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.Sr.rightRSlide);
        leftRSlide = hardwaremap.get(DcMotorEx.class, RobotConstants.Sr.leftRSlide);

        rightRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftRSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        rightRSlide.setTargetPositionTolerance(5);
        leftRSlide.setTargetPositionTolerance(5);

    }

    public void setPosition(State state){
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
            case HIGH_SPECIMEN:
                setTarget(RobotConstants.Sr.highBar);
                break;
            case IN_SUB:
                setTarget(RobotConstants.Sr.submersible);
                break;
        }
    }
    public void setTarget(int position) {
        prevTarget = target;
        target = position;
    }

    public int getPosition(){
        return (rightRSlide.getCurrentPosition());
    }

    public void setPower0(){
        leftRSlide.setPower(0);
        rightRSlide.setPower(0);
    }

    public void powerRSlides() {
        if ((robotState == State.IDLE || robotState == State.IN_SUB)) {
            rightRSlide.setPower(0);
            leftRSlide.setPower(0);
        } else {
            rightRSlide.setPower(power);
            leftRSlide.setPower(power);
        }
    }

}
