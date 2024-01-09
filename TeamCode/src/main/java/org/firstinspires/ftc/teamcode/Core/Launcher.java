package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher
{
    private Servo launcher;

    private double launcherLow = 1;

    private double launcherHigh = 0.5;

    private State launcherPosition = State.INACTIVE;

    private ServoToggle launcherToggle;

    public Launcher(HardwareMap map, String launcherName)
    {
        launcher = map.get(Servo.class, launcherName);
        launcherToggle = new ServoToggle(this::toggleLauncherPosition);
    }
    public void toggleLauncherPosition()
     {
        if (launcherPosition == State.INACTIVE)
            closeLauncher();
        else
            openLauncher();
    }
    public void openLauncher()
    {
        launcherPosition = State.INACTIVE;
        launcher.setPosition(launcherHigh);
    }
    public void closeLauncher()
    {
        launcherPosition = State.ACTIVE;
        launcher.setPosition(launcherLow);
    }
    public void update(boolean buttonState)
    {
        launcherToggle.update(buttonState);
    }
}
