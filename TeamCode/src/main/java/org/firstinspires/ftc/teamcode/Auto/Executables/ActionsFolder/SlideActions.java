package org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Slides;

public class SlideActions {
    Slides slides;

    public SlideActions(HardwareMap hardwareMap){
        slides = new Slides(hardwareMap);
    }
    public Action slideOFFAction(){
        return new SlideActions.slidesOFF();
    }
    public Action getSlidePosAction(){
        return new SlideActions.getSlidePos();
    }
    public Action slideDownAction() {
        return new SlideActions.slidesDown();
    }
    public Action slideSpecAction() {
        return new SlideActions.slideSpec();
    }
    public Action slideHBAction() {
        return new slideHB();
    }
    public class slideHB implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(835);
            return false;
        }
    }
    public class slideSpec implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(27);
            return false;
        }
    }
    public class slidesDown implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(0);
            return false;
        }
    }
    public class getSlidePos implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.getPosition();
            return false;
        }
    }
    public class slidesOFF implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPower0();
            return false;
        }
    }

}
