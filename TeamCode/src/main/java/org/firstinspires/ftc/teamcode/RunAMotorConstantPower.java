package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
@Disabled
public class RunAMotorConstantPower extends LinearOpMode{

    // Declare Motor
    private DcMotor myMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized"); // Standard Telemetry
        telemetry.update();

        // Hardware map: Let's the robot know the name of the motor so that
        // We can reference it later in the config file
        myMotor = hardwareMap.get(DcMotor.class, "myMotor");

        // Set the direction of the motor to spin FORWARD
        myMotor.setDirection(DcMotor.Direction.FORWARD);
        waitForStart();

        // runs until the end of the match (until the driver presses STOP
        while(opModeIsActive()){
            // If the a-button is pressed, set the motor to a certain power
            if(gamepad1.a == true){
                myMotor.setPower(0.5);
            }
            else{
                //Otherwise switch it off
                myMotor.setPower(0.0);
            }
            // Telemetry so that we can see the value of the motor
            telemetry.addData("Button A", gamepad1.a);
            telemetry.update();
        }
    }
}
