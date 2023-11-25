package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;



public class WheelMotors
{
    private DcMotor MotorFL, MotorBL;
    private DcMotor MotorFR, MotorBR;

    // front left motor is broken for some reason subject to change
    private final int MOTOR_FL_MODIFIER = -1;
    private final int MOTOR_BL_MODIFIER =  1;
    private final int MOTOR_FR_MODIFIER =  1;
    private final int MOTOR_BR_MODIFIER =  1;

    public WheelMotors(DcMotor FL,
                       DcMotor BL,
                       DcMotor FR,
                       DcMotor BR)
    {
        MotorFL = FL;
        MotorBL = BL;
        MotorFR = FR;
        MotorBR = BR;
    }

    public void setPower(double power)
    {
        MotorFL.setPower(power * MOTOR_FL_MODIFIER);
        MotorBL.setPower(power * MOTOR_BL_MODIFIER);
        MotorFR.setPower(power * MOTOR_FR_MODIFIER);
        MotorBR.setPower(power * MOTOR_BR_MODIFIER);
    }
    public void setDirection(DcMotor.Direction direction)
    {
        MotorFL.setDirection(direction);
        MotorBL.setDirection(direction);
        MotorFR.setDirection(direction);
        MotorBR.setDirection(direction);
    }
    public void initEncoders()
    {
       setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void Drive(double speed)
    {
        setPower(speed);
    }
    public void Stop()
    {
        setPower(0);
    }
    public void Turn(double speed)
    {
        setPowerFL(-speed);
        setPowerBL(-speed);
        setPowerFR(speed);
        setPowerBR(speed);
    }
    public void Strafe(double speed)
    {
        setPowerFL(-speed);
        setPowerBL(speed);
        setPowerFR(speed);
        setPowerBR(-speed);
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

