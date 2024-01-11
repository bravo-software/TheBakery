package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Centerstage.PixelCollector;
import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.HookMechanism;
import org.firstinspires.ftc.teamcode.Core.Launcher;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;
import org.firstinspires.ftc.teamcode.Core.Vision;

@TeleOp(name="CenterStage2023", group="TeleOp")
public class CenterStage2023 extends LinearOpMode {

    public DriveTrain driveTrain;

    private LinearSlides linearSlides;

    private Vision visionEngine;
    private PixelCollector pixelCollector;
    private HookMechanism hookMechanism;
    private Launcher launcher;

    @Override
    public void runOpMode()
    {

        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        //camera stuff
        visionEngine = new Vision(hardwareMap, "Webcam 1");
        //Linear Slides
        int linearSlidesPosition = 1540;
        linearSlides = new LinearSlides(hardwareMap, "Slides", linearSlidesPosition);
        //PixelCollector
        pixelCollector = new PixelCollector(hardwareMap, "wrist", "claw");

        hookMechanism = new HookMechanism(hardwareMap, "hanging", "hook");

        launcher = new Launcher(hardwareMap, "launcher");



        driveTrain.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        linearSlides.extend();
        linearSlides.reset();

        while (opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("Slides Pos", String.valueOf(linearSlides.motor.getCurrentPosition()));
            telemetry.addData("FR Pos", String.valueOf(driveTrain.MotorFR.getCurrentPosition()));
            telemetry.addData("FL Pos", String.valueOf(driveTrain.MotorFL.getCurrentPosition()));
            telemetry.addData("BR Pos", String.valueOf(driveTrain.MotorBR.getCurrentPosition()));
            telemetry.addData("BL Pos", String.valueOf(driveTrain.MotorBL.getCurrentPosition()));
            telemetry.addData("Linear Actuator Pos", String.valueOf(hookMechanism.getLinearActuatorPosition()));
            telemetry.addData("Hook Pos", String.valueOf(hookMechanism.getHookPosition()));
            telemetry.update();


            //Controller 1
            driveTrain.Drive(gamepad1);
            hookMechanism.update(gamepad1);


            //Controller 2
            pixelCollector.updateServos(gamepad2.x, gamepad2.a);
            linearSlides.update(gamepad2.y);
            launcher.update(gamepad2.b);

        }
    }

}

