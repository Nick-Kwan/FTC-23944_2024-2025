package org.firstinspires.ftc.teamcode.Auto.Executables.Path;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.Executables.ActionsFolder.BotActions;
import org.firstinspires.ftc.teamcode.Auto.RRdrives.PinpointDrive;

import java.util.Arrays;

@Config
@Autonomous(name = "bucket", group = "Autonomous")
public class Bucket extends LinearOpMode {
    PinpointDrive drive;
    TelemetryPacket tel = new TelemetryPacket();
    SequentialAction path;
    Pose2d start = new Pose2d(8.5, 8.25, 0);
    BotActions bot;
    boolean running;
    VelConstraint baseVelConstraint = new MinVelConstraint(Arrays.asList(
            new TranslationalVelConstraint(60),
            new AngularVelConstraint(Math.PI / 3)
    ));

    public enum AutoStates {
        followingPath, idle
    }

    AutoStates autoStates = AutoStates.followingPath;

    //x is forward
    //y is left and right, right is negative
    @Override
    public void runOpMode() throws InterruptedException {
        bot = new BotActions(hardwareMap);
        drive = new PinpointDrive(hardwareMap, start);
        path = createPath();


        while (opModeInInit()) {
            telemetry.addLine("Initializing");
            telemetry.update();
        }
        waitForStart();
        while (opModeIsActive()) {
            switch (autoStates) {
                case followingPath:
                    running = path.run(tel);
                    if (!running) autoStates = AutoStates.idle;
                    break;
                case idle:
                    break;
            }
        }
    }

    public SequentialAction createPath() {
        return new SequentialAction(
                buckets()
        );
    }

    public Action buckets() {
        return drive.actionBuilder(start)
                .afterTime(0, new ParallelAction(
                        bot.rClawMIDAction(),
                        bot.clawCloseAction(),
                        bot.armAutoAction()
                ))
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(14, 27, -Math.PI/4), Math.PI/2)
                .afterTime(0, new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.armMidAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideHbAction()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.armHBAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.rSlideHbRAction(),
                        bot.armMidAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.rSlideDownAction(),
                        bot.slideOffAction()
                ))
                .splineToLinearHeading(new Pose2d(32.5, 23.5, 0), 0) // 31 was on back edge of block
                .afterTime(0, new ParallelAction(
                        bot.rSlideOffAction()
                ))
                .waitSeconds(0.25)
                .afterTime(0, new ParallelAction(
                        bot.clawCloseAction()
                ))
                //gets first sample off floor
                .waitSeconds(0.25)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(16, 26, -Math.PI/4), Math.PI/2)
                .afterTime(0, new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.armMidAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideHbAction()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.armHBAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.rSlideHbRAction(),
                        bot.armMidAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.rSlideDownAction(),
                        bot.slideOffAction()
                ))
                .splineToLinearHeading(new Pose2d(32, 34, 0), 0)
                .afterTime(0, new ParallelAction(
                        bot.rSlideOffAction()
                ))
                .waitSeconds(0.25)
                .afterTime(0, new ParallelAction(
                        bot.clawCloseAction()
                ))
                .setTangent(0)
                .waitSeconds(0.25)
                .splineToLinearHeading(new Pose2d(14, 27, -Math.PI/4), Math.PI/2)
                .afterTime(0, new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.armMidAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideHbAction()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.armHBAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.rSlideHbRAction(),
                        bot.armMidAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.rSlideDownAction(),
                        bot.rSlideOffAction(),
                        bot.slideOffAction()
                ))
                .splineToLinearHeading(new Pose2d(15, 27.5, Math.toRadians(20)),0) // was 23 deg
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideHb3Action()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.clawCloseAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction()
                ))
                .splineToLinearHeading(new Pose2d(12, 28, -Math.PI/4), Math.PI/2)
                .afterTime(0, new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.armMidAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideHbAction()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.armHBAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction()
                ))
                .waitSeconds(0.5)
                .afterTime(0, new ParallelAction(
                        bot.rSlideHbRAction(),
                        bot.armMidAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.75)
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.slideOffAction(),
                        bot.rSlideUpAction()
                ))
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(66, -4, (Math.PI)/2), -Math.PI, new TranslationalVelConstraint(85))
                .afterTime(0, new ParallelAction(
                        bot.armHBAction()
                ))
                .build();
    }
}
