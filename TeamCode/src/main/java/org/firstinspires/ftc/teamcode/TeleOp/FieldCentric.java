package org.firstinspires.ftc.teamcode.TeleOp;


import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode
{
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Robot bot;


    @Override
    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);

        telemetry.addLine("boop");
        telemetry.update();

    }

    @Override
    public void loop()
    {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Slide Pos: "+bot.s.getPosition());
        telemetry.addLine("RotSlide Pos: "+bot.sr.getPosition());

        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();

        bot.servoRClaw.setClawPositionMID();


        if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
            bot.servoClaw.setClawPosition0();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            bot.servoClaw.setClawPosition1();
        }

//        In Sub 2
        if (operator.wasJustPressed(GamepadKeys.Button.B)){
            bot.sr.setPosition(70);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1000);
        }
        //        In Sub 1
        if (operator.wasJustPressed(GamepadKeys.Button.A)){
            bot.sr.setPosition(70);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(650);
        }

//        Fish
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.servoClaw.setClawPosition0();
            bot.sr.setPosition(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            bot.servoClaw.setClawPosition1();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(70);
        }
        //        Rest
        if (operator.wasJustPressed(GamepadKeys.Button.Y)){
            bot.s.setPosition(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(0);
        }

        //Low Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.X)){
            bot.sr.setPosition(550);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1100);
        }
        //        High Specimen
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.sr.setPosition(444);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1325);
        }
        //       High Slam
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.sr.setPosition(388);

            bot.s.setPosition(942);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.servoClaw.setClawPosition0();
        }
        //        Low Specimen
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.sr.setPosition(345);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(593);
        }
        //        Low Slam
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
            bot.sr.setPosition(293);

            bot.s.setPosition(481);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.servoClaw.setClawPosition0();
        }

        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }
    }
}
