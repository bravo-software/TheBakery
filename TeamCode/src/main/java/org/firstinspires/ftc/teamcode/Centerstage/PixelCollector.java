package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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

    /** CRServo for intake mechanism. */
    private CRServo intake;

    /** Current state of intake (ACTIVE = running, INACTIVE = stopped). */
    private State intakeState = State.INACTIVE;

    /** Servo for controlling the wrist. */
    private Servo wrist;

    /** Low position value for wrist servo. */
    private double wristLow = 0;

    /** High position value for wrist servo. */
    private double wristHigh = 0.25;

    /** Current wrist position (ACTIVE = high, INACTIVE = low). */
    private State wristPosition = State.ACTIVE;

    /** Servo for trapdoor mechanism. */
    private Servo trapdoor;

    /** Open position value for trapdoor servo. */
    private double trapdoorOpenPosition = 0.25;

    /** Close position value for trapdoor servo. */
    private double trapdoorClosePosition = 0;

    /** Current state of trapdoor (ACTIVE = open, INACTIVE = closed). */
    private State trapdoorState = State.INACTIVE;

    /**
     * Initializes intake, wrist, and trapdoor components.
     *
     * @param map           Hardware map for robot configurations.
     * @param intakeName    Name of intake CRServo.
     * @param wristName     Name of wrist Servo.
     * @param trapdoorName  Name of trapdoor Servo.
     */
    public PixelCollector(@NonNull HardwareMap map, String intakeName, String wristName, String trapdoorName)
    {
        intake = map.get(CRServo.class, intakeName);
        wrist = map.get(Servo.class, wristName);
        trapdoor = map.get(Servo.class, trapdoorName);
    }

    /** Toggles trapdoor between open and closed positions. */
    public void toggleTrapdoorPosition() {
        if (trapdoorState == State.ACTIVE)
            closeTrapdoor();
         else
            openTrapdoor();

    }

    /** Toggles intake between running and stopped states. */
    public void toggleIntake() {
        if (intakeState == State.ACTIVE)
            stopIntake();
        else
            runIntake();
    }

    /** Toggles wrist position between high and low. */
    public void toggleWristPosition()
    {
        if (wristPosition == State.ACTIVE)
            setWristLow();
        else
            setWristHigh();
    }

    /** Sets trapdoor to open position. */
    private void openTrapdoor()
    {
        trapdoorState = State.ACTIVE;
        trapdoor.setPosition(trapdoorOpenPosition);
    }

    /** Sets trapdoor to closed position. */
    private void closeTrapdoor()
    {
        trapdoorState = State.INACTIVE;
        trapdoor.setPosition(trapdoorClosePosition);
    }

    /** Activates the intake mechanism. */
    private void runIntake()
    {
        intakeState = State.ACTIVE;
        intake.setPower(1.0);
    }

    /** Stops the intake mechanism. */
    private void stopIntake()
    {
        intakeState = State.INACTIVE;
        intake.setPower(0);
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
}
