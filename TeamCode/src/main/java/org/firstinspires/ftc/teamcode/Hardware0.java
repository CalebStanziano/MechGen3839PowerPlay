package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware0 {
    public DcMotor rf;
    public DcMotor lf;
    public DcMotor rb;
    public DcMotor lb;

    public Servo servo1;

    private static Hardware0 myInstance = null;
    public static Hardware0 getInstance() {
        if (myInstance == null) {
            myInstance = new Hardware0();
        }
            return myInstance;
    }

    public void init (HardwareMap hwMap){
        // Right Front Motor
        try {
            rf = hwMap.get(DcMotor.class, "rf");
            rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf.setPower(0);
        }
        catch (Exception p_exception) {
            lf = null;
        }
        // Left Front Motor
        try {
            lf = hwMap.get(DcMotor.class, "lf");
            lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lf.setPower(0);
        }
        catch (Exception p_exception) {
            lf = null;
        }
        try {
            servo1 = hwMap.get(Servo.class, "servo1");
        }
        catch (Exception p_exception) {
            servo1 = null;
        }
    }
}



}
