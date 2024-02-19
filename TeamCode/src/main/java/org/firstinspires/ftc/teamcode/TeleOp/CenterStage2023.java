package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;

@TeleOp(name="CenterStage2023", group="TeleOp")
public class CenterStage2023 extends LinearOpMode {

    public DriveTrain driveTrain;

    @Override
    public void runOpMode()
    {

        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
        //Linear Slides
        int linearSlidesPosition = 1540;

        LinearSlides linearSlides = new LinearSlides(hardwareMap, "Slides", linearSlidesPosition);

//        Intake intake = new Intake(hardwareMap, "wrist", "claw");

//        HookMechanism hookMechanism = new HookMechanism(hardwareMap, "hanging", "hook");

//        Launcher launcher = new Launcher(hardwareMap, "launcher");

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

            telemetry.update();


            //Controller 1
            driveTrain.Drive(gamepad1);
//            hookMechanism.update(gamepad1);


            //Controller 2
//            intake.updateServos(gamepad2.x, gamepad2.a);
            linearSlides.update(gamepad2.y);
//            launcher.update(gamepad2.b);

        }
    }

}

