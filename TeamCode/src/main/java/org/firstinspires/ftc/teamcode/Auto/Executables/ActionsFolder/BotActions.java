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
    public Action rClaw90Action(){
        return rClawActions.rotClaw90Action();
    }
    public Action rClawMIDAction(){
        return rClawActions.rotClawMIDAction();
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
    public Action armSpecAction(){
        return armActions.armSpecAction();
    }
    public Action armAutoAction() {
        return armActions.armAutoAction();
    }
    public Action rSlideOffAction(){
        return rSlideActions.slideRotationOFFAction();
    }
    public Action getRslidePosAction(){
        return rSlideActions.getSlideRotationPosAction();
    }
    public Action rSlideDownAction(){
        return rSlideActions.slideRotationDownAction();
    }
    public Action rSlideUpAction(){
        return rSlideActions.slideRotationUpAction();
    }
    public Action slideOffAction(){
        return slideActions.slideOFFAction();
    }
    public Action getSlidePosAction(){
        return slideActions.getSlidePosAction();
    }
    public Action slideDownAction(){
        return slideActions.slideDownAction();
    }
    public Action slideSpecAction(){
        return slideActions.slideSpecAction();
    }
    public Action slideHbAction(){
        return slideActions.slideHBAction();
    }
}
