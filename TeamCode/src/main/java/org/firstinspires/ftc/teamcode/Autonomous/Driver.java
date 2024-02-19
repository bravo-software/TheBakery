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
        super.MotorFR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorBR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorBL.setDirection(DcMotor.Direction.REVERSE);
        super.MotorFL.setDirection(DcMotor.Direction.REVERSE);
    }

    private void resetEncoders()
    {
        super.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void forward_ticks(int ticks, double power)
    {
        resetEncoders();
        super.setTargetPosition(ticks);
        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        super.MotorFR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorBR.setDirection(DcMotor.Direction.REVERSE);
        super.MotorFL.setDirection(DcMotor.Direction.REVERSE);
        super.MotorBL.setDirection(DcMotor.Direction.REVERSE);

        super.setPower(power);
    }

    public void forward_distance(double distance, double power)
    {
        int ticks = (int) (distance * encoderResolution / distance_per_motor_rotation);
        forward_ticks(ticks, power);
    }

    public void turn_ticks(int ticks, double power)
    {
        super.setTargetPosition(ticks);
        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        super.MotorFR.setDirection(DcMotor.Direction.FORWARD);
        super.MotorBR.setDirection(DcMotor.Direction.FORWARD);

        super.MotorFL.setDirection(DcMotor.Direction.REVERSE);
        super.MotorBL.setDirection(DcMotor.Direction.REVERSE);

        super.setPower(power);
    }

    public void test()
    {
//        super.setTargetPosition(1000);
//        super.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        super.DirectForward(0.5);
//        forward_distance(609.6, 0.5);
        turn_ticks(923, 0.5);

    }

}