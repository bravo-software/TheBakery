package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Core.ServoToggle;
import org.firstinspires.ftc.teamcode.Core.State;

/** Manages the PixelCollector mechanism in an FTC robot, controlling intake, wrist, and trapdoor. */
public class PixelCollector
{
    enum WristPosition
    {
        LOW,
        MID_GOING_UP,
        MID_GOING_DOWN,
        HIGH
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
    private WristPosition wristPosition = WristPosition.MID_GOING_UP;

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
        if (wristPosition == WristPosition.LOW) {
            setWristMid();
            wristPosition = WristPosition.MID_GOING_UP;
        }
        else if(wristPosition == WristPosition.MID_GOING_UP) {
            setWristHigh();
            wristPosition = WristPosition.HIGH;
        }
        else if(wristPosition == WristPosition.HIGH) {
            setWristMid();
            wristPosition = WristPosition.MID_GOING_DOWN;
        }
        else if(wristPosition == WristPosition.MID_GOING_DOWN) {
            setWristLow();
            wristPosition = WristPosition.LOW;
        }
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
        claw.setPosition(-clawClosePosition);
    }


    /** Sets wrist to its low position. */
    public void setWristLow()
    {
        wrist.setPosition(wristLow);
    }

    /** Sets wrist to its high position. */
    public void setWristHigh()
    {
        wrist.setPosition(wristHigh);
    }

    public void setWristMid()
    {
        wrist.setPosition(wristMid);
    }

    private double getClawPosition() {
        return claw.getPosition();
    }

    public void updateServos(boolean clawButton, boolean wristButton)
    {
        wristToggle.update(wristButton);
        clawToggle.update(clawButton);
    }
}