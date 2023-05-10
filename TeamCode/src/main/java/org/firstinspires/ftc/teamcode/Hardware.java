package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Hardware {
    public DcMotor m0;
    public DcMotor m1;
    public DcMotor sm;


    private static Hardware myInstance = null;
    public static Hardware getInstance(){
        if(myInstance == null){
            myInstance = new Hardware();


        }
    return myInstance;
    }
    public double maxSpeed = 1;

    public void init(HardwareMap hwMap){

    //Right Back Motor
        try {
            m0 = hwMap.get(DcMotor.class, "m0");
            m0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m0.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            m0.setPower(0);
        } catch(Exception p_exception){
            m0 = null;
        }
    //Left Back Motor
        try {
            m1 = hwMap.get(DcMotor.class, "m1");
            m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            m1.setDirection(DcMotorSimple.Direction.REVERSE);
            m1.setPower(0);
        } catch(Exception p_exception){
            m1 = null;
        }
        //Spin Motor
        try {
            sm = hwMap.get(DcMotor.class, "m2");
            sm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            sm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sm.setPower(0);
        } catch(Exception p_exception){
            sm = null;
        }
    }
    public void setPower(double br,double bl){
        if (m0 != null) {
            m0.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        }
        if ( m1 != null) {
            m1.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
        }

    }
}
