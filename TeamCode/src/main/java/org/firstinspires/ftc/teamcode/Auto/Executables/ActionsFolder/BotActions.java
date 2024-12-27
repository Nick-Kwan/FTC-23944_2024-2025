package org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BotActions {
    ClawActions clawActions;
    RClawActions rClawActions;
    ArmActions armActions;
    SlideActions slideActions;
    RSlideActions rSlideActions;
    public BotActions (HardwareMap hardwareMap) {
        clawActions = new ClawActions(hardwareMap);
        rClawActions = new RClawActions(hardwareMap);
        armActions = new ArmActions(hardwareMap);
        slideActions = new SlideActions(hardwareMap);
        rSlideActions = new RSlideActions(hardwareMap);
    }
    public Action clawCloseAction(){
        return clawActions.clawCloseAction();
    }
    public Action clawOpenAction(){
        return clawActions.clawOpenAction();
    }
    public Action armMidAction(){
        return armActions.armMIDAction();
    }
    public Action armWallAction(){
        return armActions.armWallAction();
    }
    public Action armInitAction(){
        return armActions.armInitAction();
    }
    public Action armHBAction(){
        return armActions.armHBAction();
    }
    public Action armUPaBITAction(){
        return armActions.armUPaBITAction();
    }
    public Action 
}
