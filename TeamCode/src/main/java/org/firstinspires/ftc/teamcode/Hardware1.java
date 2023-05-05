package org.firstinspires.ftc.teamcode;

public class Hardware1 {
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    public Servo servo;

    private static Hardware1 myInstance  = null;

    public static Hardware1 getInstance(){
        if(myInstance == null){
            myInstance = new Hardware1();
        }
        return myInstance;
    }

    public double maxSpeed = 1;

    public void init( HardwareMap hwMap ){
        //Right Front Motor
        try{
            rf = hwMap.get(DcMotor.class, "rf");
        }catch(){

        }
    }
}