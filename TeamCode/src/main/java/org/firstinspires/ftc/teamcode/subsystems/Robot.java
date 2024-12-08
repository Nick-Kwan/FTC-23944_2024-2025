package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.State;

public class Robot {

    public Mecanum driveTrain;
    public Lift lift;
    public SlideRotation sr;
    public Arm arm;

    private State state;

    public Robot(HardwareMap hardwareMap){

        driveTrain = new Mecanum(hardwareMap);
        arm = new Arm(hardwareMap);
        lift = new Lift(hardwareMap);
        sr = new SlideRotation(hardwareMap);

        state = State.IDLE;
        setPosition(state);

    }

    public void setPosition(State state){
        lift.setPosition(state);
        arm.setPosition(state);
        //horizontalExtension.setPosition(state);

        this.state = state;
    }
    public State getState(){
        return state;
    }
}
