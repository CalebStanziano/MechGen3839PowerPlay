package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Servo Test")
public class TestServo extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        int position  = 0;
        boolean aPressed = false;
        boolean yPressed = false;

        robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER)

        while(opModeIsActive()){
            //y to increase
            if(gamepad1.y ){
                //yPressed = true;
                position += 1;
                robot.cc2.setPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
            } //else {
                //yPressed = false;
            //}
            //a to decrease
            if (gamepad1.a) {
                //aPressed = true;
                position -= 1;
                robot.cc2.setPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
            } //else {
               // aPressed = false;
            //}
        }
    }
}
