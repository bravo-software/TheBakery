package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Driver", group="TeleOp")
public class DriveTrain2023 extends LinearOpMode {
    public DcMotor MotorFL, MotorBL;
    public DcMotor MotorFR, MotorBR;

    @Override
    public void runOpMode()
    {

        MotorFL  = hardwareMap.get(DcMotor.class, "fL");
        MotorBL  = hardwareMap.get(DcMotor.class, "bL");
        MotorFR  = hardwareMap.get(DcMotor.class, "fR");
        MotorBR  = hardwareMap.get(DcMotor.class, "bR");

        MotorFL.setDirection(DcMotor.Direction.REVERSE);
        MotorFR.setDirection(DcMotor.Direction.REVERSE);
        MotorBL.setDirection(DcMotor.Direction.REVERSE);
        MotorBR.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        MotorFL.setPower(0);
        MotorBL.setPower(0);
        MotorFR.setPower(0);
        MotorBR.setPower(0);

        waitForStart();
        while (opModeIsActive())
        {
            if (gamepad1.right_stick_x != 0)
            {
                telemetry.addData("Turning", "" + gamepad1.right_stick_x);
                telemetry.update();
                Turn(gamepad1.right_stick_x);
            }
            if (gamepad1.left_stick_y != 0)
            {
                telemetry.addData("Not turning", "");
                telemetry.update();
                Drive(gamepad1.left_stick_y);
            }
            if (gamepad1.left_stick_x != 0)
            {
                telemetry.addData("Strafing","");
                telemetry.update();

                Strafe(gamepad1.left_stick_x);
            }
        }
    }

    void Strafe(float speed)
    {
        if(gamepad1.a)
        {
            MotorFL.setPower(1*-speed);
            //workaround Backleft doesnt work
            MotorBL.setPower(1*-speed);
            MotorFR.setPower(1*speed);
            MotorBR.setPower(1*-speed);
        }
        if(gamepad1.b)
        {
            MotorFL.setPower(0.2*-speed);
            //Backleft doesnt wrok
            MotorBL.setPower(0.2*-speed);
            MotorFR.setPower(0.2* speed);
            MotorBR.setPower(0.2*-speed);
        }
        else
        {
            MotorFL.setPower(0.6 * -speed);
            //Backleft doesnt work
            MotorBL.setPower(0.6 * -speed);
            MotorFR.setPower(0.6 *  speed);
            MotorBR.setPower(0.6 * -speed);
        }
    }

    //Front is 223rpm, Back is 312rpm
    void Drive (float speed)
    {
        if(gamepad1.a)
        {
            MotorFL.setPower(1*speed);
            //workaround Backleft doesnt work
            MotorBL.setPower(1*-speed);
            MotorFR.setPower(1*speed);
            MotorBR.setPower(1*speed);
        }
        if(gamepad1.b)
        {
            MotorFL.setPower(0.2*speed);
            //Backleft doesnt wrok
            MotorBL.setPower(0.2*-speed);
            MotorFR.setPower(0.2*speed);
            MotorBR.setPower(0.2*speed);
        }
        else
        {
            MotorFL.setPower(0.6 * speed);
            //Backleft doesnt work
            MotorBL.setPower(0.6 * -speed);
            MotorFR.setPower(0.6 * speed);
            MotorBR.setPower(0.6 * speed);
        }
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

    void Turn (float speed)
    {
        if(gamepad1.a)
        {
            MotorFL.setPower(1*-speed);
            MotorBL.setPower(1*speed);
            MotorFR.setPower(1*speed);
            MotorBR.setPower(1*speed);

        }
        if(gamepad1.b)
        {
            MotorFL.setPower(0.3*-speed);
            MotorBL.setPower(0.3*speed);
            MotorFR.setPower(0.3*speed);
            MotorBR.setPower(0.3*speed);
        }

        else
        {
            MotorFL.setPower(0.6 * -speed);
            MotorBL.setPower(0.6 * speed);
            MotorFR.setPower(0.6 * speed);
            MotorBR.setPower(0.6 * speed);
        }
    }
}