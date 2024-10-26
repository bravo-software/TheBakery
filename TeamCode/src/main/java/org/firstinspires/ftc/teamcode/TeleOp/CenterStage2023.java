package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Centerstage.Intake;
import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.Launcher;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;

@TeleOp(name="Test2024", group="TeleOp")
public class Test2024 extends LinearOpMode {

//    public DriveTrain driveTrain;
    private Servo scoop;
    private Servo turn;
    private Servo arm;

    @Override
    public void runOpMode()
    {
        scoop = hardwareMap.get(Servo.class, "scoop");
        arm = hardwareMap.get(Servo.class, "arm");
        turn = hardwareMap.get(Servo.class, "turn");

//        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
//        //Linear Slides
//        int linearSlidesPosition = 1300;
//
//        LinearSlides linearSlides = new LinearSlides(hardwareMap, "Slides", linearSlidesPosition);
//
//        Intake intake = new Intake(hardwareMap, "wrist", "claw1", "claw2");
//
////        HookMechanism hookMechanism = new HookMechanism(hardwareMap, "hanging", "hook");
//
//        Launcher launcher = new Launcher(hardwareMap, "launcher");
//
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive())
        {
            arm.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.b) {
                arm.setPosition(0.9);
            }
            else if (gamepad2.y) {
                arm.setPosition(0.482);
            }
            telemetry.addData("Servo Position", arm.getPosition());
            telemetry.update();
            telemetry.clear();

            turn.setDirection(Servo.Direction.FORWARD); // or REVERSE
            // Control the continuous servo based on gamepad input
            if (gamepad2.left_stick_y > 0) {
                // Full speed clockwise
                turn.setPosition(-1);
            } else if (gamepad2.left_stick_y < 0) {
                // Full speed counter-clockwise
                turn.setPosition(1.0);
            } else if (gamepad2.left_stick_x < 0) {
                turn.setPosition(0);
            }

                // Add telemetry or other controls as needed
                telemetry.update();



            scoop.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.a) { // goes counter-clockwise.
                scoop.setPosition(0.8);
            }
            else if (gamepad2.x) { // goes clockwise.
                scoop.setPosition(1.0);
            }
            telemetry.addData("Servo Position", scoop.getPosition());
            telemetry.update();
            telemetry.clear();



//            telemetry.addData("Slides Pos", String.valueOf(linearSlides.motor.getCurrentPosition()));
//            telemetry.addData("FR Pos", String.valueOf(driveTrain.MotorFR.getCurrentPosition()));
//            telemetry.addData("FL Pos", String.valueOf(driveTrain.MotorFL.getCurrentPosition()));
//            telemetry.addData("BR Pos", String.valueOf(driveTrain.MotorBR.getCurrentPosition()));
//            telemetry.addData("BL Pos", String.valueOf(driveTrain.MotorBL.getCurrentPosition()));
//
//            telemetry.update();


            //Controller 1
//            driveTrain.Drive(gamepad1);
//            hookMechanism.update(gamepad1);


            //Controller 2
//            intake.updateServos(gamepad2.x, gamepad2.a);
//            linearSlides.update(gamepad2.y);
//            launcher.update(gamepad2.b);

        }
    }

}

// centerstage doesn't work on drivershub for some reason
// so use Test2024 to run stuff and rn it has random stuff.
// will have servo code there so I can test it.
// then I will have chassi code to try to move the robot.