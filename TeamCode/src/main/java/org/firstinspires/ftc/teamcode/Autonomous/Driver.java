package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

public class Driver
{
   DriveTrain driveTrain;
   double tickPerRotation = 537.6;
   double diameter_mm = 96;
    public Driver(HardwareMap map)
    {
        driveTrain = new DriveTrain(map, "fL", "bL", "fR", "bR");
    }
    public void init()
    {
        driveTrain.init();
    }

    public void foward(double distance)
    {
        double rotations = distance / (Math.PI * diameter_mm);
        int ticks = (int)(rotations*tickPerRotation);
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setTargetPosition(ticks);
        driveTrain.setPower(0.5);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(driveTrain.isBusy()) {}
        driveTrain.Stop();
    }

    // Add move by ticks to provide options for differnt calls
    public void foward_ticks(int ticks)
    {
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setTargetPosition(ticks);
        driveTrain.setPower(0.5);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(driveTrain.isBusy()) {}
        driveTrain.Stop();
    }

    // Add move Continously to provide option to run until sensor input used to stop
    public void foward_cont()
    {
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setPower(0.5);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(driveTrain.isBusy()) {}
    }

    public void turn(double angle)
    {

    }
}
