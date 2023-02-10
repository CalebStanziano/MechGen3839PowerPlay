package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware1 {
    public DcMotor rf;
    public DcMotor lf;
    public Servo test
            private static Hardware1 myInstance =null;
    public static Hardware1 getInstance(){
        if(myInstance ==null){
            myInstance =new Hardware1 ();
        }return myInstance;
    }
    public void init (HardwareMap hwMap){
        //right front motomoto
        try{
            rf =hwMap.get(DcMotor.class,"rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        } catch(Exception p_exception){
            rf =null
        }
        // left front Motor
        try{
            lf =hwMap.get(DcMotor.class,"lf");
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);
        } catch(Exception p_exception){
            lf =null
        }
        // test servo
        try{
            test=hwMap.get(Servo.class,"test");

        }
        catch(Exception p_exception){
            test =null;
        }
    }
    //mecanum drivetrain

}
