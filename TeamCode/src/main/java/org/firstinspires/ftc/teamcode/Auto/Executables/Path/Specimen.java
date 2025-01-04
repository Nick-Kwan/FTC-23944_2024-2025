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
            new TranslationalVelConstraint(60),
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
                .splineToConstantHeading(new Vector2d(37, 7.5), 0,baseVelConstraint) // x: 40 works
                .afterTime(0, new ParallelAction(
                        bot.armSpecAction()
                ))
                .waitSeconds(0.125)
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
                        bot.armMidAction()
                ))
                .splineToConstantHeading(new Vector2d(40,-28),Math.PI/2,baseVelConstraint) // was x : 43
                .splineToConstantHeading(new Vector2d(55,-28),0,baseVelConstraint)
                .splineToConstantHeading(new Vector2d(55,-42),0,baseVelConstraint)
                .afterTime(0,new ParallelAction(
                        bot.clawCloseAction()
                ))
                // grabs first sample
                .waitSeconds(0.2)
                .splineToConstantHeading(new Vector2d(26,-42),0,baseVelConstraint)
                .afterTime(0,new ParallelAction(
                        bot.clawOpenAction(),
                        bot.slideDownAction()
                ))
                // lets go of first sample
                .splineToConstantHeading(new Vector2d(54,-24),0,baseVelConstraint)
                .splineToConstantHeading(new Vector2d(53,-51),-Math.PI/2,baseVelConstraint)
                .afterTime(0,new ParallelAction(
                        bot.clawCloseAction()
                ))
                //grabs second sample
                .waitSeconds(0.4)
                .afterTime(0, new ParallelAction(
                        bot.slideSpec2Action()
                ))
                .splineToConstantHeading(new Vector2d(27,-50),0,baseVelConstraint) // was x : 24
                .afterTime(0,new ParallelAction(
                        bot.clawOpenAction()
                ))
                // lets go of second sample
                .waitSeconds(0.2)
                .afterTime(0,new ParallelAction(
                        bot.armWallAction()
                ))
                .splineToConstantHeading(new Vector2d(20,-38.5),Math.PI/2,baseVelConstraint)
                .afterTime(0, new ParallelAction(
                        bot.clawCloseAction()
                ))
                // grabs preloaded spec from wall
                .waitSeconds(0.5)
                .afterTime(0,new ParallelAction(
                        bot.armUPaBITAction()
                ))
                .waitSeconds(0.2)
                .afterTime(0,new ParallelAction(
                        bot.rSlideUpAction(),
                        bot.slideSpecAction()
                ))
                .splineToConstantHeading(new Vector2d(36,12),-Math.PI/2,baseVelConstraint)
                .afterTime(0, new ParallelAction(
                        bot.armSpecAction()
                ))
                .waitSeconds(0.125)
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
                        bot.armMidAction()
                ))
                .build();
    }
}