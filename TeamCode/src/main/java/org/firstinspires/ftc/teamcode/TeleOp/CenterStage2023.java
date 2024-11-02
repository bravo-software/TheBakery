package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;


@TeleOp(name="CenterStage2023", group="TeleOp")
public class CenterStage2023 extends LinearOpMode {

//    private DcMotor leftFront;
//    private DcMotor leftRear;
//    private DcMotor rightFront;
//    private DcMotor rightRear;
    private DriveTrain driveTrain;

    private Servo scoop;
    private Servo turn;
    private Servo arm;
    private DcMotor slide;



    // Define the maximum and minimum encoder positions for the slide
    private final int SLIDE_MAX_POSITION = 5000; // Adjust based on the max height of your slide
    private final int SLIDE_MIN_POSITION = 0;

    @Override
    public void runOpMode()
    {

//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
//        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
//
//        // Set motor directions (adjust these as needed based on your setup)
//        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        // Set Zero Power Behavior to BRAKE for all motors
//        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        scoop = hardwareMap.get(Servo.class, "scoop");
        arm = hardwareMap.get(Servo.class, "arm");
        turn = hardwareMap.get(Servo.class, "turn");
        slide = hardwareMap.get(DcMotor.class, "slide");

        // Set motor direction and zero power behavior
        slide.setDirection(DcMotor.Direction.REVERSE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset encoder position and set to run using encoder
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
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

//            double y = -gamepad1.left_stick_y;       // Forward and backward (inverted for forward motion)
//            double rotation = gamepad1.right_stick_x;
//
//            double leftFrontPower = y + rotation;
//            double rightFrontPower = y - rotation;
//            double leftRearPower = y + rotation;
//            double rightRearPower = y - rotation;
//
//            double maxPower = Math.max(1.0, Math.abs(leftFrontPower));
//            leftFrontPower /= maxPower;
//            rightFrontPower /= maxPower;
//            leftRearPower /= maxPower;
//            rightRearPower /= maxPower;
//
//
//            // Set motor powers
//            leftFront.setPower(leftFrontPower);
//            rightFront.setPower(rightFrontPower);
//            leftRear.setPower(leftRearPower);
//            rightRear.setPower(rightRearPower);
//
//            // Telemetry for debugging
//            telemetry.addData("Left Front Power", leftFrontPower);
//            telemetry.addData("Right Front Power", rightFrontPower);
//            telemetry.addData("Left Rear Power", leftRearPower);
//            telemetry.addData("Right Rear Power", rightRearPower);
//            telemetry.update();



            double extendPower = gamepad2.right_trigger; // Extend slide
            double retractPower = gamepad2.left_trigger;  // Retract slide

            // Calculate desired power based on trigger inputs
            double power = (extendPower - retractPower);

            // Get current encoder position of the slide
            int currentPosition = slide.getCurrentPosition();
            if (extendPower > 0 && currentPosition < SLIDE_MAX_POSITION) {
                // Extend the slide up to the maximum limit
                slide.setPower(extendPower);
            } else if (retractPower > 0 && currentPosition > SLIDE_MIN_POSITION) {
                // Retract the slide down to the minimum limit
                slide.setPower(-retractPower);
            } else {
                // Stop motor if outside of limits or if no trigger is pressed
                slide.setPower(0);
            }

            telemetry.addData("Slide Position", currentPosition);
            telemetry.addData("Motor Power", slide.getPower());
            telemetry.addData("Left Trigger Value", gamepad2.left_trigger);
            telemetry.addData("Right Trigger Value", gamepad2.right_trigger);
            telemetry.update();



            arm.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.b) {
                arm.setPosition(0.9);
            }
            else if (gamepad2.y) {
                arm.setPosition(0.465);
            }
            telemetry.addData("Servo Position", arm.getPosition());
            telemetry.update();
            telemetry.clear();

            turn.setDirection(Servo.Direction.FORWARD); // or REVERSE
            // Control the continuous servo based on gamepad input
            if (gamepad2.right_bumper) {
                turn.setPosition(0);
            } else if (gamepad2.left_stick_y > 0) {
                // Full speed clockwise
                turn.setPosition(-1);
            } else if (gamepad2.left_stick_y < 0) {
                // Full speed counter-clockwise
                turn.setPosition(1.0);

                // Add telemetry or other controls as needed
                telemetry.update();
            }



            scoop.setDirection(Servo.Direction.FORWARD);
            if (gamepad2.a) { // goes counter-clockwise.
                scoop.setPosition(0.4);
            }
            else if (gamepad2.x) { // goes clockwise.
                scoop.setPosition(1.0);
            }
            telemetry.addData("Servo Position", scoop.getPosition());
            telemetry.update();
            telemetry.clear();




            telemetry.addData("fR Pos", String.valueOf(driveTrain.MotorfR.getCurrentPosition()));
            telemetry.addData("fL Pos", String.valueOf(driveTrain.MotorfL.getCurrentPosition()));
            telemetry.addData("bR Pos", String.valueOf(driveTrain.MotorbR.getCurrentPosition()));
            telemetry.addData("bL Pos", String.valueOf(driveTrain.MotorbL.getCurrentPosition()));

            telemetry.update();

//
//            Controller 1
            driveTrain.Drive(gamepad1);
//            hookMechanism.update(gamepad1);


            //Controller 2
//            intake.updateServos(gamepad2.x, gamepad2.a);
//            linearSlides.update(gamepad2.y);
//            launcher.update(gamepad2.b);

        }
    }

}