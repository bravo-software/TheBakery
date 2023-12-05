package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSystem
{
    private Servo trapdoor;
    private Servo wrist;
    private CRServo intake;

    public IntakeSystem(HardwareMap hardwareMap)
    {
        trapdoor = hardwareMap.get(Servo.class, "trapdoor");
        wrist = hardwareMap.get(Servo.class, "wrist");
        intake = hardwareMap.get(CRServo.class, "intake");
    }
}
