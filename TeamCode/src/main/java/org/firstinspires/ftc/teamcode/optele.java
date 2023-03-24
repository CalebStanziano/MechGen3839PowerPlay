package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "tele")
public class optele extends LinearOpMode {
    HardwareNull robot = HardwareNull.getInstance();
     public void runOpMode() throws InterruptedException {
         robot.init(hardwareMap);
         telemetry.addData("Status", "Good");
         telemetry.update();

         //Bianca was here ;)

         if (robot.rb != null) {
             robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         }
         if (robot.lb != null) {
             robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         }

         waitForStart();

         while (opModeIsActive()) {
             double forward;
             double turning;

             forward = gamepad1.left_stick_y;
             turning = -gamepad1.right_stick_x;

             double max = Math.max(Math.abs(forward - turning), Math.max(Math.abs(forward- turning),
                     Math.max(Math.abs(forward+ turning), Math.abs(forward+ turning))));
             if(max <  robot.maxSpeed){
                 robot.setPower(forward- turning, -(forward- turning));
             } else {
                 double scaleFactor = max / robot.maxSpeed;
                 robot.setPower((forward- turning) * scaleFactor, -(forward- turning) * scaleFactor);
             }

             //Precision turning
             if(gamepad1.right_bumper){
                 robot.setPower(-0.45, -0.45);
             }
             if(gamepad1.left_bumper){
                 robot.setPower(0.45, 0.45);
             }
         }
    }
}
