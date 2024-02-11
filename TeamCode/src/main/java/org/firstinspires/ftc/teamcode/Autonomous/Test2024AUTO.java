package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

@Autonomous(name="Test2024AUTO", group="Autonomous")
public class Test2024AUTO extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches

    public void runOpMode()
    {
        DriveTrain driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");

        waitForStart();
        while(opModeIsActive())
        {
            telemetry.clear();
            telemetry.addData("Status", "Running");
            telemetry.update();

            driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveTrain.setTargetPosition(1000);
            driveTrain.DirectFoward(0.5);



        }
    }
}
