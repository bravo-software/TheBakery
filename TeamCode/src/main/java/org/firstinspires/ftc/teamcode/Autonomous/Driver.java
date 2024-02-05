package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Core.DriveTrain;

public class Driver extends DriveTrain
{
    /**
     * The length of a tile in inches
     */
    int tileLength = 24;
    /**
     * The number of ticks per rotation of the motor
     */
    private final double encoderResolution = 537.7;

    /** Diameter of the wheels in millimeters */
    private final double wheel_diameter = 96; // mm

    /** linear distance covered by one rotation of the wheel*/
    private final double distance_per_rotation = wheel_diameter * Math.PI;
    public Driver(HardwareMap map)
    {
        super(map, "leftFront", "rightFront", "leftBack", "rightBack");
    }

    //! untested
    private void foward_ticks(int ticks)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            encoders_initialized = true;
        }
        super.setTargetPosition(ticks);
        super.setPower(0.5);
    }

    //! untested

    public void foward_distance(int distance)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            super.encoders_initialized = true;
        }
        double rotations = (distance / distance_per_rotation);
        int ticks = (int) (rotations * encoderResolution);
        super.setTargetPosition(ticks);
        super.setPower(0.5);
    }

    public void foward_tiles(double tiles)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            super.encoders_initialized = true;
        }
        double distance = tiles * tileLength * 0.0393701;
        double rotations = (distance / distance_per_rotation);
        int ticks = (int) (rotations * encoderResolution);
        super.setTargetPosition(ticks);
        super.setPower(0.5);
    }

    private void turn_ticks(int ticks)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            super.encoders_initialized = true;
        }
        super.MotorBL.setTargetPosition(ticks);
        super.MotorFL.setTargetPosition(ticks);
        super.MotorBR.setTargetPosition(-ticks);
        super.MotorFR.setTargetPosition(-ticks);
        super.setPower(0.5);
    }

    public void turn(double degrees)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            super.encoders_initialized = true;
        }
        double rotations = (degrees / 360);
        int ticks = (int) (rotations * encoderResolution);
        super.setTargetPosition(ticks);
        super.setPower(0.5);
    }

}