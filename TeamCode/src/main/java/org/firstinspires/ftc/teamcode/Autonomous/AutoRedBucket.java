package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoRedBucket", group="Autonomous")
public class AutoRedBucket extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
//        scorer = new Scorer(hardwareMap);

        waitForStart();
//        scorer.load();
        driver.parkEasyRed();
//        scorer.score(driver);
//        scorer.wait(500);
        driver.forward_tiles(0.1);
        driver.turn_90_clockwise(1);
        driver.forward_tiles(0.75);

    }
}