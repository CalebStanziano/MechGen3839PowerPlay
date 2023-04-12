package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Hardware1 {

    public DcMotor rf;
    public DcMotor lf;
    public DcMotor lb;
    public DcMotor rb;
    //defined motors
    public Servo Claw;
    //defined servo
    public static Hardware1 myInstance=null;
    // idk
    public double MaxSpeed=1;
    //setting the maxspeed to 1-double is decimal nums
    private Hardware1(){
    }
    //refers to itself, makes the robot an object, defines private parts
    public static Hardware1 getInstance(){
        if(myInstance==null){
        myInstance= new Hardware1();
        }
        return myInstance;
    }
    public void init(HardwareMap hwMap) {
        //initialize hardware map, robot makes an idea of what its doing
        try {
            rf = hwMap.get(DcMotor.class, "rf");
            //checking if arguement is true, adding rfmotor into hwmap
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);

        } catch (Exception p_exception) {
            rf = null;
        }
        try {
            lf = hwMap.get(DcMotor.class, "lf");
            //checking if arguement is true, adding lfmotor into hwmap
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);

        } catch (Exception p_exception) {
            lf = null;
        }
        try {
            rb = hwMap.get(DcMotor.class, "rb");
            //checking if arguement is true, adding rbmotor into hwmap
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);

        } catch (Exception p_exception) {
            rb = null;
        }
        try {
            lb = hwMap.get(DcMotor.class, "lb");
            //checking if arguement is true, adding lbmotor into hwmap
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);

        } catch (Exception p_exception) {
            lb = null;
        }
        try {
            Claw = hwMap.get(Servo.class, "cc");
        } catch (Exception p_exception) {
            Claw = null;
        }


    }
    public void setPower(double fr, double br, double fl, double bl){
        if(rf!=null){
            rf.setPower(Range.clip(fr,-MaxSpeed,MaxSpeed));
        }
        if(lf!=null){
            lf.setPower(Range.clip(fl,-MaxSpeed,MaxSpeed));
        }
        if(rb!=null){
            rb.setPower(Range.clip(br,-MaxSpeed,MaxSpeed));
        }
        if(lb!=null){
            lb.setPower(Range.clip(bl,-MaxSpeed,MaxSpeed));
        }

    }

}
