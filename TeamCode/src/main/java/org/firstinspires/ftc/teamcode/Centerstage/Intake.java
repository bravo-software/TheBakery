package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Core.ServoToggle;
import org.firstinspires.ftc.teamcode.Core.State;

/** Manages the PixelCollector mechanism in an FTC robot, controlling intake, wrist, and trapdoor. */
public class Intake
{
    enum WristPosition
    {
        LOW,
        MID_GOING_UP,
        MID_GOING_DOWN,
        HIGH
    }

    /** Servo for controlling the wrist. */
    private final Servo wrist;

    /** High position value for wrist servo. */
    private double wristLow = 0.44;

    /** Low position value for wrist servo. */
    private double wristMid = 0.34;

    /** High position value for wrist servo. */
    private double wristHigh = 0.12;

    /** Current wrist position (ACTIVE = high, INACTIVE = low). */
    private WristPosition wristPosition = WristPosition.MID_GOING_UP;

    /** Servo for claw mechanism. */
    private final Servo claw1;

    private final Servo claw2;

    /** Open position value for claw servo. */
    private double clawOpenPosition1 = 0.7;

    /** Close position value for claw servo. */
    private double clawClosePosition1 = 1;

    private double clawOpenPosition2 = 0.7;

    /** Close position value for claw servo. */
    private double clawClosePosition2 = 1;

    /** Current state of claw (ACTIVE = open, INACTIVE = closed). */
    private State clawState = State.INACTIVE;

    /**
     * Initializes intake, wrist, and trapdoor components.
     *
     * @param map           Hardware map for robot configurations.
     * @param wristName     Name of wrist Servo.
     * @param clawName      Name of trapdoor Servo.
     */

    private ServoToggle clawToggle1;

    private ServoToggle clawToggle2;

    private ServoToggle wristToggle;
    public Intake(@NonNull HardwareMap map, String wristName, String clawName1, String clawName2)
    {
        wrist = map.get(Servo.class, wristName);
        claw1 = map.get(Servo.class, clawName1);
        claw2 = map.get(Servo.class, clawName2);

        clawToggle1 = new ServoToggle(this::toggleClawPosition);
        clawToggle2 = new ServoToggle(this::toggleClawPosition);

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
        if (clawState == State.ACTIVE) {
            closeClaw1();
            closeClaw2();
        } else {
            openClaw1();
            openClaw2();
        }
    }

    /** Sets claw to open position. */
    public void openClaw1()
    {
        clawState = State.ACTIVE;
        claw1.setPosition(clawOpenPosition1);
    }

    /** Sets claw to closed position. */
    public void closeClaw1()
    {
        clawState = State.INACTIVE;
        claw1.setPosition(clawClosePosition1);
    }

    public void openClaw2()
    {
        clawState = State.ACTIVE;
        claw2.setPosition(clawOpenPosition2);
    }

    /** Sets claw to closed position. */
    public void closeClaw2()
    {
        clawState = State.INACTIVE;
        claw2.setPosition(clawClosePosition2);
    }


    /** Sets wrist to its low position. */
    public void setWristLow()
    {
        wrist.setPosition(wristLow);
    }

    // uhh delete this later
    public void xd() { wrist.setPosition(0.42); }

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