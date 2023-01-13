package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name = "Cycle Blue Auto")
public class AutoDriveTrain extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        int botPosition = 0;
        robot.lm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //stackPos depreciates as the cycle continues.
        int stackPos = 610;
        double angle = robot.gyro.getAngularOrientation().firstAngle;
        //while(opModeIsActive()){
            //telemetry.addData("Angle: ", angle);
            //telemetry.update();
        //}

        //Stack 1 = 1299
        //Stack 2 = 980
        //Stack 3 = 752
        //Stack 4 = 420
        //Stack 5 = 92

        waitForStart();
        runtime.reset();
        runtime.startTime();

/** Get signal cone out of the way */
        //open claw
        robot.cc.setPosition(0.8);
        robot.cc2.setPosition(0.5);
        //forward
        forward(-55, 0.4);
        forward(5, 0.4);
        //close claw
        robot.cc.setPosition(0.3);
        robot.cc2.setPosition(0.9);

/** Start Cycle 1 */
        //face stack
        turning(277);
        //move towards the stack
        forward(-14.35, 0.4);
        //close claw
        robot.cc.setPosition(0.3);
        robot.cc2.setPosition(0.9);
        //move lift motor to stackPos 1
        robot.lm.setTargetPosition(1100);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        //stackPos -=100;
        while (robot.lm.isBusy()){

        }
        //open claw
        robot.cc.setPosition(0.8);
        robot.cc2.setPosition(0.5);
        forward(-8, 0.4);
        //while(robot.rb.isBusy()){

        //}
        //close claw
        robot.cc.setPosition(0.3);
        robot.cc2.setPosition(0.9);
        sleep(2000);
        //move back a little
        forward(3,0.25);

        //lift
        robot.lm.setTargetPosition(2000);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(.75);
        while(robot.lm.isBusy()){

        }

        forward(24, 0.4);
        //move lift motor ope to high junction
        robot.lm.setTargetPosition(4500);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        sleep(2000);
        //turn to face high junction
        turning(138);

        //open claw
        robot.cc.setPosition(0.8);
        robot.cc2.setPosition(0.5);

        //down to grab stack 2
        robot.lm.setTargetPosition(780);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        while(robot.lm.isBusy()){

        }
        //turn to face stack
        turning(230);

/** Start Cycle 2
        //open claw
        robot.cc.setPosition(0.8);
        robot.cc2.setPosition(0.5);
        //forward to stack
        forward(-24, 0.4);
        //close claw
        robot.cc.setPosition(0.3);
        robot.cc2.setPosition(0.9);
        //sleep(2000);
        //lift
        robot.lm.setTargetPosition(2000);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(.75);
        while(robot.lm.isBusy()){

        }
        forward(24, 0.4);
        //move lift motor ope to high junction
        robot.lm.setTargetPosition(4248);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        sleep(1000);
        //turn to face high junction
        turning(140);

        //open claw
        robot.cc.setPosition(0.8);
        robot.cc2.setPosition(0.5);

        //down to grab stack 3
        robot.lm.setTargetPosition(552);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        while(robot.lm.isBusy()){

        }
*/
        //while(runtime.time() < 31){

        //}






        /*
        forward(-5, 0.4);
        //open claw
        robot.cc.setPosition(1);
        robot.cc2.setPosition(0);
        //move lift motor down to 0
        robot.lm.setTargetPosition(0);
        robot.lm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lm.setPower(1);
        //undo previeous turn
        turning(23);
        while(robot.rb.isBusy()){ }
        //move forward to align
        forward(-20, 0.4);
        while(robot.rb.isBusy()){ }
        //turn 90
        turning(95);
        while(robot.rb.isBusy()){ }
        //forward to park
        forward(-12, 0.4);
        while(robot.rb.isBusy()){ }




        /* Possible auto (2 points)
turning(-90);
        forward(-36, 0.4);
         */



    }
    public void forward(double distanceMoving, double speedMoving) {

        double wheelCircumference = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumference));
        robot.setPower(0, 0, 0, 0);
        double startPosRf = robot.rf.getCurrentPosition();
        double startPosRb = robot.rb.getCurrentPosition();
        double startPosLf = robot.lf.getCurrentPosition();
        double startPosLb = robot.rf.getCurrentPosition();

        double PosRf = Math.abs(startPosRf - Math.round(startPosRf));
        double PosRb = Math.abs(startPosRb - Math.round(startPosRb));
        double PosLf = Math.abs(startPosLf - Math.round(startPosLf));
        double PosLb = Math.abs(startPosLb - Math.round(startPosLb));



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

        while(opModeIsActive() && (robot.rf.isBusy())){
            double robotPos = robot.rf.getCurrentPosition();
            telemetry.addData("Position of Bot:", robotPos );
        }
        robot.setPower(0,0,0,0);


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

            while(opModeIsActive() && (robot.rf.isBusy())){
                double robotPos = robot.rf.getCurrentPosition();
                telemetry.addData("Position of Bot:", robotPos );
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

            while(opModeIsActive() && (robot.rf.isBusy())){
                double robotPos = robot.rf.getCurrentPosition();
                telemetry.addData("Position of Bot:", robotPos );
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

            while(opModeIsActive() && (robot.rf.isBusy())){
                double robotPos = robot.rf.getCurrentPosition();
                telemetry.addData("Position of Bot:", robotPos );
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

            while(opModeIsActive() && (robot.rf.isBusy())){
                double robotPos = robot.rf.getCurrentPosition();
                telemetry.addData("Position of Bot:", robotPos );
            }

            robot.setPower(0,0,0,0);

            robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void turning (double degrees) {

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
        robot.setPower(0,0,0,0);
    }
}
