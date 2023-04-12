package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Robot: Teleop juan", group="Robot")
public class TeleOp1 extends LinearOpMode {
Hardware1 robot = Hardware1.getInstance();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status","Initialized");
        telemetry.update();
        waitForStart();
        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(opModeIsActive()){
            double forward;
            double strafing;
            double turning;
            forward = gamepad1.left_stick_y;
            strafing = gamepad1.left_stick_x;
            turning = gamepad1.right_stick_x;
            if(gamepad1.a){
                robot.Claw.setPosition(1);
            }



        }



    }
}
