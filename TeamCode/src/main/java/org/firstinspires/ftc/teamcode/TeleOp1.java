package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Robot: Teleop juan", group="Robot")
public class TeleOp1 extends LinearOpMode {
Hardware1 robot = Hardware1.getInstance();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();
        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(opModeIsActive()) {
            double forward;
            double strafing;
            double turning;
            int ServoPos = 0;
            forward = gamepad1.left_stick_y;
            strafing = gamepad1.left_stick_x;
            turning = gamepad1.right_stick_x;
            //Strafing
            double max = Math.max(Math.abs(forward - strafing - turning), Math.max(Math.abs(forward + strafing - turning),
                    Math.max(Math.abs(forward + strafing + turning), Math.abs(forward - strafing + turning))));
            if (max < robot.MaxSpeed) {
                robot.setPower(forward - strafing - turning, forward + strafing - turning, forward + strafing + turning,
                        forward - strafing + turning);
            } else {
                double scaleFactor = max / robot.MaxSpeed;
                robot.setPower((forward - strafing - turning) * scaleFactor, (forward + strafing - turning) * scaleFactor
                        , (forward + strafing + turning) * scaleFactor, (forward - strafing + turning) * scaleFactor);
            }
            //up position
            if (gamepad2.y) {
                robot.arm.setPosition(0.655);


            }
            //down position
            if (gamepad2.b) {
                robot.arm.setPosition(0.13);

            }
            //bucket upright position
            if (gamepad2.a) {
                robot.arm.setPosition(0.5454);
            }

            //manual arm movement
            while (gamepad2.left_stick_y > 0) {
                    ServoPos += 0.01;
                    robot.arm.setPosition(ServoPos);
                }
            while (gamepad2.left_stick_y < 0) {
                    ServoPos -= 0.01;
                    robot.arm.setPosition(ServoPos);
                }
            }



    }
}
