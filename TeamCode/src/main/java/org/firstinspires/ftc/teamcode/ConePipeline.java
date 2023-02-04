package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class ConePipeline extends OpenCvPipeline {
    public Mat workingMatrix = new Mat();
    public String position = "BLUE";
 double centerTotal = 0.0;
    public ConePipeline(){

    }

    public final Mat processFrame(Mat input){
        input.copyTo(workingMatrix);

        if(workingMatrix.empty()){
            return input;
        }
        Mat matCenter = workingMatrix.submat(120,150,10,50);

        Imgproc.rectangle(workingMatrix, new Rect(115, 205, 25, 75), new Scalar(0,255,0));

        //0 = red, 1 = green, 2 = blue
         centerTotal = Core.sumElems(matCenter).val[2];
        centerTotal /= matCenter.cols() * matCenter.rows();
        //centerTotal = Math.round(centerTotal);

        //logic here:
        //Blue = 200 Red = 100 Green = 0 These are fake numbers
        /**CHANGE LATER!**/
        if(centerTotal == 2){
            position = "BLUE";
        }
        if(centerTotal == 1){
            position = "GREEN";
        }
        if(centerTotal == 0){
            position = "RED";
        }

        return workingMatrix;
    }
}
