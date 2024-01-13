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
        waitForStart();
        driver.turn_park_90_intervals(360);

<<<<<<< HEAD
        driver.forward(510);
=======
>>>>>>> 1e310eba4fa30c91c3ee56f82619d76904484f92

    }
}