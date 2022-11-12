package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "Encoder Test")
public class TestEncoders extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        int position  = 0;
        robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        boolean aPressed = false;
        boolean yPressed = false;
        //robot.lm.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while(opModeIsActive()){

            //y to increase
            if(gamepad1.y){
                position += 1;
                robot.lm.setTargetPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
                robot.cc.setPosition(.1);
                robot.lm.setPower(.04);
            }
            if(gamepad1.a){
                position -= 1;
                robot.lm.setTargetPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
                robot.lm.setPower(-.04);
            }

           /* if(gamepad1.y && !yPressed){
                yPressed = true;
                position += 10;
                robot.lm.setTargetPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
            } else {
                yPressed = false;
            }
            //a to decrease
            if (gamepad1.a && !aPressed) {
                aPressed = true;
                position -= 10;
                robot.lm.setTargetPosition(position);
                telemetry.addData("Position", position);
                telemetry.update();
            } else {
                aPressed = false;
            }

            */
        }
    }
}
