package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlides
{
    private DcMotor motor;
    private int topPosition;
    private boolean isTop;

    public LinearSlides(HardwareMap map, String name, int topPosition)
    {
        motor = map.get(DcMotor.class, name);
        this.topPosition = topPosition;
        motor.setTargetPosition(topPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        isTop = false;
    }

    public void toggle()
    {
        if (isTop)
        {
            reset();
        }
        else
        {
            extend();
        }
    }


    public void extend()
    {
        motor.setTargetPosition(topPosition);
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
