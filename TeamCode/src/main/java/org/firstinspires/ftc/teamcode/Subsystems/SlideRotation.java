package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideRotation {
    private DcMotorEx rightRSlide, leftRSlide;
    private double power = 0.75;
    int target, prevTarget;

    public SlideRotation(HardwareMap hardwaremap) {
        rightRSlide = hardwaremap.get(DcMotorEx.class, "rightRSlide");
        leftRSlide = hardwaremap.get(DcMotorEx.class, "leftRSlide");

        rightRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightRSlide.setDirection(DcMotorSimple.Direction.REVERSE);



        rightRSlide.setTargetPositionTolerance(5);
        leftRSlide.setTargetPositionTolerance(5);
    }

        public void setPosition (int pos){
            leftRSlide.setTargetPosition(pos);
            rightRSlide.setTargetPosition(pos);
            leftRSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftRSlide.setPower(power);
            rightRSlide.setPower(power);
        }
    public int getPosition(){
        return (leftRSlide.getCurrentPosition() + rightRSlide.getCurrentPosition())/2;
    }
    public void setPower0(){
        leftRSlide.setPower(0);
        rightRSlide.setPower(0);
    }

    public void resetEncoders(){
        rightRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightRSlide.setDirection(DcMotorSimple.Direction.REVERSE);



        rightRSlide.setTargetPositionTolerance(5);
        leftRSlide.setTargetPositionTolerance(5);
    }
    public void incrementSlides(double input) {
        setPosition((int)(rightRSlide.getCurrentPosition()+input));
    }
    public void setTarget(int position) {
        prevTarget = target;
        target = position;
    }

}
