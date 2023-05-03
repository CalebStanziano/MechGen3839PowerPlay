package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Mini Bot TeleOp")
public class TeleOpMini extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double forward;
            double turning;


            forward = gamepad1.left_stick_y;
            turning = -gamepad1.right_stick_x;

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
                robot.sm.setPower(0.5);
            }
            if(gamepad1.y){
                robot.sm.setPower(-0.5);
            }


        }
    }
}
