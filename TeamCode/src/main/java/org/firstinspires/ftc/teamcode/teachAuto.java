package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Teach Auto")
public class teachAuto extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        waitForStart();
        runtime.reset();

        //forward less 1
        while(runtime.seconds() < 1){
            robot.setPower(0.4,0.4,0.4,0.4);
        }
        runtime.reset();
        //forward again 1
        while(runtime.seconds() < 1){
            robot.setPower(0.4,0.4,0.4,0.4);
        }
        while(runtime.seconds() > 1){
            robot.setPower(0,0,0,0);
        }




    }
}
