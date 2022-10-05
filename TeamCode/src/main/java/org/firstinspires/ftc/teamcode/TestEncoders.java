package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Encoder Test")
public class TestEncoders extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        int position  = 0;
        boolean aPressed = false;
        boolean yPressed = false;

        while(opModeIsActive()){
            //y to increase
            if(gamepad1.y && !yPressed){
                yPressed = true;
                position += 10;
                robot.tm.setTargetPosition(position);
            } else {
                yPressed = false;
            }
            //a to decrease
            if (gamepad1.a && !aPressed) {
                aPressed = true;
                position -= 10;
                robot.tm.setTargetPosition(position);
            } else {
                aPressed = false;
            }
        }
    }
}
