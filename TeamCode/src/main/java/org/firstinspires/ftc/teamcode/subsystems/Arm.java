package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.*;

public class Arm implements Subsystem {
    private VoltageScaler voltageScaler;

    private ElapsedTime elapsedTime;

    private CRServo leftArm, rightArm;
    private Servo rotation, claw;

    private AnalogInput leftArmInput, rightArmInput;

    private PIDFController armPID;
    private double target, rotationTarget, delay;
    private boolean clawOpen;
    private double globalCorrection;


    /*private double mid = 0.92;
    private double wall = 0.60;
    private double init = 0.85;
    private double hb = 0.75; // hb = high bucket
    private double specimen = 0.45;
    private double upAbit = 0.45;
    private double slam = 0.35;*/

    public Arm(HardwareMap hardwareMap) {
        voltageScaler = new VoltageScaler(hardwareMap);

        elapsedTime = new ElapsedTime();

        rightArmInput = hardwareMap.get(AnalogInput.class, "rightArmInput");
        leftArmInput = hardwareMap.get(AnalogInput.class, "leftArmInput");

        armPID = new PIDFController(RobotConstants.Arm.P, RobotConstants.Arm.I, RobotConstants.Arm.D, RobotConstants.Arm.F);

        rotation = hardwareMap.servo.get(RobotConstants.Arm.rotation);
        claw = hardwareMap.servo.get(RobotConstants.Arm.claw);
    }

    public void setPosition(State state) {
        switch(state){
            case IDLE:
                setAssembly(RobotConstants.Arm.armIdle, RobotConstants.Arm.rotationIdle, false, 0);
                break;
            case SUB_INTAKING:
                setAssembly(RobotConstants.Arm.armSubmersible, RobotConstants.Arm.rotationIdle, true, 0);
                break;
            case SUB_GRABBING:
                setAssembly(RobotConstants.Arm.armSubmersibleGrab, rotationTarget, false, RobotConstants.Arm.submerisbleDelay);
                break;
            case TRANSFER:
                setAssembly(RobotConstants.Arm.armTransfer, RobotConstants.Arm.rotationTransfer, false, 0);
                break;
            case LOW_BUCKET:
            case HIGH_BUCKET:
                setAssembly(RobotConstants.Arm.armIdle, RobotConstants.Arm.rotationIdle, false, 0);
        }
    }

    public void updateAssembly() {
        setArmsTarget(target);
        updateArms();

        if (elapsedTime.time() > delay){
            setRotation(rotationTarget);
            setClaw(clawOpen);
        }
    }

    public void setAssembly(double target, double rotationTarget, boolean clawOpen, double delay) {
        this.target = target;
        this.rotationTarget = rotationTarget;
        this.clawOpen = clawOpen;
        this.delay = delay;
    }

    public void setClaw(Boolean clawOpen) {
        if (clawOpen) {
            openClaw();
        } else {
            closeClaw();
        }
    }

    public void actuateClaw(){
        if (claw.getPosition() == RobotConstants.Arm.clawClose) {
            openClaw();
        } else {
            closeClaw();
        }
    }

    public void closeClaw() {
        clawOpen = false;
        claw.setPosition(RobotConstants.Arm.clawClose);
    }

    public void openClaw(){
        clawOpen = true;
        claw.setPosition(RobotConstants.Arm.clawOpen);
    }

    public void incrementRotation(double increment) {
        rotationTarget += increment;
    }

    public void setRotation(double rotationTarget) {
        this.rotationTarget = rotationTarget;
        rotation.setPosition(rotationTarget);
    }

    public double getArmPosition(){
        return rightArmInput.getVoltage() / 3.3;
    }

    public void setArmsTarget(double target) {
        this.target = target;
    }

    public void updateArms() {
        double voltageCorrection = voltageScaler.getVoltageCorrection();

        double correction;
        correction = -armPID.calculate(getArmPosition(), target + voltageCorrection);

        globalCorrection = correction;

        rightArm.setPower(correction);
        leftArm.setPower(correction);
    }

    public double getCorrection(){
        return globalCorrection;
    }
}
