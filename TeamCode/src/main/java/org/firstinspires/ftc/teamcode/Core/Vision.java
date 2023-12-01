package org.firstinspires.ftc.teamcode.Core;


//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
//import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
//import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

public class Vision
{
    AprilTagProcessor myAprilTagProcessor;
    TfodProcessor myTfodProcessor;
    VisionPortal myVisionPortal;
    CameraName cameraName;


// Create the AprilTag processor and assign it to a variable.

    public Vision(WebcamName webcamName)
    {
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        myTfodProcessor = TfodProcessor.easyCreateWithDefaults();
        cameraName = webcamName;
        myVisionPortal = VisionPortal.easyCreateWithDefaults(cameraName, myAprilTagProcessor, myTfodProcessor);
    }
}
