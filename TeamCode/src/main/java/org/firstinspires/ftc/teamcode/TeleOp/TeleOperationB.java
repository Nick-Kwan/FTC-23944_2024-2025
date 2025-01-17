
package org.firstinspires.ftc.teamcode.TeleOp;


import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Timer;
import java.util.TimerTask;

@TeleOp(name="FieldCentricBucket", group="DriveModes")

public class TeleOperationB extends LinearOpMode {

    private Robot bot;

    @Override
    public void runOpMode() throws InterruptedException {

        bot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("boop");
        telemetry.update();

        bot.servoClaw.setClawPosition1();
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


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {
            bot.driveTrain.drive(gamepad1);
            if (gamepad1.left_trigger > 0.1) {
                bot.driveTrain.resetYaw();
            }

            Timer timer = new Timer();
            // timers
            TimerTask subOut = new TimerTask() {
                @Override
                public void run() {
                    // use t=350
                    bot.s.setPower0();
                    bot.aX.setArmPosMID();
                }
            };

            TimerTask fish = new TimerTask() {
                @Override
                public void run() {
                    // use t = 250
                    bot.aX.setArmPosMID();
                }
            };

            TimerTask hbR1 = new TimerTask() {
                @Override
                public void run() {
                    // use t = 750
                    bot.sr.setPosition(0);
                }
            };

            TimerTask hbR2 = new TimerTask() {
                @Override
                public void run() {
                    // use t = 250
                    bot.s.setPower0();
                    bot.sr.setPower0();
                    bot.aX.setArmPosMID();
                    bot.servoRClaw.setRClawPosMID();
                }
            };

            TimerTask hbIn = new TimerTask() {
                @Override
                public void run() {
                    // use t = 600
                    bot.s.setPosition(835);
                }
            };

            TimerTask HighSpec1Mod = new TimerTask() {
                @Override
                public void run() {
                    // use t = 250
                    bot.s.setPosition(42);
                    bot.sr.setPosition(720);
                }
            };

            TimerTask HighSpec1 = new TimerTask() {
                @Override
                public void run() {
                    // use t = 250
                    bot.s.setPosition(32);
                    bot.sr.setPosition(720);
                }
            };

            TimerTask HighSpec2 = new TimerTask() {
                @Override
                public void run() {
                    // use t = 1250
                    bot.aX.setArmPosSpec();
                }
            };
            TimerTask HighSpec3 = new TimerTask() {
                @Override
                public void run() {
                    // use t = 150
                    bot.servoClaw.setClawPosition0();
                    bot.sr.setPosition(0);
                    bot.s.setPosition(0);
                }
            };

            TimerTask HighSpec4 = new TimerTask() {
                @Override
                public void run() {
                    // use t = 1000
                    bot.s.setPosition(0);
                    bot.s.setPower0();
                    bot.sr.setPower0();
                    bot.servoClaw.setClawPosition1();
                    bot.aX.setArmPosMID();
                    bot.servoRClaw.setRClawPosMID();
                }
            };
            // slide reset
            double slidePosition = bot.s.getPosition(); // Get slide motor's current position
            boolean slowModeCondition = slidePosition > 400;
            if (slowModeCondition || bot.aX.getArmPos() == 0.645 || gamepad1.right_trigger > 0.1) {
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

            // controls

            // open claw
            if (gamepad1.left_bumper) {
                if ((bot.s.getPosition() < 50 && bot.sr.getPosition() < 50) || bot.aX.getArmPos() != 0.645 && bot.s.getPosition() < 50) {
                    bot.s.setPosition(51);
                }
                bot.servoClaw.setClawPosition0();
            }

            // close claw
            if (gamepad1.right_bumper) {
                bot.servoClaw.setClawPosition1();
            }

            // go into submersible
            if (gamepad2.b) {
                bot.servoClaw.setClawPosition1();
                bot.servoRClaw.setRClawPosMID();
                bot.aX.setArmPosSUB();
                // 100 ms timer was here
                bot.s.setPosition(590);
            }

            // rest from submersible
            if (gamepad2.dpad_down) {
                bot.servoClaw.setClawPosition1();
                bot.aX.setArmPosSUB();
                bot.servoRClaw.setRClawPosMID();
                bot.s.setPosition(0);
                timer.schedule(subOut, 350);
            }

            // Fish
            if (gamepad1.dpad_down) {
                bot.servoClaw.setClawPosition0();
                timer.schedule(fish, 250);
            }

            // Rest High Bucket
            if (gamepad2.y) {
                bot.aX.setArmPosMID();
                bot.sr.setPosition(650);
                bot.s.setPosition(0);
                bot.servoClaw.setClawPosition1();
                timer.schedule(hbR1, 750);
                timer.schedule(hbR2, 1000);
            }

            // pick up specimen from wall
            if (gamepad1.dpad_up) {
                bot.servoRClaw.setRClawPosMID();
                bot.aX.setArmPosWall();
                bot.servoClaw.setClawPosition0();
            }

            // High Bucket
            if (gamepad2.a) {
                bot.aX.setArmPosMID();
                bot.sr.setPosition(720);
                timer.schedule(hbIn, 600);
            }

            // High Specimen
            if (gamepad2.dpad_up) {
                bot.aX.setArmPosUPaBIT();
                timer.schedule(HighSpec1, 250);
                timer.schedule(HighSpec2, 1500);
                timer.schedule(HighSpec3, 1650);
                timer.schedule(HighSpec4, 2650);
            }

            // High Specimen Mod
            if (gamepad2.dpad_left) {
                bot.aX.setArmPosUPaBIT();
                timer.schedule(HighSpec1Mod, 250);
                timer.schedule(HighSpec2, 1500);
                timer.schedule(HighSpec3, 1650);
                timer.schedule(HighSpec4, 2650);
            }

            // score high bucket
            if (gamepad1.y) {
                bot.aX.setArmPosHB();
            }

            // up a bit
            if (gamepad1.a) {
                bot.aX.setArmPosUPaBIT();
            }

            // rotate claw 90 degrees
            if (gamepad1.dpad_right) {
                if (bot.s.getPosition() > 75) {
                    bot.servoRClaw.setRClawPosNine();
                }
            }
            if (gamepad1.dpad_left) {
                bot.servoRClaw.setRClawPosMID();
            }
        }
    }
}




