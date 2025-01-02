package org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.RClaw;

public class RClawActions {
    RClaw rClaw;
    public RClawActions(HardwareMap hardwareMap){
        rClaw = new RClaw(hardwareMap);
    }
    public Action rotClaw90Action(){
        return new clawRotate90();
    }
    public Action rotClawMIDAction() {
        return new clawRotateMID();
    }
    public Action rotClawHalfAction() {
        return new clawRotateHalf();
    }
    public class clawRotateHalf implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            rClaw.setRClawPosHalf();
            return false;
        }
    }
    public class clawRotate90 implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            rClaw.setRClawPosNine();
            return false;
        }
    }
    public class clawRotateMID implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            rClaw.setRClawPosMID();
            return false;
        }
    }
}
