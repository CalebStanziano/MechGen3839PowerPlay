package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//public can be run in other files
//defines public classes
public class Hardware {

    //Defined Motors
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    //Defined Servos
    public Servo claw;

    //Static: run once
    public static Hardware myInstance = null;

    /*
    Double: real number
    1 = 100% 0.5 = 50%
    */
    public double maxSpeed = 1;

    /*
    private runs only in file
    constructor initializes variables
    makes robot an object
    refers to itself
    defines private classes
    */
    private Hardware() {

    }

    public static Hardware getInstance() {

        //Compares myInstance to null
        if (myInstance == null) {

            //Sets myInstance = to Hardware
            myInstance = new Hardware();
        }
            //Output of myInstance
            return myInstance;
    }

    /*
    Void: no return output
    Hardware maps motors onto robot
    Init: Initialize
    */
    public void init(HardwareMap hwMap) {

        //attempts to preform an action
        try{

            /*
            argument is inside ()
            .get checks if argument is true
            .class defines as class
             adds rf to hwMap
             */
            rf = hwMap.get(DcMotor.class,"rf");

            /*
            .setPower(Double) sets speed
            .setZeroPowerBehavior how motor works when .setPower(0)
            .BRAKE holds position ie. Parks the robot
             */
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            /*
            .setMode sets the encoder mode
            Encoder detects rpm and wheel position
             */
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            rf.setPower(0);

            /*
            catch() detects errors
             */
        } catch(Exception p_exception) {

            rf = null;

        }

        try{

            rb = hwMap.get(DcMotor.class,"rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);

        } catch(Exception p_exception) {

            rb = null;

        }

        try{

            lf = hwMap.get(DcMotor.class,"lf");
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);

        } catch(Exception p_exception) {

            lf = null;

        }

        try{

            lb = hwMap.get(DcMotor.class,"lb");
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);

        } catch(Exception p_exception) {

            lb = null;

        }

        try{

            //adds servos to hwMap
            claw = hwMap.get(Servo.class,"claw");

        } catch(Exception p_exception) {

            claw = null;

        }

    }

    /*
    for macanum drive
    order you can remember
    corresponds to motor vars
    setPower changes the power for all motors
     */
    public void setPower(double fr, double br, double fl, double bl){

        //! means not equal
        if(rf != null) {

            //Range.clip sets a range between -maxSpeed and maxSpeed
            rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));

        }

        if(rb != null) {

            rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));

        }

        if(lf != null) {

            lf.setPower(Range.clip(fl, -maxSpeed, maxSpeed));

        }

        if(lb != null) {

            lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));

        }

    }

}
