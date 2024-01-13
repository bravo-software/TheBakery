package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousEasy2024", group="Autonomous")
public class AutonomousEasy2024 extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
        scorer = new Scorer(hardwareMap);
        waitForStart();
        scorer.load();
        driver.fowardSetDistance(tile_length);
        driver.turn_park_90_intervalsClockwise(90);
        driver.fowardSetDistance(tile_length);
        driver.turn_park_90_intervalsCounterClockwise(90);
        driver.fowardSetDistance(tile_length * 2);
        scorer.score(driver);
    }
}