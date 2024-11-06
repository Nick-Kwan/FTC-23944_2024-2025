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
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();

        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.servoClaw.setClawPosition1();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.servoClaw.setClawPosition0();
        }

        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.servoRClaw.setClawPosition1();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
            bot.servoRClaw.setClawPosition0();
        }

        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
            bot.servoRClaw.setClawPositionMID();
            bot.aX.setArmPosition0();
        }
        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            bot.servoRClaw.setClawPositionMID();
            bot.aX.setArmPosition1();
        }
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
            bot.servoRClaw.setClawPositionMID();
            bot.aX.setArmPositionMID();
        }



        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.sr.setPosition(760);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(500);
        }
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.s.setPosition(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(0);
        }

        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }
    }
}
