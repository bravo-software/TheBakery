package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;


@TeleOp(name="CenterStage2023", group="TeleOp")
public class CenterStage2023 extends LinearOpMode {
    private DriveTrain driveTrain;

    private Servo scoop;
    private Servo turn;
    private Servo arm;
    private Servo wallA;
    private Servo wallB;
    private DcMotor slide;
//    private LinearSlides linearSlides;



    // Define the maximum and minimum encoder positions for the slide
//    private final int SLIDE_MAX_POSITION = 5000; // Adjust based on the max height of your slide
//    private final int SLIDE_MIN_POSITION = 0;

    @Override
    public void runOpMode()
    {
        scoop = hardwareMap.get(Servo.class, "scoop");
        arm = hardwareMap.get(Servo.class, "arm");
        turn = hardwareMap.get(Servo.class, "turn");
        wallA= hardwareMap.get(Servo.class, "wallA");
        wallB= hardwareMap.get(Servo.class, "wallB");
        slide = hardwareMap.get(DcMotor.class, "slide");

//        // Set motor direction and zero power behavior
//        slide.setDirection(DcMotor.Direction.FORWARD);
//        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        // Reset encoder position and set to run using encoder
//        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide = hardwareMap.get(DcMotor.class, "slide");

        // Set the motor to run without encoders
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");
        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
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
//            double extendPower = gamepad2.right_trigger; // Extend slide
//            double retractPower = gamepad2.left_trigger;  // Retract slide
//
//            double maxPower = 1.0;
//            int currentPosition = slide.getCurrentPosition();
//            double power = 0; // maybe change to 0.01 or smth

//            if (extendPower > 0 && currentPosition < SLIDE_MAX_POSITION) {
//                // Extend the slide up to the maximum limit
//                power = extendPower * maxPower;
//            } else if (retractPower > 0 && currentPosition > SLIDE_MIN_POSITION) {
//                // Retract the slide down to the minimum limit
//                power = -retractPower * maxPower;
//            } else {
//                // Stop motor if outside of limits or if no trigger is pressed
//                power = 0;
//            }

            if (gamepad2.right_trigger > 0.1) {  // Move slides up when right trigger is pressed
                slide.setPower(gamepad2.right_trigger);  // Use trigger value for variable speed
            } else if (gamepad2.left_trigger > 0.1) {  // Move slides down when left trigger is pressed
                slide.setPower(-gamepad2.left_trigger);  // Use negative trigger value for down direction
            } else {
                slide.setPower(0);  // Keep slides stationary if neither trigger is pressed
            }
            telemetry.addData("Slide Motor Power", slide.getPower());
            telemetry.update();

//            slide.setPower(power);
//
//            telemetry.addData("Slide Position", currentPosition);
//            telemetry.addData("Motor Power", slide.getPower());
//            telemetry.addData("Left Trigger Value", gamepad2.left_trigger);
//            telemetry.addData("Right Trigger Value", gamepad2.right_trigger);
//            telemetry.update();



            arm.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.b) {
          //      arm.setPosition(0.965); //0.965 for white arm
                arm.setPosition (0.985);
            }
            else if (gamepad2.y) {
                arm.setPosition(0.55);

            }
            telemetry.addData("Servo Position", arm.getPosition());
            telemetry.update();
            telemetry.clear();

            turn.setDirection(Servo.Direction.FORWARD); // or REVERSE
            // Control the continuous servo based on gamepad input
            if (gamepad2.left_stick_y > 0) {
                // Full speed clockwise
                turn.setPosition(-1.0);
            } else if (gamepad2.left_stick_y < 0) {
                // Full speed counter-clockwise
//                double reducedSpeed = gamepad2.left_stick_y * 0.5; // Adjust the factor as needed (e.g., 0.5 for 50% speed)
//                turn.setPosition(reducedSpeed);
                turn.setPosition(1.0);

                // Add telemetry or other controls as needed
                telemetry.update();
            }

            wallA.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.dpad_up) { //goes counter-clockwise
                wallA.setPosition(0.3);
            }
            else if (gamepad2.dpad_down) { // goes clockwise
                wallA.setPosition(1.0);
            }
            telemetry.addData( "Servo Position", wallA. getPosition ());
            telemetry.update ();
            telemetry.clear();

            wallB.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.dpad_right) { //goes counter-clockwise
                wallB.setPosition(0.3);
            }
            else if (gamepad2.dpad_left) {
                wallB.setPosition(1.0);
            }
            telemetry.addData("Servo Position", wallB.getPosition());
            telemetry.update();
            telemetry.clear();


            scoop.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.a) { // goes counter-clockwise.
                scoop.setPosition(0.4);
         //       scoop.setPosition(0.3);
            }
            else if (gamepad2.x) { // goes clockwise.
                scoop.setPosition(1.0);
            }
            telemetry.addData("Servo Position", scoop.getPosition());
            telemetry.update();
            telemetry.clear();




            telemetry.addData("fR Pos", String.valueOf(driveTrain.MotorfR.getPower()));
            telemetry.addData("fL Pos", String.valueOf(driveTrain.MotorfL.getPower()));
            telemetry.addData("bR Pos", String.valueOf(driveTrain.MotorbR.getPower()));
            telemetry.addData("bL Pos", String.valueOf(driveTrain.MotorbL.getPower()));
            //getCurrentPosition()

            telemetry.update();

//
//            Controller 1
            driveTrain.Drive(gamepad1);
//            hookMechanism.update(gamepad1);


            //Controller 2
//            intake.updateServos(gamepad2.x, gamepad2.a);
//            linearSlides.update(gamepad1.a);
//            launcher.update(gamepad2.b);

        }
    }

}