package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ESlides;
import org.firstinspires.ftc.teamcode.Commands.RotationState;

public class Slides {
     private DcMotorEx leftSlide, rightSlide;
     private double power = .05;
     private Robot bot;

    public Slides(HardwareMap hardwareMap) {
        rightSlide = hardwareMap.get(DcMotorEx.class, "rightRSlide");
        leftSlide = hardwareMap.get(DcMotorEx.class, "leftRSlide");

        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        rightSlide.setTargetPositionTolerance(5);
        leftSlide.setTargetPositionTolerance(5);
    }

    public void setRSlide(RotationState rotationState, ESlides eSlides) {
        switch (rotationState) {
            case stat:
                break;
            case rotating: {
                switch (eSlides) {
                    case rest:
                        bot.sr.setPosition(0);
                        setPosition(0);
                        break;
                    case vertical:
                        bot.sr.setPosition(1);
                        setPosition(1);
                        break;
                    case horizontal:
                        bot.sr.setPosition(2);
                        setPosition(2);
                        break;
                    case lowBasket:
                        bot.sr.setPosition(3);
                        setPosition(3);
                        break;
                    case lowClip:
                        bot.sr.setPosition(4);
                        setPosition(4);
                        break;
                    case upClip:
                        bot.sr.setPosition(5);
                        setPosition(5);
                        break;
                    case lowClimb:
                        bot.sr.setPosition(6);
                        setPosition(6);
                        break;
                }
            }
            case rotated:
                break;
        }
    }

    public void setPosition(int pos) {
        leftSlide.setTargetPosition(pos);
        rightSlide.setTargetPosition(pos);

        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftSlide.setPower(power);
        rightSlide.setPower(power);
    }
}
