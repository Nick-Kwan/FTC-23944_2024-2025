package org.firstinspires.ftc.teamcode.TeleOp;


import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import


public class FieldCentric extends OpMode
{
    private GamepadEx goon;

    @Override
    public void init()
    {

        goon = new GamepadEx(gamepad1);

        telemetry.addLine("Skibidi");

    }

    @Override
    public void loop()
    {

        goon.readButtons();

    }
}
