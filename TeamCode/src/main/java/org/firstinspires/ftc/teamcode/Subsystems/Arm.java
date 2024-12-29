package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private Servo servoAL;
    private Servo servoAR;
    private double mid = 0.92;
    private double fish = 0.89;
    private double wall = 0.645;
    private double init = 0.85;
    private double hb = 0.58    ; // hb = high bucket
    private double upAbit = 0.45;
    private double sub = 0.7;
    private double pos1 = 1;
    private double spec = 0.815;
    private double autoStart = 0.37;

    public Arm(HardwareMap hardwareMap)
    {
        servoAL = hardwareMap.get(Servo.class, "servoAL");
        servoAR = hardwareMap.get(Servo.class, "servoAR");
    }


    public void setArmPosMID(){servoAL.setPosition(mid); servoAR.setPosition(mid);}
    public void setArmPosWall(){servoAL.setPosition(wall); servoAR.setPosition(wall);}
    public void setArmPosInit(){servoAL.setPosition(init); servoAR.setPosition(init);}
    public void setArmPosHB(){servoAL.setPosition(hb); servoAR.setPosition(hb);}
    public void setArmPosUPaBIT(){servoAL.setPosition(upAbit); servoAR.setPosition(upAbit);}
    public void setArmPosSUB(){servoAL.setPosition(sub); servoAR.setPosition(sub);}
    public void setArmPosSpec(){servoAL.setPosition(spec); servoAR.setPosition(spec);}
    public void setArmPosFish(){servoAL.setPosition(fish); servoAR.setPosition(fish);}
    public void setArmPosSAuto(){servoAL.setPosition(autoStart); servoAR.setPosition(autoStart);}
    public double getArmPos(){
        return servoAL.getPosition();
    }











}
