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
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Slide Pos: "+bot.s.getPosition());
        telemetry.addLine("RotSlide Pos: "+bot.sr.getPosition());

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
        /*if (driver.wasJustPressed(GamepadKeys.Button.X)){
            bot.servoRClaw.incrementRotation(0.1);
        }
        if (driver.wasJustPressed(GamepadKeys.Button.B)){
            bot.servoRClaw.incrementRotation(-0.1);
        }*/

//        In Sub 2
        if (operator.wasJustPressed(GamepadKeys.Button.B)){
            bot.servoClaw.setClawPosition1();
            bot.servoRClaw.setRClawPositionMID();
            bot.aX.setArmPosSUB();
            bot.servoClaw.setClawPosition0();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1500);
        }
        //        In Sub 1
        /*if (operator.wasJustPressed(GamepadKeys.Button.A)){
            bot.servoClaw.setClawPosition0();
            bot.sr.setPosition(120);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(650);
        }*/

//        Fish
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.servoClaw.setClawPosition0();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.aX.setArmPos1();
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.servoClaw.setClawPosition1();
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.aX.setArmPosSUB();
        }
        //        Rest High
        if (driver.wasJustPressed(GamepadKeys.Button.B)){
            bot.s.setPosition(0);
            try {
                Thread.sleep(2750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPower42();
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
            try {
                Thread.sleep(450);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPower42();
            bot.sr.setPosition(0);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
            bot.sr.setPosition(600);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(2500);
            bot.aX.setArmPosHB();
        }
        //        High Specimen
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.sr.setPosition(485); // prev 465 worked
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1720);// 1650 perfect W/ GRIP
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPosFlip();
        }
        //       High Slam
       if (driver.wasJustPressed(GamepadKeys.Button.X)){
            bot.s.setPosition(400);
        }

        //all the way up
        if (driver.wasJustPressed(GamepadKeys.Button.Y)){
            bot.aX.setArmPosHB(); // try init next of too high
        }
        // up a bit
        if (driver.wasJustPressed(GamepadKeys.Button.A)) {
            bot.aX.setArmPosUPaBIT();
        }

        // rotate claw 90 degrees
        if (driver.wasJustPressed(GamepadKeys.Button.X)) {
            bot.servoRClaw.setRClawPosNine();
        }


        //        Low Slam
      /*  if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
            bot.sr.setPosition(240);

            bot.s.setPosition(481);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.servoClaw.setClawPosition0();
        }

       */
        //        Low Specimen
        /*if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.aX.setArmPosSpec();
            bot.sr.setPosition(690);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(532);
        }*/
        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }


    }
}
