package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Core.Vision;
import org.firstinspires.ftc.teamcode.Core.WheelMotors;

@TeleOp(name="Driver2024", group="TeleOp")
public class DriveTrain2023 extends LinearOpMode {

    public WheelMotors wheelMotors;
    private DcMotor MotorFL, MotorBL;
    private DcMotor MotorFR, MotorBR;
    private WebcamName camName;
    private Vision visionEngine;
    public Servo claw1;

    @Override
    public void runOpMode()
    {

        MotorFL  = hardwareMap.get(DcMotor.class, "fL");
        MotorBL  = hardwareMap.get(DcMotor.class, "bL");
        MotorFR  = hardwareMap.get(DcMotor.class, "fR");
        MotorBR  = hardwareMap.get(DcMotor.class, "bR");

        wheelMotors = new WheelMotors(MotorFL, MotorBL, MotorFR, MotorBR);
        claw1    = hardwareMap.get(Servo.class, "claw1");

        //camera stuff
        camName = hardwareMap.get(WebcamName.class, "Webcam 1");
        visionEngine = new Vision(camName);


        wheelMotors.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "New Initialized");
        telemetry.update();

//        wheelMotors.initEncoders();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        wheelMotors.setPower(0);

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("Position FL", MotorFL.getCurrentPosition());
            telemetry.addData("Position BL", MotorBL.getCurrentPosition());
            telemetry.addData("Position FR", MotorFR.getCurrentPosition());
            telemetry.addData("Position BR", MotorBR.getCurrentPosition());
            telemetry.update();
            if (gamepad1.right_stick_x != 0)
            {
                telemetry.addData("Turning", "" + gamepad1.right_stick_x);
                telemetry.update();
                Turn(gamepad1.right_stick_x);
            }
            else
            {
                Turn(0);
            }
            if (gamepad1.left_stick_y != 0)
            {
                telemetry.addData("Not turning", "");
                telemetry.update();
                Drive(gamepad1.left_stick_y);
            }
            else
            {
                Drive(0);
            }
            if (gamepad1.left_stick_x != 0)
            {
                telemetry.addData("Strafing","");
                telemetry.update();

                Strafe(gamepad1.left_stick_x);
            }
            else
            {
                Strafe(0);
            }

            if (gamepad2.left_bumper)
            {
                telemetry.addData("Left Bumper","");
                telemetry.update();
                claw1.setDirection(Servo.Direction.FORWARD);
                // claw1.setPosition(0);
                claw1.setPosition(0.35);
            }
            if (gamepad2.right_bumper)
            {
                telemetry.addData("Right Bumper","");
                telemetry.update();
                claw1.setDirection(Servo.Direction.REVERSE);
                // claw1.setPosition(0);
                claw1.setPosition(0.4);
            }

            // if (gamepad2.y)
            // {
            //     arm.setDirection(DcMotorSimple.Direction.REVERSE);
            //     arm.setTargetPosition(75);
            // }
//            if (gamepad2.left_stick_y != 0)
//            {
//                telemetry.addData("Arm","");
//                telemetry.update();
//                arm.setPower(gamepad2.left_stick_y * 0.6);
//
//            }
        }
    }

    void Strafe(float speed)
    {
        double speedMod = calculateSpeedModifier(0.2, 0.6, 1);
        wheelMotors.Strafe(speed * speedMod);
    }

    //Front is 223rpm, Back is 312rpm
    void Drive (float speed)
    {
        //get speed modifier
        double speedMod = calculateSpeedModifier(0.2, 0.6, 1);
        wheelMotors.Drive(speed * speedMod);
    }
    void Turn (float speed)
    {
        double speedMod = calculateSpeedModifier(0.3, 0.6, 1);
        wheelMotors.Turn(speed * speedMod);
    }


//    private void runSlides(double pos, double power)
//    {
//        double TICKS_PER_MOTOR_REV = 537.6;
//       //countable events per revolution for output shaft, THIS NUMBER CHANGES DEPENDING ON THE TYPE OF MOTOR. VISIT SPECIFICATIONS ON THE WEBSITE THE TEAM GOT THE MOTOR FROM.
//        double DRIVE_GEAR_REDUCTION = 1.0;
//       //drive gear reduction
//        double WHEEL_DIAMETER_INCHES = 1.5;
//       //diameter of wheels
//        double COUNTS_PER_INCH = (TICKS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
//           (WHEEL_DIAMETER_INCHES * 3.1415);
//
//        int newPos;
//
//        if (opModeIsActive()) {
//
//            while (opModeIsActive() && pulley.isBusy())
//            {
//               //Wait for it to finish rotating
//            }
//
//            sleep(250);
//            /*
//            1. Pause to prevent current movements to affect any future movements.
//            */
//       }
//    }

    double calculateSpeedModifier(double slow, double normal, double fast)
    {
        double speedModifier = normal;
        if(gamepad1.a)
        {
            speedModifier = fast;
        }
        else if(gamepad1.b)
        {
            speedModifier = slow;
        }
        else
        {
            speedModifier = normal;
        }
        return speedModifier;
    }
}
