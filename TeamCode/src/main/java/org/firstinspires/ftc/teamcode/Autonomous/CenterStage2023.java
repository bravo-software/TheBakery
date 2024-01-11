package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousCenter2023", group="Autonomous")
public class CenterStage2023 extends LinearOpMode
{
    Driver driver;
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);

        driver.foward(510);

    }
}