package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Drive Train Tele")
public class AutoDriveTrain extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();




    }
    public void forward(double distanceMoving, double speedMoving) {

        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumference));
        robot.setPower(0, 0, 0, 0);

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

        robot.setPower(speedMoving, speedMoving, speedMoving, speedMoving);

        while(opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 < ticks || opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 > ticks){

        }
        robot.setPower(0,0,0,0);

        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public void diagonal (double distance, double speed, String direction){
        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distance * (wheelMotor / wheelCircumference));
        robot.setPower(0, 0, 0, 0);
        if("left/up".equals(direction)){

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

            while(opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 < ticks || opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 > ticks){

            }
            robot.setPower(0,0,0,0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if("left/down".equals(direction)){

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

            while(opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 < ticks || opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 > ticks){

            }
            robot.setPower(0,0,0,0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if("right/up".equals(direction)){

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

            while(opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 < ticks || opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 > ticks){

            }
            robot.setPower(0,0,0,0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if("right/down".equals(direction)){

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

            robot.setPower(-speed,0, 0, -speed);
            //robot.rf.setPower(-speed);
            //robot.lb.setPower(-speed);

            while(opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 < ticks || opModeIsActive() && (robot.rf.getCurrentPosition()) + 10 > ticks){

            }
            robot.setPower(0,0,0,0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void turning (double degrees) {

        robot.rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentAngle = robot.gyro.getAngularOrientation().firstAngle;
        double finalAngle = currentAngle + degrees;

        if (finalAngle > 180) {
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
        robot.setPower(0,0,0,0);
    }
}
