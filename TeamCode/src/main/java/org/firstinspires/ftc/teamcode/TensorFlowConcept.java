/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Hardware;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

/**
 * This 2022-2023 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine which image is being presented to the robot.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "Concept: TensorFlow Object Detection Webcam")
public class TensorFlowConcept extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    /*
     * Specify the source for the Tensor Flow Model.
     * If the TensorFlowLite object model is included in the Robot Controller App as an "asset",
     * the OpMode must to load it using loadModelFromAsset().  However, if a team generated model
     * has been downloaded to the Robot Controller's SD FLASH memory, it must to be loaded using loadModelFromFile()
     * Here we assume it's an Asset.    Also see method initTfod() below .
     */
    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";


    String position = "";
    private static final String[] LABELS = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel"
    };

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "Aa4sIFb/////AAABmcu13SEwaUP8pr8UBa5/r8s9Y5Zw2vT6elOYarLOBF1JCOHA0JgHcZ2TVVg3EK6eAD+cki7DTg4nEEv36vihm3HzDRYMNFn1K52KofrfFJ0MFV/acm9OR45ZJ4siZAiwXxuwSPv8sem3EoK4tqRw0bRSMefehLvOMzKw5Hb38A+RLWVdE5BrW6ASXRYvTWYW8GUDxEsw9L7S7dfkH/64b1qNFBCzZma18kSp7u/qGuwlb4mF4zy3x1CO2O5Mb7q+usIEwT37zSgMtVfPVS5z3/MwObuDFHp2DDs3g525o9XZ+9wp63ij/Qou3ifErnffv2oFFH9nMzZk2CWTRYz5khmfn6LP17eNZy3w3dAPu+EM";


    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                OpenCvCamera webCam;
                webCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "wc1"));
                webCam.openCameraDevice();
                FtcDashboard.getInstance().startCameraStream(webCam, 0);
                webCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_RIGHT);

                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Objects Detected", updatedRecognitions.size());

                        // step through the list of recognitions and display image position/size information for each one
                        // Note: "Image number" refers to the randomized image orientation/number
                        for (Recognition recognition : updatedRecognitions) {
                            double col = (recognition.getLeft() + recognition.getRight()) / 2;
                            double row = (recognition.getTop() + recognition.getBottom()) / 2;
                            double width = Math.abs(recognition.getRight() - recognition.getLeft());
                            double height = Math.abs(recognition.getTop() - recognition.getBottom());

                            position = recognition.getLabel();

                            telemetry.addData("", " ");
                            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                            telemetry.addData("- Position (Row/Col)", "%.0f / %.0f", row, col);
                            telemetry.addData("- Size (Width/Height)", "%.0f / %.0f", width, height);
                        }
                        telemetry.update();
                    }
                }
                if (position.equals("1 Bolt") || position.equals("2 Bulb") || position.equals("3 Panel")) {
                    tfod = null;
                }
            }
                if (position == "1 Bolt") {
                    forward(24, 0.4);
                    turning(90);
                    forward(24, 0.4);

                } else if (position == "2 Bulb") {
                    forward(24, 0.4);


                } else if (position == "3 Panel") {
                    forward(-24, 0.4);
                    turning(-90);
                    forward(-24, 0.4);


                }


        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "wc1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
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
