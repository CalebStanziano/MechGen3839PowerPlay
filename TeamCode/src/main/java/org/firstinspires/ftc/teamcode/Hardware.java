package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


public class Hardware {
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    //cone claw servo
    public Servo cc;

    private static Hardware myInstance = null;
    public static Hardware getInstance(){
        if(myInstance == null){
            myInstance = new Hardware();
        }
        return myInstance;
    }
    public double maxSpeed = 0.5;

    public void init(HardwareMap hwMap){
        //right front motor
        try{
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        } catch(Exception p_exception){
            rf = null;
        }

        //right back motor
        try{
            rb = hwMap.get(DcMotor.class, "rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);
        } catch(Exception p_exception){
            rb = null;
        }

        //left front motor
        try{
            lf = hwMap.get(DcMotor.class, "lf");
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);
        } catch(Exception p_exception){
            lf = null;
        }

        //left back motor
        try{
            lb = hwMap.get(DcMotor.class, "lb");
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);
        } catch(Exception p_exception){
            lb = null;
        }

        //Servo cc
        try{
            cc = hwMap.get(Servo.class, "cc");
        } catch(Exception p_exception){
            cc = null;
        }


    }

    public void setPower(double fr, double br, double fl, double bl){
        if(rf != null){
            rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));
        }
        if(rb != null){
            rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        }
        if(lf != null){
            lf.setPower(Range.clip(fl, -maxSpeed, maxSpeed));
        }
        if(lb != null){
            lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
        }
    }
}



/**package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * If you see this next meeting tell me:"I'm ready for OpenCv."

public class Hardware {

    public DcMotor rf;
    public DcMotor lf;
    public DcMotor rb;
    public DcMotor lb;

    public DcMotor liftMotor;
    private static Hardware myInstance = null;
    public static Hardware getInstance(){
        if(myInstance == null){
            myInstance = new Hardware();


        }
    return myInstance;
    }
    public double maxSpeed = 0.75;

    public void init(HardwareMap hwMap){

    //Right Motor
        try {
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        } catch(Exception p_exception){
            rf = null;
        }
    }
}
 */
