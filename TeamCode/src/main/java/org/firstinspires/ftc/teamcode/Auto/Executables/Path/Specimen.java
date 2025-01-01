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
@Autonomous(name = "specimen", group = "Autonomous")
public class Specimen extends LinearOpMode{
    PinpointDrive drive;
    TelemetryPacket tel = new TelemetryPacket();
    SequentialAction path;
    Pose2d start = new Pose2d(8.5,-8.25,Math.toRadians(180));
    BotActions bot;
    boolean running;
    VelConstraint baseVelConstraint = new MinVelConstraint(Arrays.asList(
            new TranslationalVelConstraint(20),
            new AngularVelConstraint(Math.PI/3)
    ));

    public enum AutoStates{
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




        while (opModeInInit()){
            telemetry.addLine("Initializing");
            telemetry.update();
        }
        waitForStart();
        while (opModeIsActive()){
            switch (autoStates){
                case followingPath:
                    running = path.run(tel);
                    if(!running) autoStates = AutoStates.idle;
                    break;
                case idle:
                    break;
            }
        }
    }
    public SequentialAction createPath(){
        return new SequentialAction(
                specimens()
        );
    }

    public Action specimens(){
        return drive.actionBuilder(start)
                .afterTime(0, new ParallelAction(
                        bot.rClawMIDAction(),
                        bot.clawCloseAction(),
                        bot.armAutoAction()
                ))
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(40, 7.5), Math.toRadians(0), new TranslationalVelConstraint(30))
                .afterTime(0, new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.armSpecAction()
                ))
                .waitSeconds(0.2)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction(),
                        bot.rSlideDownAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(1)
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.slideOffAction(),
                        bot.rSlideDownAction(),
                        bot.clawCloseAction(),
                        bot.armMidAction()
                ))
                .splineToConstantHeading(new Vector2d(40, -28.5), Math.PI/2, new TranslationalVelConstraint(50))
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(60, -35, Math.PI/2), Math.PI/2)
                .splineToConstantHeading(new Vector2d(27,-35),0, new TranslationalVelConstraint(30))
                .splineToConstantHeading(new Vector2d(70, -32), 0)
                .splineToConstantHeading(new Vector2d(60, -43), 0)
                .splineToConstantHeading(new Vector2d(27,-41),0, new TranslationalVelConstraint(30))
                .splineToLinearHeading(new Pose2d(48, -48, (3*Math.PI)/2), 0)
                .afterTime(0, new ParallelAction(
                        bot.slideSpecAction(),
                        bot.rClaw90Action(),
                        bot.clawOpenAction()
                ))
                .splineToConstantHeading(new Vector2d(49, -61.5), 0)
                .afterTime(0, new ParallelAction(
                        bot.clawCloseAction(),
                        bot.slideDownAction(),
                        bot.slideOffAction()
                ))
                .splineToLinearHeading(new Pose2d(21, -50, 0), 0)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction()
                ))
                .build();
    }
}