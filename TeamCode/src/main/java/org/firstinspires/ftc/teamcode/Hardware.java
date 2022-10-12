package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
        try{
            rb = hwMap.get(DcMotor.class, "rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);
        } catch (Exception p_exception) {
            rb = null;
        }
        try{
            lf = hwMap.get(DcMotor.class, "rb");
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);
        } catch (Exception p_exception) {
            lf = null;
        }
        try{
            lb = hwMap.get(DcMotor.class, "rb");
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);
        } catch (Exception p_exception) {
            lb = null;
        }
    }
}
