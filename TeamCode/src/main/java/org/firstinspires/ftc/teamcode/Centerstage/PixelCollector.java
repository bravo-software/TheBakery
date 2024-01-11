package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Core.ServoToggle;

/** Manages the PixelCollector mechanism in an FTC robot, controlling intake, wrist, and trapdoor. */
public class PixelCollector
{
    /** Represents operational states of PixelCollector components */
    private enum State
    {
        /** Indicates the component is operational (e.g., open, running, up). */
        ACTIVE,
        /** Indicates the component is not operational (e.g., closed, stopped, down). */
        INACTIVE
    }

    /** Servo for controlling the wrist. */
    private Servo wrist;

    /** High position value for wrist servo. */
    private double wristLow = 0.35;

    /** Low position value for wrist servo. */
    private double wristMid = 0.3;

    /** High position value for wrist servo. */
    private double wristHigh = 0.1;

    /** Current wrist position (ACTIVE = high, INACTIVE = low). */
    private State wristPosition = State.ACTIVE;

    /** Servo for claw mechanism. */
    private Servo claw;

    /** Open position value for claw servo. */
    private double clawOpenPosition = 0.51;

    /** Close position value for claw servo. */
    private double clawClosePosition = 0.3;

    /** Current state of claw (ACTIVE = open, INACTIVE = closed). */
    private State clawState = State.INACTIVE;

    /**
     * Initializes intake, wrist, and trapdoor components.
     *
     * @param map           Hardware map for robot configurations.
     * @param wristName     Name of wrist Servo.
     * @param clawName      Name of trapdoor Servo.
     */

    private ServoToggle clawToggle;
    private ServoToggle wristToggle;
    public PixelCollector(@NonNull HardwareMap map, String wristName, String clawName)
    {
        wrist = map.get(Servo.class, wristName);
        claw = map.get(Servo.class, clawName);

        clawToggle = new ServoToggle(this::toggleClawPosition);
        wristToggle = new ServoToggle(this::toggleWristPosition);
    }

    /** Toggles wrist position between high and low. */
    public void toggleWristPosition()
    {
        if (wristPosition == State.ACTIVE)
            setWristLow();
        else
            setWristHigh();
    }

    public void moveWristMid() {
        wrist.setPosition(0.5);
    }

    /** Toggles claw between open and closed positions. */
    public void toggleClawPosition() {
        if (clawState == State.ACTIVE)
            closeClaw();
        else
            openClaw();
    }

    /** Sets claw to open position. */
    public void openClaw()
    {
        clawState = State.ACTIVE;
        claw.setPosition(clawOpenPosition);
    }

    /** Sets claw to closed position. */
    public void closeClaw()
    {
        clawState = State.INACTIVE;
        claw.setPosition(clawClosePosition);
    }


    /** Sets wrist to its low position. */
    private void setWristLow()
    {
        wristPosition = State.INACTIVE;
        wrist.setPosition(wristLow);
    }

    /** Sets wrist to its high position. */
    private void setWristHigh()
    {
        wristPosition = State.ACTIVE;
        wrist.setPosition(wristHigh);
    }

    private void setWristMid()
    {
        wristPosition = State.ACTIVE;
        wrist.setPosition(wristMid);
    }

    private double getClawPosition() {
        return claw.getPosition();
    }

    public void updateServos(boolean clawButton, boolean wristLow , boolean wrisHigh, boolean wristMid)
    {
        if (wristLow)
            setWristLow();
        else if (wrisHigh)
            setWristHigh();
        else if (wristMid)
            setWristMid();
        clawToggle.update(clawButton);
//        wristToggle.update(wristButton);
    }
}