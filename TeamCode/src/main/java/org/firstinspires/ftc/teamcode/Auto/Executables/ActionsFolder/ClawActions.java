package org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class ClawActions {
    Claw claw;
    public ClawActions(HardwareMap hardwareMap){
        claw = new Claw(hardwareMap);
    }
    public Action closeClawAction(){
        return new closeClaw();
    }
    public Action openClawAction(){
        return new openClaw();
    }

    public class closeClaw implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setClawPosition1();
            return false;
        }
    }

    public class openClaw implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setClawPosition0();
            return false;
        }
    }
}
