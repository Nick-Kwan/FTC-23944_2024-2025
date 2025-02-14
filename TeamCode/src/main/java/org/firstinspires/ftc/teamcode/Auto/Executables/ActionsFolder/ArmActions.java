package org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;


public class ArmActions {
    Arm arm;
    public ArmActions(HardwareMap hardwareMap) {
        arm = new Arm(hardwareMap);
    }
    public Action armMIDAction() {
        return new armMID();
    }
    public Action armWallAction() {
        return new armWall();
    }
    public Action armInitAction() {
        return new armInit();
    }
    public Action armHBAction() {
        return new armHB();
    }
    public Action armUPaBITAction () {
        return new armUPaBIT();
    }
    public Action armAutoAction () {
        return new armAuto();
    }
    public Action armSpecAction () {
        return new armSpec();
    }
    public class armSpecAction implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosSpec();
            return false;
        }
    }
    public class armAuto implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosSAuto();
            return false;
        }
    }
    public class armMID implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosMID();
            return false;
        }
    }
    public class armWall implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosWall();
            return false;
        }
    }
    public class armInit implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosInit();
            return false;
        }
    }
    public class armHB implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosHB();
            return false;
        }
    }
    public class armUPaBIT implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosUPaBIT();
            return false;
        }
    }
    public class armSub implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosSUB();
            return false;
        }
    }
    public class armSpec implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.setArmPosSpec();
            return false;
        }
    }
}
