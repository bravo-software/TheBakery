package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HookMechanism
{

    DcMotor linearActuator;
    Servo hook;
    ServoToggle hookToggle;
    ServoToggle linearActuatorToggle;

    State hookState;
    State linearActuatorState;

    final int targetPos = 10;
    final double hookPos = 0.7;

    public HookMechanism(HardwareMap map, String motorName, String hookName)
    {

        linearActuator = map.get(DcMotor.class, motorName);


        linearActuator.setDirection(DcMotor.Direction.FORWARD);


        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearActuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearActuator.setTargetPosition(targetPos);
        linearActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linearActuatorToggle = new ServoToggle(this::toggleMotor);
        linearActuatorState = State.INACTIVE;

        hookState = State.INACTIVE;
        hook = map.get(Servo.class, hookName);
        hookToggle = new ServoToggle(this::toggleHook);

    }

    public void toggleMotor()
    {
        if(linearActuatorState == State.ACTIVE)
        {
            linearActuator.setTargetPosition(0);
            linearActuator.setPower(0.5);
        }
        else
        {
            linearActuator.setTargetPosition(-targetPos);
            linearActuator.setPower(0.5);
        }
    }

    public void toggleHook()
    {
        if(hookState == State.ACTIVE)
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
        linearActuatorToggle.update(pad.y);
        hookToggle.update(pad.x);

    }



}