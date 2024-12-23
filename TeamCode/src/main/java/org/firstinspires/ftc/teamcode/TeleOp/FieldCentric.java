package org.firstinspires.ftc.teamcode.TeleOp;


import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
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
        bot.driveTrain.slideSlipFix(driver);
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
            //bot.servoClaw.setClawPosition0();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1500);
        }
        // rest from sub
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            bot.aX.setArmPosSUB();
            bot.s.setPosition(0);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPower0();
            bot.aX.setArmPosMID();
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
            /*try {
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
            bot.aX.setArmPosSUB();*/
        }
        //        Rest High Specimen
        /*if (driver.wasJustPressed(GamepadKeys.Button.B)){
            bot.sr.setPosition(0);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(0);
            bot.s.setPower0();
            bot.sr.setPower0();
            bot.servoClaw.setClawPosition1();
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPositionMID();
        }*/

        //        Rest High Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.Y)){
            bot.aX.setArmPosMID();

            bot.s.setPosition(0);
            try {
                Thread.sleep(3000);
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

        //pick up specimen from wall
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.servoRClaw.setRClawPositionMID();
            bot.aX.setArmPosWall();
            bot.servoClaw.setClawPosition0();
        }

        //Low Bucket
        /*if (operator.wasJustPressed(GamepadKeys.Button.X)){
            bot.sr.setPosition(550);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1500);
        }*/
        //High Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.A)){
            bot.aX.setArmPosMID();
            bot.sr.setPosition(720);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(835);
        }
        //        High Specimen
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.aX.setArmPosUPaBIT();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(720); //
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.aX.setArmPosSpec();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.servoClaw.setClawPosition0();
            bot.sr.setPosition(0);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(0);
            bot.s.setPower0();
            bot.sr.setPower0();
            bot.servoClaw.setClawPosition1();
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPositionMID();
        }

        // Retract Slides due to slides slipping out while robot is moving
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
            bot.s.setPosition(0);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPower0();
        }

        // Slowly Raise or Lower Slides
        /*if(operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
            bot.sr.incrementSlides(50);
        }
        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.sr.incrementSlides(-50);
        }*/
        // Level 2 Climb
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.aX.setArmPosWall();
            bot.sr.setPosition(390);
            try {
                Thread.sleep(2500); // prev 3500.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(1100); // prev 1360
            try {
                Thread.sleep(350); // prev 500
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(200); // prev 250
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(0);
        }

        //if clawservo stuck
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
