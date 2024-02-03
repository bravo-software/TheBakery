package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Test2024", group="TeleOp")

public class Test2024 extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        DcMotor test = hardwareMap.get(DcMotor.class, "test");
        test.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        test.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("test", test.getCurrentPosition());
        telemetry.update();
        waitForStart();

        while (opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("test", test.getCurrentPosition());
            telemetry.update();

            test.setPower(0.1);

        }



    }
}
