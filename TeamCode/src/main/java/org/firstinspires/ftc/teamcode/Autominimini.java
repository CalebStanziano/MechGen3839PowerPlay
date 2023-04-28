package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class Autominimini extends LinearOpMode {
    Hardware1 robot = Hardware1.getInstance();
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialize");
        telemetry.update();

        waitForStart();
        runtime.reset();
        runtime.startTime();
    //add code here
    }
    //methods here
    public void forward(double DistanceoMoving, double speedMoving) {
        double wheelcircumference = 4 * Math.PI;
        //measure diameter (4)
        double wheelMotor = 560;
        double ticks = (DistanceoMoving * (wheelMotor / wheelcircumference));
        robot.setPower(0, 0, 0, 0);
        double startPosRf = robot.rf.getCurrentPosition();
        double startPosRb = robot.rb.getCurrentPosition();
        double startPosLf = robot.lf.getCurrentPosition();
        double startPosLb = robot.lb.getCurrentPosition();

        double PosRf = Math.abs(startPosRf - Math.round(startPosRf));
        double PosRf = Math.abs(startPosRb - Math.round(startPosRb));
        double PosRf = Math.abs(startPosLf - Math.round(startPosLf));
        double PosRf = Math.abs(startPosLb - Math.round(startPosLb));

        

    }
}

