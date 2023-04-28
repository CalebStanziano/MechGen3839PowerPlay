package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Hardware {
    public DcMotor rb;
    public DcMotor lb;


    private static Hardware myInstance = null;
    public static Hardware getInstance(){
        if(myInstance == null){
            myInstance = new Hardware();


        }
    return myInstance;
    }
    public double maxSpeed = 0.75;

    public void init(HardwareMap hwMap){

    //Right Back Motor
        try {
            rb = hwMap.get(DcMotor.class, "rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);
        } catch(Exception p_exception){
            rb = null;
        }
    //Left Back Motor
        try {
            lb = hwMap.get(DcMotor.class, "lb");
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);
        } catch(Exception p_exception){
            rb = null;
        }
    }
    public void setPower(double br,double bl){
        if (rb != null) {
            rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        }
        if (lb != null) {
            lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
        }

    }
}
