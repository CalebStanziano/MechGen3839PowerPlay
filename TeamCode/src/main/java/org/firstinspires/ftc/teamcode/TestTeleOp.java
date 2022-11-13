package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Test")

public class TestTeleOp extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    @Override
    public void runOpMode() throws InterruptedException{
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //wait for driver to press start
        waitForStart();

        while(opModeIsActive()){
            double forward;
            double strafing;
            double turning;

            forward = -gamepad1.left_stick_y;
            turning = -gamepad1.right_stick_x;
            strafing = -gamepad1.left_stick_x;

            //slow drive
            if(gamepad1.dpad_up){
                forward = -0.25;

            }
            if(gamepad1.dpad_down){
                forward = 0.25;

            }
            //strafing
            double max = Math.max(Math.abs(forward - turning - strafing), Math.max(Math.abs(forward + strafing - turning),
                Math.max(Math.abs(forward + strafing + turning), Math.abs(forward - strafing + turning))));
            if(max < robot.maxSpeed){
                robot.setPower(forward - strafing - turning, forward + strafing - turning, forward + strafing + turning,
                        forward - strafing + turning);

            } else {
                double scaleFactor = max/robot.maxSpeed;
                robot.setPower(forward - strafing - turning * scaleFactor, forward + strafing - turning * scaleFactor,
                        forward + strafing + turning * scaleFactor, forward - strafing + turning * scaleFactor);
            }


            //slow turning
            if(gamepad1.left_bumper){
                turning = -0.25;
                //robot.setPower(-0.45, -0.45, 0.45, 0.45);

            }
            if(gamepad1.right_bumper){
                turning = 0.25;
                //robot.setPower(0.45, 0.45, -0.45, -0.45);


            }

            //cc Servo
            if(gamepad2.x) {
                robot.cc.setPosition(0);
            }
            if(!gamepad2.x) {
                robot.cc.setPosition(0.3);
            }
        }
    }

}
