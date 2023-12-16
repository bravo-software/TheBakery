package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HookMechanism {

    DcMotor motor;
    Servo hook;
    ServoToggle hookToggle;
    ServoToggle motorToggle;

    boolean hookState;
    boolean motorState;

    final int targetPos = 10;
    final double hookPos = 0.7;

    public HookMechanism(HardwareMap map, String motorName, String hookName)
    {
        hookState = false;
        motorState = false;
        motor = map.get(DcMotor.class, motorName);
        hook = map.get(Servo.class, hookName);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(targetPos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorToggle = new ServoToggle(this::toggleMotor);
        hookToggle = new ServoToggle(this::toggleHook);

    }

    public void toggleMotor()
    {
        if(motorState)
        {
            motor.setTargetPosition(0);
            motor.setPower(0.5);
        }
        else
        {
            motor.setTargetPosition(targetPos);
            motor.setPower(0.5);
        }
    }

    public void toggleHook()
    {
        if(hookState)
        {
            hook.setPosition(0);
        }
        else
        {
            hook.setPosition(hookPos);
        }
    }

    public void update(Gamepad pad)
    {
        motorToggle.update(pad.y);
        hookToggle.update(pad.x);

    }



}
