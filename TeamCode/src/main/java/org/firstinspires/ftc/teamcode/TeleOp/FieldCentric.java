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
            bot.aX.setArmPosition0();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            bot.aX.setArmPosition1();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.aX.setALPosition0();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
            bot.aX.setALPosition1();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.X)){
            bot.aX.setARPosition0();
        }
        if (driver.wasJustPressed(GamepadKeys.Button.B)){
            bot.aX.setARPosition1();
        }


        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }


    }
}
