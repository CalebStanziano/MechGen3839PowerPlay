package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Servo Test")
public class TestServo extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        int position  = 0;
        boolean aPressed = false;
        boolean yPressed = false;

        while(opModeIsActive()){
            //y to increase
            if(gamepad1.y ){
                yPressed = true;
                position += 1;
                robot.cc.setPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
            } else {
                yPressed = false;
            }
            //a to decrease
            if (gamepad1.a) {
                aPressed = true;
                position -= 1;
                robot.cc.setPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
            } else {
                aPressed = false;
            }
        }
    }
}
