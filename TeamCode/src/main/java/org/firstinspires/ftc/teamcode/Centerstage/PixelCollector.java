package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Core.ServoToggle;

/** Manages the PixelCollector mechanism in an FTC robot, controlling intake, wrist, and trapdoor. */
public class PixelCollector
{

    /** CRServo for intake mechanism. */
    public CRServo intake;

    /** Servo for controlling the wrist. */
    private Servo wrist;

    /** Low position value for wrist servo. */
    private double wristLow = 0.2;

    /** High position value for wrist servo. */
    private double wristHigh = 0.6;

    /** Servo for trapdoor mechanism. */
    private Servo trapdoor;

    /** Open position value for trapdoor servo. */
    private double trapdoorOpenPosition = 0.67;

    /** Close position value for trapdoor servo. */
    private double trapdoorClosePosition = 0.31;

    private ServoToggle trapdoorToggle;
    private ServoToggle wristToggle;
    private ServoToggle intakeToggle;

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
        trapdoor.setDirection(Servo.Direction.REVERSE);

        trapdoorToggle = new ServoToggle(this::openTrapdoor, this::closeTrapdoor, false);
        wristToggle = new ServoToggle(this::setWristHigh, this::setWristLow, false);
//        intakeToggle = new ServoToggle(this::runIntake, this::stopIntake, false);
    }

    public void updateServos(boolean trapdoorButton, boolean wristButton, boolean intakeButton)
    {
        trapdoorToggle.update(trapdoorButton);
        wristToggle.update(wristButton);

        if (intakeButton)
           runIntake();
        else
            stopIntake();
    }


    /** Sets trapdoor to open position. */
    public void openTrapdoor()
    {
        trapdoor.setPosition(trapdoorOpenPosition);
    }

    /** Sets trapdoor to closed position. */
    public void closeTrapdoor()
    {
        trapdoor.setPosition(trapdoorClosePosition);
    }

    /** Activates the intake mechanism. */
    public void runIntake()
    {
        intake.setPower(1.0);
    }

    /** Stops the intake mechanism. */
    private void stopIntake()
    {
        intake.setPower(0);
    }

    /** Sets wrist to its low position. */
    private void setWristLow()
    {
        wrist.setPosition(wristLow);
    }

    /** Sets wrist to its high position. */
    private void setWristHigh()
    {
        wrist.setPosition(wristHigh);
    }

}
