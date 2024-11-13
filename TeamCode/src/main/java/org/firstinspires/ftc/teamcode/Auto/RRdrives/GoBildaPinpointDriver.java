package org.firstinspires.ftc.teamcode.Auto.RRdrives;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriver;
import com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriverRR;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class PinpointDrive extends MecanumDrive {
    public static class Params {
        public double xOffset = -7.58;
        public double yOffset = -.012;
        public double encoderResolution = GoBildaPinpointDriverRR.goBILDA_4_BAR_POD;
        public GoBildaPinpointDriver.EncoderDirection xDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
        public GoBildaPinpointDriver.EncoderDirection yDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    }

    public static Params PARAMS = new Params();
    public GoBildaPinpointDriverRR pinpoint;
    private Pose2d lastPinpointPose = pose;
    public Limelight limelight;
    Telemetry telemetry;
    ElapsedTime LLTimer = new ElapsedTime();
    int numUpdates = 0;
    public boolean useLL = false;

    public PinpointDrive(HardwareMap hardwareMap, Pose2d pose, Limelight.corners corner, Telemetry tel) {
        super(hardwareMap, pose);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        pinpoint = hardwareMap.get(GoBildaPinpointDriverRR.class,"pinpoint");
        pinpoint.setOffsets(DistanceUnit.MM.fromInches(PARAMS.xOffset), DistanceUnit.MM.fromInches(PARAMS.yOffset));
        pinpoint.setEncoderResolution(PARAMS.encoderResolution);
        pinpoint.setEncoderDirections(PARAMS.xDirection, PARAMS.yDirection);
        pinpoint.resetPosAndIMU();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pinpoint.setPosition(pose);
        limelight = new Limelight(hardwareMap, corner, tel);
        telemetry = tel;
        LLTimer.reset();
    }

    @Override
    public PoseVelocity2d updatePoseEstimate() {
        if (lastPinpointPose != pose) {
            pinpoint.setPosition(pose);
        }
        if (LLTimer.seconds() > 2 && useLL) {
            double heading = pose.heading.toDouble();
            Vector2d aprilTagPose = limelight.getLatestPosition(Math.toDegrees(heading), pose);
            if (aprilTagPose != null) {
                pose = new Pose2d(aprilTagPose, heading);
                pinpoint.setPosition(pose);
                numUpdates += 1;
                LLTimer.reset();
            }
            telemetry.addLine("Searching for ATAG");
        }

        pinpoint.update();
        pose = pinpoint.getPositionRR();

        lastPinpointPose = pose;

        poseHistory.add(pose);
        while (poseHistory.size() > 100) {
            poseHistory.removeFirst();
        }

        telemetry.addData("Using LL", useLL);
        telemetry.addData("Number of updates", numUpdates);
        telemetry.addData("RR x", pose.position.x);
        telemetry.addData("RR y", pose.position.y);
        telemetry.addData("RR H", Math.toDegrees(pose.heading.toDouble()));

        return pinpoint.getVelocityRR();
    }

    public void initAuto(){
        limelight.getLatestPosition(Math.toDegrees(0), pose);
    }
}