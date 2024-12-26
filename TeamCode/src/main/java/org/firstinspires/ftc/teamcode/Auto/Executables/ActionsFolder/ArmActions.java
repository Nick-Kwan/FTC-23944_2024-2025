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
}
