package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HardwareNull {
    //motors
    public DcMotor sweep;
    public DcMotor lift;
    public DcMotor rb;
    public DcMotor lb;

    public Servo servo1;

    private static HardwareNull myInstance = null;

    public static HardwareNull getInstance() {
        if (myInstance == null) {
            myInstance = new HardwareNull();
        }
        return myInstance;
    }

    public double maxSpeed = 0.75;

    public void init(HardwareMap hwMap) {

        // Right Front Motor
        try {
            sweep = hwMap.get(DcMotor.class, "rf");
            sweep.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            sweep.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sweep.setPower(0);
        } catch (Exception p_exception) {
            sweep = null;
        }

        // Left Front Motor
        try {
            lift = hwMap.get(DcMotor.class, "lf");
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lift.setPower(0);
        } catch (Exception p_exception) {
            lift = null;
        }

        // Right Back Motor
        try {
            rb = hwMap.get(DcMotor.class, "rb");
            rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb.setPower(0);
        } catch (Exception p_exception) {
            rb = null;
        }

        // Left Back Motor
        try {
            lb = hwMap.get(DcMotor.class, "lb");
            lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb.setPower(0);
        } catch (Exception p_exception) {
            lb = null;
        }

        // Servo 1
        try {
            servo1 = hwMap.get(Servo.class, "servo1");
        } catch (Exception p_exception) {
            servo1 = null;
        }
    }
    public void setPower (double br, double bl) {
        if (rb != null) {
            rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        }

        if (lb != null) {
            lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
        }
    }
}