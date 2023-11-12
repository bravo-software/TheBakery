package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Driver", group="TeleOp")
public class DriveTrain2023 extends LinearOpMode {
    public DcMotor MotorFL, MotorBL;
    public DcMotor MotorFR, MotorBR;

    public Servo claw1, claw2;
    public Servo arm;

    public double armMultiplier = 2;
    @Override
    public void runOpMode()
    {
        //Setup Motors
        MotorFL  = hardwareMap.get(DcMotor.class, "fL");
        MotorBL  = hardwareMap.get(DcMotor.class, "bL");
        MotorFR  = hardwareMap.get(DcMotor.class, "fR");
        MotorBR  = hardwareMap.get(DcMotor.class, "bR");

        claw1 = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class, "claw2");
        arm = hardwareMap.get(Servo.class, "arm");

        //reset Encoders
        MotorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //run using encoders
        MotorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
            if(gamepad2.left_stick_y != 0)
            {
                //needs changing
                arm.setPosition( arm.getPosition() + gamepad2.left_stick_y  * armMultiplier / arm.MAX_POSITION);
            }

            if(gamepad2.a)
            {
                //needs changing
                claw1.setPosition(1);
                claw2.setPosition(0.8);
            }

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