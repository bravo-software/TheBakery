package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Centerstage.PixelCollector;
import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;
import org.firstinspires.ftc.teamcode.Core.Vision;

@TeleOp(name="CenterStage2023", group="TeleOp")
public class CenterStage2023 extends LinearOpMode {

    public DriveTrain driveTrain;
    private LinearSlides linearSlides;
    private final int linearSlidesPosition = 1540;

    private final int linearInitializationSlidesPosition = 100;

    private LinearSlides temp;

    private Vision visionEngine;
    private PixelCollector pixelCollector;

    @Override
    public void runOpMode()
    {

        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        //camera stuff
        visionEngine = new Vision(hardwareMap, "Webcam 1");
        //Linear Slides
        linearSlides = new LinearSlides(hardwareMap, "Slides", linearSlidesPosition);
        //PixelCollector
        pixelCollector = new PixelCollector(hardwareMap, "wrist", "trapdoor", "launcher");

        driveTrain.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        linearSlides.extend();
        linearSlides.reset();

        while (opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("Slides Pos", "" + linearSlides.getPosition());
            telemetry.update();


            //Controller 1
            driveTrain.Drive(gamepad1);


            //Controller 2
            pixelCollector.updateServos(gamepad2.x, gamepad2.a, gamepad2.b, gamepad2.right_bumper);
            linearSlides.update(gamepad2.y);

        }
    }

}

