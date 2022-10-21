package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.hardware.bosch.BNO055IMU;

/**
 * 192.168.43.1:8080/dash
 * must be connected to robot wifi
 */

public class Hardware {

    public DcMotor rf;
    public DcMotor lf;
    public DcMotor rb;
    public DcMotor lb;

    //Lift Motor
    public DcMotor lm;

    //Lift Motor
    public DcMotor liftMotor;

    //Cone Claw servo
    public Servo cc;


    public BNO055IMU gyro;


    private static Hardware myInstance = null;

    public static Hardware getInstance() {
        if (myInstance == null) {
            myInstance = new Hardware();


        }
        return myInstance;
    }

    public double maxSpeed = 0.75;

    public void init(HardwareMap hwMap) {

        //Right Front Motor
        try {
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        } catch (Exception p_exception) {
            rf = null;
        }
        //Left Front
        try {
            lf = hwMap.get(DcMotor.class, "lf");
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setDirection(DcMotorSimple.Direction.REVERSE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);
        } catch (Exception p_exception) {
            lf = null;
        }
        //Right Back
        try {
            rb = hwMap.get(DcMotor.class, "rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);
        } catch (Exception p_exception) {
            rb = null;
        }
        //Left Back
        try {
            lb = hwMap.get(DcMotor.class, "lb");
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setDirection(DcMotorSimple.Direction.REVERSE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);
        } catch (Exception p_exception) {
            lb = null;
        }
        //Turret Motor
        try {
            lm = hwMap.get(DcMotor.class, "tm");
            lm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lm.setPower(0);
        } catch (Exception p_exception) {
            lm = null;
        }
        //Gyro
        try {
            gyro = hwMap.get(BNO055IMU.class, "gyro");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.loggingEnabled = true;
            parameters.loggingTag = "gyro";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            gyro.initialize(parameters);
        } catch (Exception p_exception) {
            gyro = null;
        }
        //Cone Claw Servo
        try {
            cc = hwMap.get(Servo.class, "cc");
        } catch( Exception p_exception){
            cc = null;
        }
    }

    //Set Power
    public void setPower(double fr, double br, double fl, double bl) {
        if (rf != null) {
            rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));
        }
        if (lf != null) {
            lf.setPower(Range.clip(fl, -maxSpeed, maxSpeed));
        }
        if (rb != null) {
            rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        }
        if (lb != null) {
            lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
        }
    }
    //public void spinTurret (double x, double y){
        /*if(x == 1){
            lm.setTargetPosition(1);
        }
        if(x==2){

        }
    }
    */



}