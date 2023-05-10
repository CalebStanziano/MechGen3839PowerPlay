package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous (name = "Auto")
public class AutoMini extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        if(){
            forward(30, 0.7);
        } else {
            forward(30, 0.7);

        }
    }


    public void forward(double distanceMoving, double speedMoving) {

        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumference));
        robot.setPower(0, 0);

        double startPosRb = robot.m0.getCurrentPosition();
        double startPosLb = robot.m1.getCurrentPosition();

        double PosRb = Math.abs(startPosRb - Math.round(startPosRb));
        double PosLb = Math.abs(startPosLb - Math.round(startPosLb));

        robot.m0.setTargetPosition((int) Math.round(ticks));
        robot.m1.setTargetPosition((int) Math.round(ticks));

        robot.m0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.m0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(-speedMoving, -speedMoving);

        while (opModeIsActive() && (robot.m0.isBusy())) {
            double robotPos = robot.m0.getCurrentPosition();
            telemetry.addData("Position of Bot:", robotPos);
        }
        robot.setPower(0, 0);


    }

    public void turning (double degrees) {

        robot.m0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.m0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
                robot.setPower(0.0055 * errorOfDegree, 0.0055 * errorOfDegree);
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
                robot.setPower(-0.0055 * errorOfDegree, -0.0055 * errorOfDegree);
                errorOfDegree = finalAngle - robot.gyro.getAngularOrientation().firstAngle;
                if (errorOfDegree > 180) {
                    errorOfDegree -= 360;
                } else if (errorOfDegree < -180) {
                    errorOfDegree += 360;
                }
                errorOfDegree = Math.abs(errorOfDegree);
            }
        }
        robot.setPower(0,0);
    }
}

