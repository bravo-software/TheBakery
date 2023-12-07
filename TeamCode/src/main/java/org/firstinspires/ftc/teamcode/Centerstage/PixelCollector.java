package org.firstinspires.ftc.teamcode.Centerstage;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PixelCollector
{
    private enum State
    {
        ACTIVE, //OPEN, RUNNING, UP
        INACTIVE //CLOSED, STOPPED, DOWN
    }

    private CRServo intake;
    private State intakeState = State.INACTIVE;


    private Servo wrist;
    private double wristLow = 0;
    private double wristHigh = 0.25;
    private State wristPosition = State.ACTIVE;


    private Servo trapdoor;
    private double trapdoorOpenPosition  = 0.25;
    private double trapdoorClosePosition = 0;
    private State trapdoorState = State.INACTIVE;

    public PixelCollector(@NonNull HardwareMap map, String intakeName, String wristName, String trapdoorName)
    {
        intake = map.get(CRServo.class, intakeName);
        wrist  = map.get(Servo.class, wristName);
        trapdoor = map.get(Servo.class, trapdoorName);
    }


    public void toggleTrapdoorPosition()
    {
        if (trapdoorState == State.ACTIVE)
        {
            closeTrapdoor();
        }
        else
        {
            openTrapdoor();
        }
    }
    public void toggleIntake()
    {
        if (intakeState == State.ACTIVE)
        {
            stopIntake();
        }
        else
        {
            runIntake();
        }

    }
    public void toggleWristPosition()
    {
        if (wristPosition == State.ACTIVE)
        {
            setWristLow();
        }
        else
        {
            setWristHigh();
        }
    }
    private void openTrapdoor()
    {
        trapdoorState = State.ACTIVE;
        trapdoor.setPosition(trapdoorOpenPosition);
    }
    private void closeTrapdoor()
    {
        trapdoorState = State.INACTIVE;
        trapdoor.setPosition(trapdoorClosePosition);
    }

    private void runIntake()
    {
        intakeState = State.ACTIVE;
        intake.setPower(1.0);
    }
    private void stopIntake()
    {
        intakeState = State.INACTIVE;
        intake.setPower(0);
    }

    private void setWristLow()
    {
        wristPosition = State.INACTIVE;
        wrist.setPosition(wristLow);
    }
    private void setWristHigh()
    {
        wristPosition = State.ACTIVE;
        wrist.setPosition(wristHigh);
    }


}
