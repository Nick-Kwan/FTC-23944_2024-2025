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
        TimerTask setBucketSlidesInDelay = new TimerTask() {
            @Override
            public void run() {
                bot.s.setPosition(835);
            }
        };

        TimerTask setBucketSlidesOut1Delay = new TimerTask() {
            @Override
            public void run() {
                bot.sr.setPosition(0);
            }
        };

        TimerTask setBucketSlidesOut2Delay = new TimerTask() {
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

        TimerTask setHighSpecIn1Delay = new TimerTask() {
            @Override
            public void run() {
                bot.sr.setPosition(720);
                bot.s.setPosition(27);
            }
        };

        TimerTask setHighSpecIn2Delay = new TimerTask() {
            @Override
            public void run() {
                bot.servoClaw.setClawPosition0();
                bot.sr.setPosition(0);
                bot.s.setPosition(0);
            }
        };

        TimerTask setHighSpecIn3Delay = new TimerTask() {
            @Override
            public void run() {
                bot.s.setPosition(0);
                bot.s.setPower0();
                bot.sr.setPower0();
                bot.servoClaw.setClawPosition1();
                bot.aX.setArmPosMID();
            }
        };

        // open claw
        if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
            bot.servoClaw.setClawPosition0();

        }

        // close claw
        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            bot.servoClaw.setClawPosition1();
        }

//        In Sub 2
        if (operator.wasJustPressed(GamepadKeys.Button.B)){
            bot.servoClaw.setClawPosition1();
            bot.servoRClaw.setRClawPosMID();
            bot.aX.setArmPosSUB();
            timer.schedule(setSubSlidesInDelay,500);
        }
        // rest from sub
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            bot.aX.setArmPosSUB();
            bot.servoRClaw.setRClawPosMID();
            bot.s.setPosition(0);
            timer.schedule(setSubSlidesOutDelay,750);
        }

//        Fish
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.servoClaw.setClawPosition0();
            timer.schedule(setFishDelay,250);

        }

        //        Rest High Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.Y)){
            bot.aX.setArmPosMID();
            bot.sr.setPosition(650);
            bot.s.setPosition(0);
            timer.schedule(setBucketSlidesOut1Delay,1000);
            timer.schedule(setBucketSlidesOut2Delay,1750);
        }

        //pick up specimen from wall
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.servoRClaw.setRClawPosMID();
            bot.aX.setArmPosWall();
            bot.servoClaw.setClawPosition0();
        }
        //transfer in between wall and high spec
        if (driver.wasJustPressed(GamepadKeys.Button.A)){
            bot.aX.setArmPosUPaBIT();
        }

        //High Bucket
        if (operator.wasJustPressed(GamepadKeys.Button.A)){
            bot.aX.setArmPosMID();
            bot.sr.setPosition(732);
            timer.schedule(setBucketSlidesInDelay,1500);
        }

        //        High Specimen In
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
            bot.aX.setArmPosUPaBIT();
            timer.schedule(setHighSpecIn1Delay,250);
        }

        // High Specimen Out
        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
            bot.aX.setArmPosSpec();
            timer.schedule(setHighSpecIn2Delay,90);
            timer.schedule(setHighSpecIn3Delay,1090);
        }
        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){
            bot.aX.setArmPosPreSpec();
        }
        if(driver.wasJustPressed(GamepadKeys.Button.Y)){
            bot.aX.setArmPosHB();
        }

        //Reset IMU
        if (driver.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.driveTrain.resetIMU();
        }
        if(driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0){
            bot.driveTrain.resetYaw();
        }

    }
}
