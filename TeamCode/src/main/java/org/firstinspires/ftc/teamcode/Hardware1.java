package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware1 {

    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lb;
    public DcMotor lf;


    private static Hardware1 myInstance = null;

    public static Hardware1 getInstance() {
        if(myInstance == null){
            myInstance = new Hardware1();

        }
        return myInstance;
    }

    double maxSpeed = 0.75;

    public void init(HardwareMap hwMap) {

        try {
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        } catch (Exception p_exception) {
            rf = null;
    }

}
