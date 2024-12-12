package org.firstinspires.ftc.teamcode.TeleOp;


import org.firstinspires.ftc.teamcode.commands.State;
import org.firstinspires.ftc.teamcode.commands.VoltageReader;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode {

    private Robot bot;

    private GamepadEx driver, operator;
    private VoltageReader voltageReader;
    double slideOverride;

    @Override
    public void init() {
        telemetry.addLine("Initializing");
        telemetry.update();

        bot = new Robot(hardwareMap);
        voltageReader = new VoltageReader(hardwareMap);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {

        telemetry.addLine("Running");
        telemetry.addData("Current State: ", bot.getState());

        telemetry.addData("\n Lift Position: ", bot.lift.getPosition());
        telemetry.addData("\n Lift Power: ", bot.lift.getLiftPower());
        telemetry.addData("\n Slide Rotation Position: ", bot.sr.getPosition());
        telemetry.addData("\n Slide Rotation Power: ", bot.sr.getSrPower());
        telemetry.addData("\n Arm Position: ", bot.arm.getArmPosition());
        telemetry.addData("\n Arm Correction: ", bot.arm.getCorrection());
        telemetry.addData("\n Slide PID Position Error: ", bot.lift.getPositionError());
        telemetry.addData("\n FL Motor Power: ", bot.driveTrain.getMotorPowers()[0]);
        telemetry.addData("\n BL Motor Power: ", bot.driveTrain.getMotorPowers()[1]);
        telemetry.addData("\n FR Motor Power: ", bot.driveTrain.getMotorPowers()[2]);
        telemetry.addData("\n BR Motor Power: ", bot.driveTrain.getMotorPowers()[3]);
        telemetry.update();

        driver.readButtons();
        operator.readButtons();


// --------------------------- DRIVER CODE --------------------------- //

        bot.driveTrain.drive(driver);

        // --------------------------- GLOBAL CONTROLS --------------------------- //

        bot.lift.powerSlides();
        bot.arm.updateAssembly();

        if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
            bot.arm.actuateClaw();
        }


        // --------------------------- SUBMERSIBLE LAYER --------------------------- //

        switch (bot.getState()) {
            case IDLE:

                // Go To Different States
                if (driver.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                    bot.setPosition(State.GROUND_INTAKING);
                }
                if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                    bot.setPosition(State.SUB_INTAKING);
                }
                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                    bot.setPosition(State.HIGH_BUCKET);
                }
                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
                    bot.setPosition(State.LOW_BUCKET);
                }
                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
                    bot.setPosition(State.HIGH_BAR);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.A)) {
                    bot.lift.resetEncoder();
                }

                break;
            case SUB_INTAKING:
            case SUB_GRABBING:

                // Return To Default
                if(driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
                    bot.setPosition(State.IDLE);
                }

                // didnt finish full code here
        }
    }
}
