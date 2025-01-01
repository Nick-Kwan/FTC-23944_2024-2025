package org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.SlideRotation;

public class RSlideActions {
    SlideRotation slideRotation;

    public RSlideActions(HardwareMap hardwareMap){
        slideRotation = new SlideRotation(hardwareMap);
    }
    public Action slideRotationOFFAction(){
        return new rSlideOFF();
    }
    public Action getSlideRotationPosAction(){
        return new getSlideRotationPos();
    }
    public Action slideRotationDownAction() {
        return new slideRotationDown();
    }
    public Action slideRotationUpAction() {
        return new slideRotationUp();
    }
    public Action slideRotationHbRAction() {
        return new slideRotationHbRAction();
    }
    public class slideRotationHbRAction implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slideRotation.setPosition(650);
            return false;
        }
    }
    public class slideRotationUp implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slideRotation.setPosition(720);
            return false;
        }
    }
    public class slideRotationDown implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slideRotation.setPosition(0);
            return false;
        }
    }
    public class getSlideRotationPos implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slideRotation.getPosition();
            return false;
        }
    }
    public class rSlideOFF implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            slideRotation.setPower0();
            return false;
        }
    }
}
