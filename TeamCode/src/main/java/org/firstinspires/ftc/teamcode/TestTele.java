package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Tele Op Test")
public class TestTele extends LinearOpMode {

    Hardware robot = Hardware.getInstance();

    @Override
    public void runOpMode() throws InterruptedException{
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //wait for Driver to press Start
        
        waitForStart();

        while(opModeIsActive()){
            double forward;
            double strafing;
            double turning;

            forward = gamepad1.left_stick_y;
            turning = -gamepad1.right_stick_x;
            strafing = -gamepad1.left_stick_x;

            //slow drive
            if(gamepad1.dpad_up){
                forward = -.25;
            }
<<<<<<< Updated upstream


            //strafing


=======
            
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
>>>>>>> Stashed changes
            //slow turning

            //cc Servo

            if(gamepad2.x) {
                robot.cc.setPosition(0);
            } else {
                robot.cc.setPosition(0.377);

            }



        }

    }
}
