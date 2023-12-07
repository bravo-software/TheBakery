package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlides
{
    private DcMotor motor;
    private int targetPosition;
    private boolean isTop;

    public LinearSlides(HardwareMap map, String name, int targetPosition)
    {
        motor = map.get(DcMotor.class, name);
        this.targetPosition = targetPosition;
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        isTop = false;
    }

    public void extend()
    {
        motor.setTargetPosition(targetPosition);
        motor.setPower(0.5);
        isTop = true;
    }
    public void reset()
    {
        motor.setTargetPosition(0);
        motor.setPower(0.5);
        isTop = false;
    }

    public boolean isTop()
    {
        return isTop;
    }

    public int getPosition()
    {
        return motor.getCurrentPosition();
    }
}
