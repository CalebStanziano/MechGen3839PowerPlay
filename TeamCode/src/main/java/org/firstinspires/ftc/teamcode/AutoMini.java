package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "mini Game Auto")
public class AutoMini extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
        runtime.startTime();
        //Add Code Here:

    }
    //Methods here:
    public void forward(double distanceMoving, double speedMoving){
        //in inches
        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving *(wheelMotor / wheelCircumference));
        robot.setPower(0,0,0,0);
        double startPosRf = robot.rf.getCurrentPosition();
        double startPosRb = robot.rb.getCurrentPosition();
        double startPosLf = robot.lf.getCurrentPosition();
        double startPosLb = robot.rf.getCurrentPosition();

        double PosRf = Math.abs(startPosRf - Math.round(startPosRf));
        double PosRb = Math.abs(startPosRb - Math.round(startPosRb));
        double PosLf = Math.abs(startPosLf - Math.round(startPosLf));
        double PosLb = Math.abs(startPosLb - Math.round(startPosLb));
 //left off here Patty
        robot.rf.setTargetPosition((int) Math.round(ticks));
        robot.lf.setTargetPosition((int) Math.round(ticks));
        robot.rb.setTargetPosition((int) Math.round(ticks));
        robot.lb.setTargetPosition((int) Math.round(ticks));

        robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
