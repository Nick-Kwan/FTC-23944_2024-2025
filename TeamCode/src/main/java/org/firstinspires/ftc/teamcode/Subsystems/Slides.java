package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.ESlides;
import org.firstinspires.ftc.teamcode.Subsystems.SlideRotation;

public class Slides {
    private DcMotorEx rightSlide, leftSlide;
    private Robot bot;
    private double power = 0.75;

    public Slides(HardwareMap hardwaremap) {
        rightSlide = hardwaremap.get(DcMotorEx.class, "rightSlide");
        leftSlide = hardwaremap.get(DcMotorEx.class, "leftSlide");

        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        rightSlide.setTargetPositionTolerance(5);
        leftSlide.setTargetPositionTolerance(5);


    }

    public void setRSlidePosition(ExtensionState extensionState, ESlides eSlides) {
        switch (extensionState) {
            case retracted:
                break;
            case extending: {
                switch (eSlides) {
                    case rest:
                        bot.sr.setPosition(100);
                        setPosition(0);
                        break;
                    case vertical:
                        bot.sr.setPosition(1);
                        setPosition(1);
                        break;
                    case Horizontal:
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
            case extended:
                break;
        }
    }
    public void setPosition (int pos){
        leftSlide.setTargetPosition(pos);
        rightSlide.setTargetPosition(pos);

        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftSlide.setPower(power);
        rightSlide.setPower(power);
    }
    public int getPosition(){
        return (rightSlide.getCurrentPosition());
    }
    public void setPower0(){
        leftSlide.setPower(0);
        rightSlide.setPower(0);
    }

}
