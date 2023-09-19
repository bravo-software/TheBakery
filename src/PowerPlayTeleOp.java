package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Hello!", group="Drive")
public class PowerPlayTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo claw = hardwareMap.get(Servo.class, "claw");
        Servo claw2 = hardwareMap.get(Servo.class, "claw2");

        DcMotor motor1 = hardwareMap.get(DcMotor.class, "arm");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "arm2");

        DcMotor motorFL= hardwareMap.dcMotor.get("fL");
        DcMotor motorBL= hardwareMap.dcMotor.get("bL");
        DcMotor motorFR= hardwareMap.dcMotor.get("fR");
        DcMotor motorBR= hardwareMap.dcMotor.get("bR");

        // motorBL.setDirection(DcMotor.Direction.REVERSE);
        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

        waitForStart();

        if (isStopRequested()) return;

        long start = System.currentTimeMillis();
        while (opModeIsActive()) {
            if (gamepad2.x) {
                claw.setPosition(1);
                claw2.setPosition(0.8);
            } else {
                claw.setPosition(0.5);
                claw2.setPosition(-0.35);
            }

            if (gamepad2.left_stick_y != 0) {
                start = System.currentTimeMillis();
                if (gamepad2.left_stick_y < 0) {
                    motor1.setPower(gamepad2.left_stick_y);
                    motor2.setPower(gamepad2.left_stick_y);
                } else {
                    motor1.setPower(gamepad2.left_stick_y * 0.2);
                    motor2.setPower(gamepad2.left_stick_y * 0.2);
                }
            } else {
                if (System.currentTimeMillis() - start > 20) {
                    motor1.setPower(-0.35);
                    motor2.setPower(-0.35);
                } else {
                    motor1.setPower(0);
                    motor2.setPower(0);
                }
            }

            double mult = 0.45;

            double y = -gamepad1.left_stick_y * mult; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1 * mult; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x * mult;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFL.setPower(frontLeftPower);
            motorBL.setPower(backLeftPower);
            motorFR.setPower(frontRightPower);
            motorBR.setPower(backRightPower);
        }
    }
}
