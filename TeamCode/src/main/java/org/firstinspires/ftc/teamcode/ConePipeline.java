package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ConePipeline extends OpenCvPipeline {
    private Mat workingMatrix = new Mat();
    public String position = "CENTER";

    public ConePipeline(){

    }

    public final Mat processFrame(Mat input){
        input.copyTo(workingMatrix);

        if(workingMatrix.empty()){
            return input;
        }
        Mat matCenter = workingMatrix.submat(120,150,10,50);

        Imgproc.rectangle(workingMatrix, new Rect(10, 120, 40, 30), new Scalar(0,255,0));

        //0 =red, 1 = green, 2 = blue
        double centerTotal =Core.sumElems(matCenter).val[2];
        centerTotal /= matCenter.cols() * matCenter.rows();

        //logic here:


        return workingMatrix;
    }
}