package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "One high Right")
public class OneHighAutoRight extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //int botPosition = 0;
        robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //stackPos depreciates as the cycle continues.
        //int stackPos = 610;
        //double angle = robot.gyro.getAngularOrientation().firstAngle;
        double autoSpeed = 0.5;

        waitForStart();
        //close Claw
        robot.cc.setPosition(0.3);
        robot.cc2.setPosition(0.9);
        forward(-54, 0.4);
        turning(66);
        //move lift motor high
        robot.lm.setTargetPosition(4334);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        while (robot.lm.isBusy()){

        }
        //open claw
        robot.cc.setPosition(0.8);
        robot.cc2.setPosition(0.5);
        turning(-66);

        forward(54, 0.4);
        //move lift motor 0
        robot.lm.setTargetPosition(0);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        while (robot.lm.isBusy()){

        }
        turning(-90);
        forward(-24, 0.4);


    }

    public void forward ( double distanceMoving, double speedMoving){

        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumference));
        robot.setPower(0, 0, 0, 0);
            /*
            double startPosRf = robot.rf.getCurrentPosition();
            double startPosRb = robot.rb.getCurrentPosition();
            double startPosLf = robot.lf.getCurrentPosition();
            double startPosLb = robot.rf.getCurrentPosition();

            double PosRf = Math.abs(startPosRf - Math.round(startPosRf));
            double PosRb = Math.abs(startPosRb - Math.round(startPosRb));
            double PosLf = Math.abs(startPosLf - Math.round(startPosLf));
            double PosLb = Math.abs(startPosLb - Math.round(startPosLb));


             */

        robot.rf.setTargetPosition((int) Math.round(ticks));
        robot.lf.setTargetPosition((int) Math.round(ticks));
        robot.rb.setTargetPosition((int) Math.round(ticks));
        robot.lb.setTargetPosition((int) Math.round(ticks));

        robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(-speedMoving, -speedMoving, -speedMoving, -speedMoving);

        while (opModeIsActive() && (robot.rf.isBusy())) {
            //double robotPos = robot.rf.getCurrentPosition();
            //telemetry.addData("Position of Bot:", robotPos);
        }
        robot.setPower(0, 0, 0, 0);


    }

    public void diagonal ( double distance, double speed, String direction){
        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distance * (wheelMotor / wheelCircumference));
        robot.setPower(0, 0, 0, 0);
        if ("left/up".equals(direction)) {

            robot.rf.setTargetPosition((int) Math.round(ticks));
            robot.lf.setTargetPosition((int) Math.round(ticks));
            robot.rb.setTargetPosition((int) Math.round(ticks));
            robot.lb.setTargetPosition((int) Math.round(ticks));

            robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.setPower(speed, 0, 0, speed);
            //robot.rf.setPower(speed);
            //robot.lb.setPower(speed);

            while (opModeIsActive() && (robot.rf.isBusy())) {
                //double robotPos = robot.rf.getCurrentPosition();
                //telemetry.addData("Position of Bot:", robotPos);
            }
            robot.setPower(0, 0, 0, 0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if ("left/down".equals(direction)) {

            robot.rf.setTargetPosition((int) Math.round(ticks));
            robot.lf.setTargetPosition((int) Math.round(ticks));
            robot.rb.setTargetPosition((int) Math.round(ticks));
            robot.lb.setTargetPosition((int) Math.round(ticks));

            robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.setPower(0, -speed, -speed, 0);
            //robot.rb.setPower(-speed);
            //robot.lf.setPower(-speed);

            while (opModeIsActive() && (robot.rf.isBusy())) {
                //double robotPos = robot.rf.getCurrentPosition();
                //telemetry.addData("Position of Bot:", robotPos);
            }

            robot.setPower(0, 0, 0, 0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if ("right/up".equals(direction)) {

            robot.rf.setTargetPosition((int) Math.round(ticks));
            robot.lf.setTargetPosition((int) Math.round(ticks));
            robot.rb.setTargetPosition((int) Math.round(ticks));
            robot.lb.setTargetPosition((int) Math.round(ticks));

            robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.setPower(0, speed, speed, 0);
            //robot.rb.setPower(speed);
            //robot.lf.setPower(speed);

            while (opModeIsActive() && (robot.rf.isBusy())) {
                //double robotPos = robot.rf.getCurrentPosition();
                //telemetry.addData("Position of Bot:", robotPos);
            }

            robot.setPower(0, 0, 0, 0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if ("right/down".equals(direction)) {

            robot.rf.setTargetPosition((int) Math.round(ticks));
            robot.lf.setTargetPosition((int) Math.round(ticks));
            robot.rb.setTargetPosition((int) Math.round(ticks));
            robot.lb.setTargetPosition((int) Math.round(ticks));

            robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.setPower(-speed, 0, 0, -speed);
            //robot.rf.setPower(-speed);
            //robot.lb.setPower(-speed);

            while (opModeIsActive() && (robot.rf.isBusy())) {
                //double robotPos = robot.rf.getCurrentPosition();
                //telemetry.addData("Position of Bot:", robotPos);
            }

            robot.setPower(0, 0, 0, 0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void turning ( double degrees){

        robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentAngle = robot.gyro.getAngularOrientation().firstAngle;
        double finalAngle = currentAngle + degrees;


        if (finalAngle >= 180) {
            finalAngle -= 360;
        } else if (finalAngle < -180) {
            finalAngle += 360;
        }
        if (degrees >= 0) {
            double errorOfDegree = degrees;
            while (Math.abs(errorOfDegree) > 3) {
                robot.setPower(-0.0055 * errorOfDegree, -0.0055 * errorOfDegree, 0.0055 * errorOfDegree, 0.0055 * errorOfDegree);
                errorOfDegree = finalAngle - robot.gyro.getAngularOrientation().firstAngle;
                if (errorOfDegree > 180) {
                    errorOfDegree -= 360;
                } else if (errorOfDegree < -180) {
                    errorOfDegree += 360;
                }
                errorOfDegree = Math.abs(errorOfDegree);
            }
        } else {
            double errorOfDegree = degrees;
            while (Math.abs(errorOfDegree) > 3) {
                robot.setPower(0.0055 * errorOfDegree, 0.0055 * errorOfDegree, -0.0055 * errorOfDegree, -0.0055 * errorOfDegree);
                errorOfDegree = finalAngle - robot.gyro.getAngularOrientation().firstAngle;
                if (errorOfDegree > 180) {
                    errorOfDegree -= 360;
                } else if (errorOfDegree < -180) {
                    errorOfDegree += 360;
                }
                errorOfDegree = Math.abs(errorOfDegree);
            }
        }
        robot.setPower(0, 0, 0, 0);
    }
}
