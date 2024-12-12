package org.firstinspires.ftc.teamcode.commands;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Auto.RRdrives.PinpointDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

public class RobotActions {
    public Robot bot;
    public PinpointDrive drive;

    public RobotActions(HardwareMap hardwareMap, PinpointDrive drive) {
        bot = new Robot(hardwareMap);

        this.drive = drive;
    }

    public Action setRobotState(State state) {
        return new setRobotState(state);
    }

    public class setRobotState implements Action {
        State state;
        public setRobotState(State state) {
            this.state = state;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            bot.setPosition(state);
            return false;
        }
    }
}