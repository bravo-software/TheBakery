package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Test2024AUTO", group="Autonomous")
public class Test2024AUTO extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches

    public void runOpMode()
    {
        waitForStart();
        while(opModeIsActive())
        {
            driver.foward_tiles(1);
            sleep(1000);
            driver.foward_tiles(-1);
            sleep(1000);
            driver.foward_distance(24);
            sleep(1000);
            driver.foward_distance(-24);
            sleep(1000);
        }
    }
}
