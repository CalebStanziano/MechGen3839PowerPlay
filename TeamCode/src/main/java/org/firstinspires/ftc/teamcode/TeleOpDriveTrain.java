package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp (name = "Main TeleOp")
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

        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()){
            double forward;
            double strafing;
            double turning;
            double spinTurretX;
            double spinTurretY;
            int position = 0;


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
                robot.setPower((forward - strafing - turning) * scaleFactor, (forward + strafing - turning) * scaleFactor,
                        (forward + strafing + turning) * scaleFactor, (forward - strafing + turning) * scaleFactor);
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
            //pick-up
            if(gamepad2.dpad_down) {
                robot.lm.setTargetPosition(0);
                robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lm.setPower(1);
            }
            //low junction
            if(gamepad2.dpad_left){
                robot.lm.setTargetPosition(2120);
                robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lm.setPower(1);
            }
            //mid. junction
            if(gamepad2.dpad_up){
                robot.lm.setTargetPosition(3180);
                robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lm.setPower(1);
            }

            //high junction
            if(gamepad2.dpad_right){
                robot.lm.setTargetPosition(4390);
                robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lm.setPower(1);
            }

            /*if(gamepad2.left_bumper){
                robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            */

            //ground junction.
            if(gamepad2.b){
                robot.lm.setTargetPosition(60);
                robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lm.setPower(1);
            }



        }

        /**Turret Motor Spin
        public void spinTurret() {
            if (gamepad2.right_stick_x = 1) {
            robot.tm.setPower();
            }
        } **/
    }
}