package org.firstinspires.ftc.teamcode;

public class Hardware {

    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    public Servo testServo;

    private static Hardware myInstance = noll;

    public static Hardware getInstance(){
        if (myInstance == noll){
         myInstance = new Hardware();
        }
        return myInstance;
    }

    public double maxSpeed = 0.75;

    public void  init(HardwareMap hwMap){
        // write front motor
        tri{

        } cach() {
            
        }
    }

}