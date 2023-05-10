package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Mini Bot TeleOp")
public class TeleOpMini extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double forward;
            double turning;
            double strafing =0;


            forward = gamepad1.left_stick_y;
            turning = -gamepad1.right_stick_x;

/*
            if (forward != 0){
                robot.setPower(forward, forward);
            } else {
                robot.setPower(0,0);
            }
            if(turning != 0){
                robot.setPower(-turning, turning);
            } else {
                robot.setPower(0,0);
            }

 */
            double max = Math.max(Math.abs(forward - strafing - turning), Math.max(Math.abs(forward + strafing - turning),
                    Math.max(Math.abs(forward + strafing + turning), Math.abs(forward - strafing + turning))));
            if (max < robot.maxSpeed) {
                robot.setPower(forward + strafing - turning, forward - strafing + turning);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower((forward + strafing - turning) * scaleFactor, (forward - strafing + turning) * scaleFactor);
            }

            //Slow Drive
            if (gamepad1.dpad_up) {
                forward = -0.25;
            }
            if (gamepad1.dpad_down) {
                forward = 0.25;
            }


            //Precision turning
            if (gamepad1.right_bumper) {
                robot.setPower(0.45, -0.45);
            }
            if (gamepad1.left_bumper) {
                robot.setPower(-0.45, 0.45);
            }

            if(gamepad1.a){
                robot.sm.setPower(1);
            } else if(gamepad1.y){
                robot.sm.setPower(-1);
            } else {
                robot.sm.setPower(0);
            }


        }
    }
}
