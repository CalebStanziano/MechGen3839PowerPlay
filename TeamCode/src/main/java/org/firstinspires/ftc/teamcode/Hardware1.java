package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware1 {



public DcMotor rf;
public DcMotor rb;
public DcMotor lf;
public DcMotor lb;

//Cone claw Servo
    public Servo cc;

    private static Hardware1 myInstance = null;

    private static Hardware1 getInstance() {
        if(myInstance == null) {
            myInstance = new Hardware1();
        }
        return myInstance;
    }
    public void init(HardwareMap hwMap){

        //Right Front Motor
        try{
            rf = hwMap.get(DcMotor.class, "rf");

        }
    }

}
