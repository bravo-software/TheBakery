package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousEasyBlue2024", group="Autonomous")
public class AutonomousEasyBlue2024 extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
        scorer = new Scorer(hardwareMap);
        scorer.load();

        waitForStart();
//        driver.forward_tiles(1.5);
//        driver.turn_90_clockwise(1);
//        driver.forward_tiles(1);
//        driver.turn_90_counter_clockwise(1);
//        scorer.score(driver);
    }
}