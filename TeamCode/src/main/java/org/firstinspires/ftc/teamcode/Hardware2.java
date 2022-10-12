package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Hardware2 {

    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    //Cone Claw Servo
    public Servo cc;

    private static Hardware2 myInstance = null;

    private static Hardware2 getInstance(){
        if(myInstance == null){
            myInstance = new Hardware2();
        }
        return myInstance;
    }
    public double maxSpeed =0.5;
    public void init(HardwareMap hwMap){
        //Right Front Motor
        try{
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        } catch (Exception p_exception){
            rf = null;
        }

        //Servo cc
        try{
            cc = hwMap.get(Servo.class, "cc");
        } catch (Exception p_exception){
            cc = null;
        }
    }

    public void setPower1(double fr, double br, double fl, double bl){
        if(rf != null){
            rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));
        }
    }

}
