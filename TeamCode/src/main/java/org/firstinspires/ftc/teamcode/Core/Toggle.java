package org.firstinspires.ftc.teamcode.Core;

public class Toggle
{
    private boolean state = false;
    private boolean lastState = false;

    public Toggle() {}

    public boolean shouldToggle()
    {
        return state && !lastState;
    }
}
