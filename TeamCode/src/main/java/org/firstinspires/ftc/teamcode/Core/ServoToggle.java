package org.firstinspires.ftc.teamcode.Core;

public class ServoToggle {
    private boolean lastButtonState = false;
    private boolean isActive;

    private final Runnable onActivate;
    private final Runnable onDeactivate;

    public ServoToggle(Runnable onActivate, Runnable onDeactivate, boolean initialState)
    {
        this.onActivate = onActivate;
        this.onDeactivate = onDeactivate;
        this.isActive = initialState;
    }

    public void update(boolean currentButtonState)
    {
        if (currentButtonState && !lastButtonState)
        {
            isActive = !isActive;
            if (isActive)
            {
                onActivate.run();
            }
            else
            {
                onDeactivate.run();
            }
        }
        lastButtonState = currentButtonState;
    }
}
