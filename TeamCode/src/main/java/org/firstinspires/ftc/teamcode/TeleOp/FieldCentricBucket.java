package org.firstinspires.ftc.teamcode.TeleOp;


import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import java.util.Timer;
import java.util.TimerTask;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentricBucket extends OpMode
{
    private GamepadEx driver, operator;
    private Robot bot;
    private Timer timer;
    private double slidePositionThreshold = 400; // Position threshold for slow mode


    @Override
    public void init()
    {
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);
        timer = new Timer();

        telemetry.addLine("boop");
        telemetry.update();

        bot.aX.setArmPosInit();
        bot.sr.setPosition(-750);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bot.sr.setPower0();
        bot.servoRClaw.setRClawPosMID();
        bot.aX.setArmPosMID();
        bot.s.setPosition(-72);
        bot.sr.resetEncoders();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bot.s.resetEncoders();
        bot.s.setPower0();
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
        //bot.driveTrain.setMotorPower();



        double slidePosition = bot.s.getPosition(); // Get slide motor's current position
        boolean slowModeCondition = slidePosition > slidePositionThreshold;
        if (slowModeCondition || bot.aX.getArmPos() == 0.645) {
            bot.driveTrain.setSlowMode();
        } else {
            bot.driveTrain.setMotorPower();
            if (bot.driveTrain.getMotorPower() > 0.1 || bot.driveTrain.getMotorPower() < -0.1) {
                bot.s.setPosition(0);
                if (slidePosition <= 10) {
                    bot.s.setPower0();
                }
            }
        }
        if(bot.s.getPosition() < 100){
            bot.servoRClaw.setRClawPosMID();
        }


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
            bot.servoRClaw.setRClawPosMID();
            bot.aX.setArmPosSUB();
            //bot.servoClaw.setClawPosition0();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(590);
        }
        // rest from sub
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            bot.aX.setArmPosSUB();
            bot.servoRClaw.setRClawPosMID();
            bot.s.setPosition(0);
            try {
                Thread.sleep(750);
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
            bot.aX.setArmPosMID();
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
            bot.sr.setPosition(650);
            bot.s.setPosition(0);
            try {
                Thread.sleep(1000);
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
            bot.servoRClaw.setRClawPosMID();
        }

        //pick up specimen from wall
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.servoRClaw.setRClawPosMID();
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
            bot.sr.setPosition(732);
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
            bot.s.setPosition(27);
            bot.sr.setPosition(720); //
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.aX.setArmPosSpec();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.servoClaw.setClawPosition0();
            bot.sr.setPosition(0);
            bot.s.setPosition(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(0);
            bot.s.setPower0();
            bot.sr.setPower0();
            bot.servoClaw.setClawPosition1();
            bot.aX.setArmPosMID();
            bot.servoRClaw.setRClawPosMID();
        }


        // Level 2 Climb
        /*if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.aX.setArmPosMID();
            bot.sr.setPosition(700);
            try {
                Thread.sleep(2500); // prev 3500.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(310); // prev 1360
            try {
                Thread.sleep(1000); // prev 500
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.sr.setPosition(746); // prev 250
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(220);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bot.s.setPosition(0);
            bot.sr.setPosition(0);
        } */

        //if clawservo stuck
        if (driver.wasJustPressed(GamepadKeys.Button.Y)){
            bot.aX.setArmPosHB(); // try init next of too high
        }
        // up a bit
        if (driver.wasJustPressed(GamepadKeys.Button.A)) {
            bot.aX.setArmPosUPaBIT();
        }

        // rotate claw 90 degrees
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
            bot.servoRClaw.actuateClaw();
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
        if(driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0){
            bot.driveTrain.resetYaw();
        }

        TimerTask setBucketSlidesInDelay = new TimerTask() {
            @Override
            public void run() {
                bot.s.setPosition(835);
            }
        };

        TimerTask setBucketSlidesOutDelay = new TimerTask() {
            @Override
            public void run() {
                bot.sr.setPosition(0);
                timer.schedule(,)
            }
        };

        TimerTask useIn_BucketOut = new TimerTask() {
            @Override
            public void run() {
                bot.s.setPower0();
                bot.sr.setPower0();
                bot.servoClaw.setClawPosition1();
                bot.aX.setArmPosMID();
            }
        };
        TimerTask setSubSlidesInDelay = new TimerTask() {
            @Override
            public void run() {
                bot.s.setPosition(590);
            }
        };

        TimerTask setSubSlidesOutDelay = new TimerTask() {
            @Override
            public void run() {
                bot.s.setPower0();
                bot.aX.setArmPosMID();
            }
        };
        TimerTask setFishDelay = new TimerTask() {
            @Override
            public void run() {
                bot.aX.setArmPosMID();
            }
        };



    }
}
