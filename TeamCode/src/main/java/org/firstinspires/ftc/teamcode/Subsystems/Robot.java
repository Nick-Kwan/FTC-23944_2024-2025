package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.State;

public class Robot {
    public Mecanum driveTrain;
    public Claw servoClaw;
    public RClaw servoRClaw;
    public Slides s;
    public SlideRotation sr;
    public Arm aX;

    private State state;

    public Robot(HardwareMap hardwareMap){

        driveTrain = new Mecanum(hardwareMap);
        aX = new Arm(hardwareMap);
        servoClaw = new Claw(hardwareMap);
        servoRClaw = new RClaw(hardwareMap);
        s = new Slides(hardwareMap);
        sr = new SlideRotation(hardwareMap);

        state = State.IDLE;
        setPosition(state);
    }

    public void setPosition(State state){
        s.setPosition(state);
        sr.setPosition(state);

        this.state = state;
    }

    public State getState(){
        return state;
    }
}
