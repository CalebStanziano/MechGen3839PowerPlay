package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp (name = "DriveTrainTeleOp")
public class TeleOpDriveTrain extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    @Override
    public void runOpMode() throws InterruptedException{
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //wait for driver to press start
        waitForStart();
        boolean xKey = true;
        boolean xPressed = false;

        while (opModeIsActive()){
            double forward;
            double strafing;
            double turning;
            double spinTurretX;
            double spinTurretY;


            forward = gamepad1.left_stick_y;
            turning  = -gamepad1.right_stick_x;
            strafing = -gamepad1.left_stick_x;
            spinTurretX = gamepad2.right_stick_x;
            spinTurretY = gamepad2.right_stick_y;

            //Slow Drive
            if(gamepad1.dpad_up){
                forward = -0.25;
            }
            if(gamepad1.dpad_down){
                forward = 0.25;
            }
            if(gamepad1.dpad_left){
                strafing = 0.45;
            }
            if(gamepad1.dpad_right){
                strafing = -0.45;
            }

            //Strafing
            double max = Math.max(Math.abs(forward - strafing - turning), Math.max(Math.abs(forward + strafing - turning),
                    Math.max(Math.abs(forward + strafing + turning), Math.abs(forward - strafing + turning))));
            if(max <  robot.maxSpeed){
                robot.setPower(forward - strafing - turning, forward + strafing - turning, forward + strafing + turning,
                        forward - strafing + turning);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower(forward - strafing - turning * scaleFactor, forward + strafing - turning * scaleFactor,
                        forward + strafing + turning * scaleFactor, forward - strafing + turning * scaleFactor);
            }

            //Precision turning
            if(gamepad1.right_bumper){
                robot.setPower(0.45, 0.45, -0.45, -0.45);
            }
            if(gamepad1.left_bumper){
                robot.setPower(-0.45, -0.45, 0.45, 0.45);
            }

            //Turret Motor

            //Cone Claw
            if(gamepad2.a){
                robot.cc.setPosition(0.3);
                telemetry.addData("Position", "open");
                telemetry.update();
            }
            if(gamepad2.y){
                robot.cc.setPosition(0);
                telemetry.addData("Position", "closed");
                telemetry.update();
            }

            //Lift Motor Positions
            //0 = ground junction, 1 = low junction, 2 = med junction, 3 = high junction
            robot.lm.setPower(gamepad2.left_stick_y);


        }

        /**Turret Motor Spin
        public void spinTurret() {
            if (gamepad2.right_stick_x = 1) {
            robot.tm.setPower();
            }
        } **/
    }
}