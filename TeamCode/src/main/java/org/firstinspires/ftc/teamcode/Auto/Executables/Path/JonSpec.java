package org.firstinspires.ftc.teamcode.Auto.Executables.Path;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.AccelConstraint;
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
@Autonomous(name = "JonSpec", group = "Autonomous")
public class JonSpec extends LinearOpMode{
    PinpointDrive drive;
    TelemetryPacket tel = new TelemetryPacket();
    SequentialAction path;
    Pose2d start = new Pose2d(8.5,-8.125,Math.toRadians(180));
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
    VelConstraint v = new MinVelConstraint(Arrays.asList(
            new TranslationalVelConstraint(75), // was 63
            new AngularVelConstraint(3*(Math.PI/3))
    ));
    VelConstraint v2 = new MinVelConstraint(Arrays.asList(
            new TranslationalVelConstraint(90),
            new AngularVelConstraint(3*(Math.PI/3))
    ));
    AccelConstraint a = new ProfileAccelConstraint(-25,100);

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
                        bot.slideSpecSevenAction(),
                        bot.rClawFlipAction(),
                        bot.armWallAction()
                ))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(40, 4), -Math.PI/2,v,a) // all accel constraints were -25, 100
                .afterTime(0, new ParallelAction(
                        bot.armUPaBITAction()
                ))
                .waitSeconds(0.125) // 0.25 worked
                .afterTime(0, new ParallelAction(
                        bot.clawOpenAction(),
                        bot.rSlideDownAction(),
                        bot.slideDownAction()
                ))
                .waitSeconds(0.125) // 0.25 worked
                .afterTime(0, new ParallelAction(
                        bot.rClawMIDAction(),
                        bot.slideFiftyAction()
                ))
                .waitSeconds(0.675)
                .afterTime(0, new ParallelAction(
                        bot.rSlideOffAction(),
                        bot.armWallAction(),
                        bot.clawOpenAction(),
                        bot.slideDownAction(),
                        bot.slideOffAction()

                ))
                // scores first spec
                .splineToConstantHeading(new Vector2d(40,-27),(Math.PI)/4,v,a)
                .splineToConstantHeading(new Vector2d(55,-27),(Math.PI)/4,v,a)
                .splineToConstantHeading(new Vector2d(55,-39),-Math.PI/2,v,a)
                .waitSeconds(0)
                .lineToX(22,v2,a)
                .afterTime(0,new ParallelAction(
                        bot.slideDownAction()
                ))
                // lets go of first sample
                .lineToX(55,v,a)
//                .splineToConstantHeading(new Vector2d(53,-14), 0,aVelConstraint, new ProfileAccelConstraint(-25,100)) // was x 56
                .splineToConstantHeading(new Vector2d(53,-48),-Math.PI/2,v,a) //was x 56, y : -50
                .waitSeconds(0)
                .lineToX(26,v2,a)
                // lets go of second sample
                .splineToConstantHeading(new Vector2d(57,-40),Math.toRadians(270),v,new ProfileAccelConstraint(-25,100))
                .waitSeconds(0)
                .splineToConstantHeading(new Vector2d(57,-53.15),-Math.PI/2,v,new ProfileAccelConstraint(-25,100))
                .waitSeconds(0)
                .lineToX(17,v2,a)
                // lets go of third sample
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
                .splineToConstantHeading(new Vector2d(42,10),-5*Math.PI/8,v, new ProfileAccelConstraint(-30,80))
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
                .splineToConstantHeading(new Vector2d(24,-24),-Math.PI/2,v, new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(16.55,-29),0,twoVelConstraint) // was 16.5

                .afterTime(0,new ParallelAction(
                        bot.clawCloseAction()
                ))
                // gets Third Spec from wall
                .waitSeconds(0.75)
                .afterTime(0,new ParallelAction(
                        bot.armUPaBITAction()
                ))
                .waitSeconds(0.2) // was 0.4
                .afterTime(0,new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .splineToConstantHeading(new Vector2d(28,8.75),-7*(Math.PI/8),v,new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(39,8.75),0,baseVelConstraint) // was 9.75 too uch to the right
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
                .splineToConstantHeading(new Vector2d(28,-32),-Math.PI/2,v,new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(17.5,-32),0,twoVelConstraint, new ProfileAccelConstraint(-80,10)) // prev 18
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
                .splineToConstantHeading(new Vector2d(40,12),-Math.PI/3,v, new ProfileAccelConstraint(-30,80))
                .splineToConstantHeading(new Vector2d(42,12),0,baseVelConstraint,new ProfileAccelConstraint(-70,50)) // y 10 was too much to he right
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