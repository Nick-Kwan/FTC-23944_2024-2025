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
            new TranslationalVelConstraint(76),
            new AngularVelConstraint(3*(Math.PI/3))
    ));
    VelConstraint twoVelConstraint = new MinVelConstraint(Arrays.asList(
            new TranslationalVelConstraint(20),
            new AngularVelConstraint(3*(Math.PI/3))
    ));
    VelConstraint aVelConstraint = new MinVelConstraint(Arrays.asList(
            new TranslationalVelConstraint(65), // was 63
            new AngularVelConstraint(3*(Math.PI/3))
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
                        bot.armUPaBITAction(),
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(43.5, 11), 0,aVelConstraint,new ProfileAccelConstraint(-30,80))
                .afterTime(0, new ParallelAction(
                        bot.armSpecAction()
                ))
                .waitSeconds(0.175)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction(),
                        bot.rSlideDownAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.25)//was 0.5s
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.slideOffAction(),
                        bot.rSlideDownAction(),
                        bot.armWallAction()
                ))
                // scores first spec
                .splineToConstantHeading(new Vector2d(40,-28),Math.PI/2,aVelConstraint,new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(55,-28),0,aVelConstraint,new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(55,-40),0,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .waitSeconds(0)
                .splineToConstantHeading(new Vector2d(26,-40),0,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .afterTime(0,new ParallelAction(
                        bot.slideDownAction()
                ))
                // lets go of first sample
                .splineToConstantHeading(new Vector2d(54,-14),0,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(53,-51),-Math.PI/2,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .waitSeconds(0)
                .splineToConstantHeading(new Vector2d(27,-29),0,aVelConstraint, new ProfileAccelConstraint(-30,80)) // was y : 29
                .waitSeconds(0)
                .splineToConstantHeading(new Vector2d(15.5,-32),Math.PI,twoVelConstraint, new ProfileAccelConstraint(-80,10)) // prev 10
                // lets go of second sample

                .afterTime(0, new ParallelAction(
                        bot.clawCloseAction()
                ))
                // grabs preloaded (second) spec from wall
                .waitSeconds(0.5)
                .afterTime(0,new ParallelAction(
                        bot.armUPaBITAction()
                ))
                .waitSeconds(0.2)
                .afterTime(0,new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .splineToConstantHeading(new Vector2d(40,12),-5*Math.PI/8,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .afterTime(0, new ParallelAction(
                        bot.slideSpecAction(),
                        bot.armSpecAction()
                ))
                .waitSeconds(0.175)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction(),
                        bot.rSlideDownAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.25)//was 0.5s
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.slideOffAction(),
                        bot.rSlideDownAction(),
                        bot.armWallAction()
                ))
                // scores preloaded specimen
                .splineToConstantHeading(new Vector2d(24,-24),-Math.PI/2,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(15.5,-29),0,twoVelConstraint) // was 16.5

                .afterTime(0,new ParallelAction(
                        bot.clawCloseAction()
                ))
                // gets Third Spec from wall
                .waitSeconds(0.5)
                .afterTime(0,new ParallelAction(
                        bot.armUPaBITAction()
                ))
                .waitSeconds(0.2) // was 0.4
                .afterTime(0,new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .splineToConstantHeading(new Vector2d(28,11),-7*(Math.PI/8),aVelConstraint,new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(39,11),0,baseVelConstraint) // was 5 too uch to the right
                .afterTime(0, new ParallelAction(
                        bot.slideSpecAction(),
                        bot.armSpecAction()
                ))
                .waitSeconds(0.175)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction(),
                        bot.rSlideDownAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.25)//was 0.5s
                .afterTime(0, new ParallelAction(
                        bot.slideDownAction(),
                        bot.slideOffAction(),
                        bot.rSlideDownAction(),
                        bot.armWallAction()
                ))
                // scores 3rd spec
                .splineToConstantHeading(new Vector2d(28,-32),-Math.PI/2,aVelConstraint,new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(16.75,-32),0,twoVelConstraint, new ProfileAccelConstraint(-80,10)) // prev 18
                .afterTime(0,new ParallelAction(
                        bot.clawCloseAction()
                ))
                // gets Fourth Spec from wall
                .waitSeconds(0.75)
                .afterTime(0,new ParallelAction(
                        bot.armUPaBITAction()
                ))
                .waitSeconds(0.2) // was 0.4
                .afterTime(0,new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .splineToConstantHeading(new Vector2d(43,10.25),Math.PI/3,aVelConstraint, new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(43.5,10.25),0,baseVelConstraint,new ProfileAccelConstraint(-60,50)) // y 10 was too much to he right
                .afterTime(0, new ParallelAction(
                        bot.slideSpecAction(),
                        bot.armSpecAction()
                ))
                .waitSeconds(0.175)
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction(),
                        bot.rSlideDownAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.25)//was 0.5s
                .afterTime(0, new ParallelAction(
                        bot.slideSpecNegAction(),
                        bot.slideOffAction(),
                        bot.rSlideDownAction(),
                        bot.armMidAction(),
                        bot.clawCloseAction()
                ))
                // scores 4th spec
                //.splineToConstantHeading(new Vector2d(26,-32),0,aVelConstraint, new ProfileAccelConstraint(-30,60))
                .build();
    }
}