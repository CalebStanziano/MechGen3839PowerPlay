package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "FirstAuto")
public class FirstAuto extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        //forwards less than 1 sec
        while(runtime.seconds() <= 1){
            robot.setPower(0.4, 0.4, 0.4, 0.4);

        }
        runtime.reset();
        //forward again
        while(runtime.seconds() <= 1){
            robot.setPower(0.4, 0.4, 1.4, 0.4);

        }
    }


}
