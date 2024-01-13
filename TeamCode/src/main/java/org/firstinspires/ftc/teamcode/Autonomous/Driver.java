package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

public class Driver
{
   DriveTrain driveTrain;
   int timeFor90Degrees = 270;
   double inchesPerSecond = 26.02;
    public Driver(HardwareMap map)
    {
        driveTrain = new DriveTrain(map, "fL", "bL", "fR", "bR");
    }
    public void init()
    {
        driveTrain.init();
    }

    private int turnTimeValue180Mill = 2000;


    public void forward_park()
    {
        driveTrain.DirectFoward(0.5);
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {

        }
    }
    public void turn_park_90_intervals(int angle)
    {
        int intervals = (int) (angle / 90);
        for(int i = 0; i<intervals; i++)
        {
            driveTrain.DirectTurn(1);
            try {Thread.sleep(timeFor90Degrees);} catch (InterruptedException ignored) {}
            driveTrain.Stop();
            try {Thread.sleep(250);} catch (InterruptedException ignored) {}
        }
    }

    //distance in mm
<<<<<<< HEAD
    public void forward (double distance)
    {
        double rotations = distance / (Math.PI * diameter_mm);
        int ticks = (int)(rotations*tickPerRotation);
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setTargetPosition(ticks);
        driveTrain.setPower(0.5);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while( driveTrain.isBusy() ) {}
        driveTrain.Stop();
    }

    // Add move by ticks to provide options for different calls
    public void forward_ticks(int ticks)
    {
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setTargetPosition(ticks);
        driveTrain.setPower(0.5);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(driveTrain.isBusy()) {}
        driveTrain.Stop();
    }

    // Add move Continuously to provide option to run until sensor input used to stop
    public void forward_cont()
    {
        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.setPower(0.5);
        driveTrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(driveTrain.isBusy()) {}
    }

=======
    public void foward(int time) {
        driveTrain.DirectFoward(-0.5);
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driveTrain.Stop();
    }

>>>>>>> 1e310eba4fa30c91c3ee56f82619d76904484f92
//    public void turn(double angle)
//    {
//        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        driveTrain.DirectTurn(0.5);
//        int tickPosition = turntickValue180 * (int)(angle/180);
//
//        driveTrain.MotorBL.setTargetPosition(-tickPosition);
//        driveTrain.MotorBR.setTargetPosition( tickPosition);
//        driveTrain.MotorFL.setTargetPosition(-tickPosition);
//        driveTrain.MotorFR.setTargetPosition( tickPosition);
//
//        while(driveTrain.isBusy()) {}
//    }
}
