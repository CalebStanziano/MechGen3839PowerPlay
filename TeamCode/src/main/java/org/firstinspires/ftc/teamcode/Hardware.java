package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    //define motor
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    public Servo testServo;

    private static Hardware myInstance = null;

    public static Hardware getInstance(){
        if(myInstance == null){
            myInstance = new Hardware();
        }
        return myInstance;
    }

    public double maxSpeed = 0.75;

    public void init(HardwareMap hwMap){
        //right front motor
        try{

        } catch() {
            
        }
    }

}
