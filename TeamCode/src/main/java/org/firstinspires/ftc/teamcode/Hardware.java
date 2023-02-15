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
    }
<<<<<<< Updated upstream
}
=======

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
>>>>>>> Stashed changes
