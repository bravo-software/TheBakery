package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Test2024AUTO", group="Autonomous")
public class Test2024AUTO extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    double tick_per_rotation = 537.7;
    public void runOpMode()
    {
        waitForStart();
        DcMotor test = hardwareMap.get(DcMotor.class, "test");

        test.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        test.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        test.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        test.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        while(opModeIsActive()) {
            test.setTargetPosition((int)(tick_per_rotation * 2));
            test.setPower(0.5);
        }

    }
}
