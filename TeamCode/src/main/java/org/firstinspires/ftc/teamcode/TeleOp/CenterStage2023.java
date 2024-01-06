package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Centerstage.PixelCollector;
import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.HookMechanism;
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
    private HookMechanism hookMechanism;

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

        hookMechanism = new HookMechanism(hardwareMap, "haning", "hook");



        driveTrain.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        linearSlides.extend();
        linearSlides.reset();

        while (opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("Slides Pos", "" + linearSlides.motor.getCurrentPosition());
            telemetry.addData("FR Pos", "" + driveTrain.MotorFR.getCurrentPosition());
            telemetry.addData("FL Pos", "" + driveTrain.MotorFL.getCurrentPosition());
            telemetry.addData("BR Pos", "" + driveTrain.MotorBR.getCurrentPosition());
            telemetry.addData("BL Pos", "" + driveTrain.MotorBL.getCurrentPosition());
            telemetry.update();


            //Controller 1
            driveTrain.Drive(gamepad1);
            hookMechanism.update(gamepad1);


            //Controller 2
            pixelCollector.updateServos(gamepad2.x, gamepad2.a, gamepad2.b, gamepad2.right_bumper);
            linearSlides.update(gamepad2.y);

        }
    }

}

