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
    public Action slideHB3Action() {
        return new slideHB3();
    }
    public Action slideSpec1Action() {
        return new slideSpec1();
    }
    public Action slideSpec2Action() {
        return new slideSpec2();
    }
    public Action slideSpec3Action(){
        return new slideSpec3();
    }
    public Action slideSpec4Action(){
        return new slideSpec4();
    }
    public Action slideSpecNegAction(){
        return new slideSpecNeg();
    }
    public class slideSpecNeg implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(-72);
            return false;
        }
    }
    public class slideSpec4 implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(460);
            return false;
        }
    }
    public class slideSpec3 implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(500);
            return false;
        }
    }
    public class slideSpec2 implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(350);
            return false;
        }
    }
    public class slideSpec1 implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(520);
            return false;
        }
    }
    public class slideHB3 implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slides.setPosition(755);
            return false;
        }
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
            slides.setPosition(21);
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
