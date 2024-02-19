package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

@Autonomous(name="Test2024AUTO", group="Autonomous")
public class Test2024AUTO extends LinearOpMode
{

        public void runOpMode()
        {
            DriveTrain driveTrain = new DriveTrain(hardwareMap, "fL", "bL", "fR", "bR");
            Driver driver = new Driver(hardwareMap);

            driveTrain.MotorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveTrain.MotorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveTrain.MotorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveTrain.MotorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            waitForStart();
            while(opModeIsActive())
            {
                telemetry.clear();
                telemetry.addData("Status", "Running");
                telemetry.addData("MotorFL", driveTrain.MotorFL.getCurrentPosition());
                telemetry.addData("MotorFR", driveTrain.MotorFR.getCurrentPosition());
                telemetry.addData("MotorBL", driveTrain.MotorBL.getCurrentPosition());
                telemetry.addData("MotorBR", driveTrain.MotorBR.getCurrentPosition());
                telemetry.update();

                driver.test(telemetry);
            }
        }
}