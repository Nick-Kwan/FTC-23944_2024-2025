package org.firstinspires.ftc.teamcode.TeleOp;


import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

<<<<<<< HEAD

public class FieldCentric extends OpMode
{
    private GamepadEx goon;
=======



public class FieldCentric extends OpMode
{
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Robot bot;

>>>>>>> origin/Jonathan

    @Override
    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

<<<<<<< HEAD
        goon = new GamepadEx(gamepad1);
=======
>>>>>>> origin/Jonathan

        telemetry.addLine("Skibidi");
        telemetry.update();

    }

    @Override
    public void loop()
    {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.update();

<<<<<<< HEAD
        goon.readButtons();

=======
        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();

        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }


>>>>>>> origin/Jonathan
    }
}
