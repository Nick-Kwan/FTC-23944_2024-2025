package org.firstinspires.ftc.teamcode.TeleOp;


import org.firstinspires.ftc.teamcode.commands.State;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode
{

    private GamepadEx driver, operator;
    private Robot bot;

    @Override
    public void init()
    {
        telemetry.addLine("Initializing");
        telemetry.update();

        bot = new Robot(hardwareMap);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);

        telemetry.addLine("boop");
        telemetry.update();

        bot.aX.setArmPosInit();
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bot.servoRClaw.setRClawPositionMID();
        bot.aX.setArmPosMID();

    }

    @Override
    public void loop()
    {
        telemetry.addLine("Running");
        telemetry.addData("Current State: ", bot.getState());

        telemetry.addData("\n Slide Position: ", bot.s.getPosition());
        //telemetry.addData("\n Slide Power: ", bot.s.getPower());
        telemetry.addData("\n Slide Rotation Position: ", bot.sr.getPosition());
        //telemetry.addData("\n Slide Rotation Power: ", bot.sr.getSrPower());
        telemetry.addData("\n FL Motor Power: ", bot.driveTrain.getMotorPowers()[0]);
        telemetry.addData("\n BL Motor Power: ", bot.driveTrain.getMotorPowers()[1]);
        telemetry.addData("\n FR Motor Power: ", bot.driveTrain.getMotorPowers()[2]);
        telemetry.addData("\n BR Motor Power: ", bot.driveTrain.getMotorPowers()[3]);
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();

        if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
            bot.servoClaw.setClawPosition0();

        }
        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            bot.servoClaw.setClawPosition1();
        }

//        In Sub 2
        if (operator.wasJustPressed(GamepadKeys.Button.B)){
            bot.servoClaw.setClawPosition1();
            bot.servoRClaw.setRClawPositionMID();
            bot.aX.setArmPosSUB();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1500);
        }

//        Fish
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.servoClaw.setClawPosition0();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.aX.setArmPos1();
        }
        //        Rest High
        if (driver.wasJustPressed(GamepadKeys.Button.B)){
            bot.s.setPosition(0);
            try {
                Thread.sleep(2750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(0);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPower0();
            bot.sr.setPower0();
            bot.servoClaw.setClawPosition1();
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPositionMID();
        }
        //        Rest Low
        if (operator.wasJustPressed(GamepadKeys.Button.Y)){
            bot.s.setPosition(0);
            if (bot.s.getPosition() < 100) {
                bot.sr.setPosition(0);
            }

            bot.s.setPower0();
            bot.sr.setPower0();
            bot.servoClaw.setClawPosition1();
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPositionMID();
        }
        //pick up specimen from wall
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.servoRClaw.setRClawPositionMID();
            bot.aX.setArmPosWall();
            bot.servoClaw.setClawPosition0();
        }

        //Low Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.X)){
            bot.sr.setPosition(550);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1500);
        }
        //High Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.A)){
            bot.aX.setArmPosMID();
            bot.sr.setPosition(690); // Doing high bucket from behind he robot right now
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(2500);
            bot.aX.setArmPosUPaBIT();
        }
        //        High Specimen
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.sr.setPosition(500); // prev 485 worked
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1720);// 1650 perfect W/ GRIP
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPosFlip();
        }

        //all the way up
        if (driver.wasJustPressed(GamepadKeys.Button.Y)){
            bot.aX.setArmPosHB(); // try init next of too high
        }
        // up a bit
        if (driver.wasJustPressed(GamepadKeys.Button.A)) {
            bot.aX.setArmPosUPaBIT();
        }

        /*// rotate claw 90 degrees
        if (driver.wasJustPressed(GamepadKeys.Button.X)) {
            bot.servoRClaw.setRClawPosNine();
        }*/

        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }


    }
}
