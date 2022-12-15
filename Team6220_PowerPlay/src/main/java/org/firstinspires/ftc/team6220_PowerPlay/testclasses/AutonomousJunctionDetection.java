package org.firstinspires.ftc.team6220_PowerPlay.testclasses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.team6220_PowerPlay.BaseAutonomous;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team6220_PowerPlay.Constants;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "JunctionDetectionTest", group = "Autonomous")
public class AutonomousJunctionDetection extends BaseAutonomous {

    OpenCvCamera webcam;
    ColorDetectPipeline pipeline = new JunctionDetectPipeline();

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "GrabberCamera"), cameraMonitorViewId);

        webcam.setPipeline(pipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(800, 600, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        waitForStart();

        servoGrabber.setPosition(Constants.GRABBER_CLOSE_POSITION);
        sleep(1000);

        while (opModeIsActive()) {
            servoGrabber.setPosition(Constants.GRABBER_CLOSE_POSITION);
            driveSlides(Constants.SLIDE_HIGH);

            if (pipeline.rectDetected) {
                driveWithIMU(pipeline.detectedRect.x / 800.0, pipeline.detectedRect.y / 600.0, 0);
            }

            telemetry.addData("x", pipeline.detectedRect.x);
            telemetry.addData("y", pipeline.detectedRect.y);
            telemetry.addData("🏃‍💨", pipeline.isRunning);
            telemetry.addData("counter", pipeline.counter);
            telemetry.update();
        }
    }
}
