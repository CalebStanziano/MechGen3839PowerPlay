package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    //moter varible
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    public Servo testServo;
    private static Hardware myInstants = null;

    public static Hardware getInstance(){
        if(myInstants == null){ myInstants = new Hardware();
        } return myInstants;
    }
    public double maxSpeed = 0.75;

    public void init(HardwareMap hwMap){
        //right front moter
        try{

        } catch(){

    }
}
