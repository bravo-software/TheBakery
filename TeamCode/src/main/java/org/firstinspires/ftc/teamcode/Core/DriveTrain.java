package org.firstinspires.ftc.teamcode.Core;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DriveTrain
{
    public DcMotor MotorFL, MotorBL;
    public DcMotor MotorFR, MotorBR;


    // front left motor is broken for some reason subject to change
    private final int MOTOR_FL_MODIFIER = -1;
    private final int MOTOR_BL_MODIFIER =  1;
    private final int MOTOR_FR_MODIFIER = -1;
    private final int MOTOR_BR_MODIFIER = -1;

    public DriveTrain(HardwareMap map, String FL,
                      String BL,
                      String FR,
                      String BR)
    {
        MotorFL = map.get(DcMotor.class, FL);
        MotorBL = map.get(DcMotor.class, BL);
        MotorFR = map.get(DcMotor.class, FR);
        MotorBR = map.get(DcMotor.class, BR);
    }

    public void Drive(Gamepad gamepad)
    {
        if (gamepad.right_stick_x != 0)
        {
            Turn(gamepad, gamepad.right_stick_x);
        }

        if (gamepad.left_stick_y != 0)
        {
            Forward(gamepad, gamepad.left_stick_y);
        }
        else
        {
            Forward(gamepad, 0);
        }

        if (gamepad.left_stick_x != 0)
        {
            Strafe(gamepad, gamepad.left_stick_x);
        }

        else
        {
            Strafe(gamepad, 0);
        }

    }
    public void init()
    {
        setDirection(DcMotor.Direction.REVERSE);
        Stop();
//        initEncoders();
    }

    public void Stop()
    {
        setPower(0);
    }

    private void Strafe(Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad,0.2, 0.6, 1);
        DirectStrafe(speed * speedMod);
    }

    //Front is 223rpm, Back is 312rpm
    private void Forward(Gamepad gamepad, float speed)
    {
        //get speed modifier
        double speedMod = calculateSpeedModifier(gamepad,0.2, 0.6, 1);
        DirectFoward(speed * speedMod);
    }
    private void Turn (Gamepad gamepad, float speed)
    {
        double speedMod = calculateSpeedModifier(gamepad,0.3, 0.6, 1);
        DirectTurn(speed * speedMod);
    }

    private double calculateSpeedModifier(@NonNull Gamepad gamepad, double slow, double normal, double fast)
    {
        double speedModifier = normal;
        if(gamepad.a)
        {
            speedModifier = fast;
        }
        else if(gamepad.b)
        {
            speedModifier = slow;
        }
        else
        {
            speedModifier = normal;
        }
        return speedModifier;
    }



    private void setDirection(DcMotor.Direction direction)
    {
        MotorFL.setDirection(direction);
        MotorBL.setDirection(direction);
        MotorFR.setDirection(direction);
        MotorBR.setDirection(direction);
    }
    private void initEncoders()
    {
       setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void DirectFoward(double speed)
    {
        System.out.println("Driving");
        setPower(speed);
    }
    private void DirectTurn(double speed)
    {
        System.out.println("turning");
        setPowerFL(-speed);
        setPowerBL(-speed);
        setPowerFR(speed);
        setPowerBR(speed);
    }
    private void DirectStrafe(double speed)
    {
        System.out.println("strafing");
        setPowerFL(-speed);
        setPowerBL(speed);
        setPowerFR(speed);
        setPowerBR(-speed);
    }
    private void setPower(double power)
    {
        MotorFL.setPower(power * MOTOR_FL_MODIFIER);
        MotorBL.setPower(power * MOTOR_BL_MODIFIER);
        MotorFR.setPower(power * MOTOR_FR_MODIFIER);
        MotorBR.setPower(power * MOTOR_BR_MODIFIER);
    }
    private void setPowerFL(double power)
    {
        MotorFL.setPower(power * MOTOR_FL_MODIFIER);
    }
    private void setPowerBL(double power)
    {
        MotorBL.setPower(power * MOTOR_BL_MODIFIER);
    }
    private void setPowerFR(double power)
    {
        MotorFR.setPower(power * MOTOR_FR_MODIFIER);
    }
    private void setPowerBR(double power)
    {
        MotorBR.setPower(power * MOTOR_BR_MODIFIER);
    }
    private void setMode(DcMotor.RunMode mode)
    {
        MotorFL.setMode(mode);
        MotorBL.setMode(mode);
        MotorFR.setMode(mode);
        MotorBR.setMode(mode);
    }

}

