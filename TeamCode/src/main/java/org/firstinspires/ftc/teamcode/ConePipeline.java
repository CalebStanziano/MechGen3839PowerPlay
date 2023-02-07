package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class ConePipeline extends OpenCvPipeline {
    public Mat workingMatrix = new Mat();
    public String positionCone = "BLUE";
    double centerTotal0 = 0.0;
    double centerTotal1 = 0.0;
 double centerTotal2 = 0.0;
    public ConePipeline(){

    }

    public final Mat processFrame(Mat input){
        input.copyTo(workingMatrix);

        if(workingMatrix.empty()){
            return input;
        }
        Mat matCenterBlue = workingMatrix.submat(120,150,10,50);

        Imgproc.rectangle(workingMatrix, new Rect(110, 260, 25, 65), new Scalar(0,255,0));

        //0 = red, 1 = green, 2 = blue
         centerTotal2 = Core.sumElems(matCenterBlue).val[2];
        centerTotal2 /= matCenterBlue.cols() * matCenterBlue.rows();
        centerTotal0 = Core.sumElems(matCenterBlue).val[0];
        centerTotal0 /= matCenterBlue.cols() * matCenterBlue.rows();
        centerTotal1 = Core.sumElems(matCenterBlue).val[1];
        centerTotal1 /= matCenterBlue.cols() * matCenterBlue.rows();


        //logic here:
        //Blue = 200 Red = 100 Green = 0 These are fake numbers
        /**CHANGE LATER!**/
        if(centerTotal1 < 90){
            positionCone = "GREEN";
        }
        else if(centerTotal2 == 100 && centerTotal0 == 80){
            positionCone = "RED";
        } else if(centerTotal2 == 100){
            positionCone = "BLUE";
        }

        return workingMatrix;
    }
}
