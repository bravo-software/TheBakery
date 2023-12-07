package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Centerstage.PixelCollector;
import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;
import org.firstinspires.ftc.teamcode.Core.Vision;

@TeleOp(name="Driver2024", group="TeleOp")
public class DriveTrain2023 extends LinearOpMode {

    public DriveTrain driveTrain;
    private WebcamName camName;

    private LinearSlides linearSlides;
    private final int linearSlidesPosition = 1100;
    private Vision visionEngine;

    private PixelCollector pixelCollector;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");

        //camera stuff
        visionEngine = new Vision(hardwareMap, "Webcam 1");

        //Linear Slides
        linearSlides = new LinearSlides(hardwareMap, "Slides", linearSlidesPosition);

        //PixelCollector
        pixelCollector = new PixelCollector(hardwareMap, "intake", "wrist", "trapdoor");

        driveTrain.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.clear();
            telemetry.addData("Position FL", driveTrain.MotorFL.getCurrentPosition());
            telemetry.addData("Position BL", driveTrain.MotorBL.getCurrentPosition());
            telemetry.addData("Position FR", driveTrain.MotorFR.getCurrentPosition());
            telemetry.addData("Position BR", driveTrain.MotorBR.getCurrentPosition());

            telemetry.addData("Linear Slides Position", linearSlides.getPosition());
            telemetry.update();

            driveTrain.Drive(gamepad1);

            if (gamepad2.y) {
                if (linearSlides.isTop())
                    linearSlides.reset();
                else
                    linearSlides.extend();
            }
            if (gamepad2.a) {
                pixelCollector.flipIntake();
            }
            if (gamepad2.b) {
                pixelCollector.flipWristPosition();
            }
            if (gamepad2.x) {
                pixelCollector.flipTrapdoorPosition();
            }
        }
    }

}

