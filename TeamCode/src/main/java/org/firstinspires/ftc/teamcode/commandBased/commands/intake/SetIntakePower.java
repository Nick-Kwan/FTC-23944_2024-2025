package org.firstinspires.ftc.teamcode.commandBased.commands.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.subsystems.IntakeSubsystem;

public class SetIntakePower extends CommandBase {

    private final IntakeSubsystem m_intakeSubsystem;
    private final double power;

    public SetIntakePower(IntakeSubsystem intakeSubsystem, double power) {
        m_intakeSubsystem = intakeSubsystem;
        addRequirements(m_intakeSubsystem);
        this.power = power;
    }

    @Override
    public void initialize() {
        m_intakeSubsystem.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}