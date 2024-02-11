package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.hardware.DcMotor;
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
    private final double distance_per_motor_rotation = wheel_diameter * Math.PI;

    private final double wheel_rotations_per_90_turn = 0.5; //guess
    public Driver(HardwareMap map)
    {
        super(map, "fL", "bL", "fR", "bR");
        super.initEncoders();
    }

    private void resetEncoders()
    {
        super.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    private void selectRunToPositionMode()
    {
        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    //! untested
    private void forwardTicks(int ticks)
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

    public void forwardDistance(int distance)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            super.encoders_initialized = true;
        }
        double rotations = (distance / distance_per_motor_rotation);
        int ticks = (int) (rotations * encoderResolution);
        super.setTargetPosition(ticks);
        super.setPower(0.5);
    }

    public void forwardTiles(double tiles)
    {
        if(!super.encoders_initialized)
        {
            super.initEncoders();
            super.encoders_initialized = true;
        }
        double distance = tiles * tileLength * 0.0393701;
        double rotations = (distance / distance_per_motor_rotation);
        int ticks = (int) (rotations * encoderResolution);
        super.setTargetPosition(ticks);
        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.setPower(0.5);
    }

    private void turnTicks(int ticks)
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