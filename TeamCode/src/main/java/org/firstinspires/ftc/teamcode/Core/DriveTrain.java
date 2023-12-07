package org.firstinspires.ftc.teamcode.Core;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain
{
    public DcMotor MotorFL, MotorBL;
    public DcMotor MotorFR, MotorBR;

    /**
     * Motor power modifiers to ensure consistent directional movement across all wheels.
     * <p>
     * These modifiers are used to adjust the direction of rotation for each motor, as
     * sometimes wheels may move in opposite directions with the same power setting.
     * <p>
     * These modifiers ensure that all wheels move in the intended direction.
     */
    private final int MOTOR_FL_MODIFIER = 1, MOTOR_BL_MODIFIER = -1, MOTOR_FR_MODIFIER = 1, MOTOR_BR_MODIFIER = 1;

    /**
     * Constructor for DriveTrain.
     * Initializes the motors used in the drive train.
     *
     * @param map HardwareMap to get hardware configurations.
     * @param FL  Front Left motor name.
     * @param BL  Back Left motor name.
     * @param FR  Front Right motor name.
     * @param BR  Back Right motor name.
     */
    public DriveTrain(HardwareMap map, String FL, String BL, String FR, String BR)
    {
        MotorFL = map.get(DcMotor.class, FL);
        MotorBL = map.get(DcMotor.class, BL);
        MotorFR = map.get(DcMotor.class, FR);
        MotorBR = map.get(DcMotor.class, BR);
    }

    /**
     * Drives the robot based on Gamepad input.
     * It controls the robot's turn, forward, and strafe movements.
     *
     * @param gamepad The gamepad input from the user.
     */
    public void Drive(Gamepad gamepad)
    {
        Turn(gamepad, gamepad.right_stick_x);
        Forward(gamepad, gamepad.left_stick_y);
        Strafe(gamepad, gamepad.left_stick_x);
    }

    /**
     * Initializes the robot's drive system.
     * Sets the motor direction and stops all motors.
     */
    public void init()
    {
        setDirection(DcMotor.Direction.FORWARD);
        Stop();
        // initEncoders();
    }

    /**
     * Stops all motors in the drive train.
     */
    public void Stop()
    {
        setPower(0);
    }

    /**
     * Controls the strafing movement of the robot with speed modifiers.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param speed   The speed at which to strafe.
     */
    private void Strafe(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.2, 0.6, 1);
        DirectStrafe(speed * speedMod);
    }

    /**
     * Controls the forward/backward movement of the robot with speed modifiers.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param speed   The speed at which to move forward/backward.
     */
    private void Forward(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.2, 0.6, 1);
        DirectFoward(speed * speedMod);
    }

    /**
     * Controls the turning movement of the robot with speed modifiers.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param speed   The speed at which to turn.
     */
    private void Turn(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad, 0.3, 0.6, 1);
        DirectTurn(speed * speedMod);
    }

    /**
     * Calculates the speed modifier based on gamepad button inputs.
     *
     * @param gamepad The gamepad controlling movement for the robot.
     * @param slow    Slow speed modifier.
     * @param normal  Normal speed modifier.
     * @param fast    Fast speed modifier.
     * @return The calculated speed modifier.
     */
    private double calculateSpeedModifier(@NonNull Gamepad gamepad, double slow, double normal, double fast)
    {
        double speedModifier = normal;
        if (gamepad.a)
        {
            speedModifier = fast;
        }
        else if (gamepad.b)
        {
            speedModifier = slow;
        }
        else
        {
            speedModifier = normal;
        }
        return speedModifier;
    }

    /**
     * Sets the direction of all motors in the drive train.
     *
     * @param direction The direction to set the motors.
     */
    private void setDirection(DcMotor.Direction direction)
    {
        MotorFL.setDirection(direction);
        MotorBL.setDirection(direction);
        MotorFR.setDirection(direction);
        MotorBR.setDirection(direction);
    }

    /**
     * Initializes the encoders for the motors.
     */
    private void initEncoders()
    {
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Directly controls the forward movement of the robot without speed modifiers.
     *
     * @param speed The speed at which to move forward.
     */
    private void DirectFoward(double speed)
    {
        System.out.println("Driving");
        setPower(speed);
    }

    /**
     * Directly controls the turning movement of the robot without speed modifiers.
     *
     * @param speed The speed at which to turn.
     */
    private void DirectTurn(double speed)
    {
        System.out.println("Turning");
        setPowerFL(-speed);
        setPowerBL(-speed);
        setPowerFR(speed);
        setPowerBR(speed);
    }

    /**
     * Directly controls the strafing movement of the robot without speed modifiers.
     *
     * @param speed The speed at which to strafe.
     */
    private void DirectStrafe(double speed)
    {
        System.out.println("Strafing");
        setPowerFL(-speed);
        setPowerBL(speed);
        setPowerFR(speed);
        setPowerBR(-speed);
    }

    /**
     * Sets the power for all motors in the drive train.
     *
     * @param power The power level to set the motors.
     */
    private void setPower(double power)
    {
        MotorFL.setPower(power * MOTOR_FL_MODIFIER);
        MotorBL.setPower(power * MOTOR_BL_MODIFIER);
        MotorFR.setPower(power * MOTOR_FR_MODIFIER);
        MotorBR.setPower(power * MOTOR_BR_MODIFIER);
    }

    /**
     * Sets the power for the Front Left motor.
     *
     * @param power The power level for the Front Left motor.
     */
    private void setPowerFL(double power)
    {
        MotorFL.setPower(power * MOTOR_FL_MODIFIER);
    }

    /**
     * Sets the power for the Back Left motor.
     *
     * @param power The power level for the Back Left motor.
     */
    private void setPowerBL(double power)
    {
        MotorBL.setPower(power * MOTOR_BL_MODIFIER);
    }

    /**
     * Sets the power for the Front Right motor.
     *
     * @param power The power level for the Front Right motor.
     */
    private void setPowerFR(double power)
    {
        MotorFR.setPower(power * MOTOR_FR_MODIFIER);
    }

    /**
     * Sets the power for the Back Right motor.
     *
     * @param power The power level for the Back Right motor.
     */
    private void setPowerBR(double power)
    {
        MotorBR.setPower(power * MOTOR_BR_MODIFIER);
    }

    /**
     * Sets the mode for all motors in the drive train.
     *
     * @param mode The mode to set the motors.
     */
    private void setMode(DcMotor.RunMode mode)
    {
        MotorFL.setMode(mode);
        MotorBL.setMode(mode);
        MotorFR.setMode(mode);
        MotorBR.setMode(mode);
    }
}
