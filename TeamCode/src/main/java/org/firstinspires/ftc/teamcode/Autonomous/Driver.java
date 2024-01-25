package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

public class Driver
{
    DriveTrain driveTrain;
    int timeFor90Degrees = 290;
    double inchesPerSecond = 26.5;
    int tileLength = 24;

    public Driver(HardwareMap map)
    {
        driveTrain = new DriveTrain(map, "fL", "bL", "fR", "bR");
    }
    public void init()
    {
        driveTrain.init();
    }

    private int turnTimeValue180Mill = 2000;

    public void forwardSetDistance(double inches)
    {
        int time = (int) ((inches / inchesPerSecond) * 1000);
        forward(time);
    }

    public void backwardsSetDistance(double inches)
    {
        int time = (int) ((inches / inchesPerSecond) * 1000);
        backwards(time);
    }

    public void turn_park_90_intervalsClockwise(int angle)
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

    public void turn_park_90_intervalsCounterClockwise(int angle)
    {
        int intervals = (int) (angle / 90);
        for(int i = 0; i<intervals; i++)
        {
            driveTrain.DirectTurn(-1);
            try {Thread.sleep(timeFor90Degrees);} catch (InterruptedException ignored) {}
            driveTrain.Stop();
            try {Thread.sleep(250);} catch (InterruptedException ignored) {}
        }
    }

    //distance in mm
    public void forward(int time) {
        driveTrain.DirectFoward(-0.5);
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driveTrain.Stop();
    }

    public void backwards(int time)
    {
        driveTrain.DirectFoward(0.5);
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driveTrain.Stop();
    }

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