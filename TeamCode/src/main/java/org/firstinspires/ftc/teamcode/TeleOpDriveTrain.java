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
        boolean dPadPressed = false;

        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.lm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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

            //Cone Claw
            if(gamepad2.a){
                robot.cc.setPosition(0.8);
                robot.cc2.setPosition(0.5);
                telemetry.addData("Position", "open");
                telemetry.update();
            }
            if(gamepad2.y){
                robot.cc.setPosition(0.3);
                robot.cc2.setPosition(0.9);
                telemetry.addData("Position", "closed");
                telemetry.update();
            }

            //Lift Motor Positions
            //0 = ground junction, 1 = low junction, 2 = med junction, 3 = high junction

            //pick-up
            if(gamepad2.dpad_down) {
                dPadPressed = true;
                while(robot.lm.getCurrentPosition() != 0) {
                    robot.lm.setTargetPosition(0);
                    robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lm.setPower(1);
                }
                dPadPressed = false;

            }
            //92.0754717
            //low junction
            if(gamepad2.dpad_left){
                dPadPressed = true;
                while(robot.lm.getCurrentPosition() != 2090) {
                    robot.lm.setTargetPosition(2090);
                    robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lm.setPower(1);
                }
                dPadPressed = false;

            }
            //mid. junction
            if(gamepad2.dpad_up){
                dPadPressed = true;
                while(robot.lm.getCurrentPosition() != 3282) {
                    robot.lm.setTargetPosition(3282);
                    robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lm.setPower(1);
                }
                dPadPressed = false;
            }
            //high junction
            if(gamepad2.dpad_right){
                dPadPressed = true;
                while(robot.lm.getCurrentPosition() != 4248) {
                    robot.lm.setTargetPosition(4248);
                    robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.lm.setPower(1);
                }
                dPadPressed = false;
            }
            //ground junction.
            if(gamepad2.b){
                robot.lm.setTargetPosition(322);
                robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lm.setPower(1);
            }


            //reset encoders
            if(gamepad2.left_bumper && gamepad2.right_bumper){
                encoderReset();
                //telemetry.addData("Encoders", "reset");
                //telemetry.update();

            }

            // Manuel Controls
            if(gamepad2.left_stick_y > 0){
                robot.lm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.lm.setPower(-1);
            }
            else if(gamepad2.left_stick_y < 0) {
                robot.lm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.lm.setPower(1);
                if(robot.lm.getCurrentPosition() == 0){
                    robot.lm.setPower(0);
                }
            } else{
                robot.lm.setPower(0);
                robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
    }
    public void encoderReset(){
        int currentPos = robot.lm.getCurrentPosition();
        telemetry.addData("position", currentPos);
        telemetry.update();
        robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //robot.lm.setTargetPosition(0);
        //robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //robot.lm.setPower(1);
    }
}