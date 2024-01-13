package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousHard2024", group="Autonomous")
public class AutonomousHard2024 extends LinearOpMode
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
        driver.fowardSetDistance(tile_length * 3);
        driver.turn_park_90_intervalsCounterClockwise(90);
        driver.fowardSetDistance(tile_length * 4);
        driver.turn_park_90_intervalsCounterClockwise(90);
        driver.fowardSetDistance(tile_length * 1);
        driver.turn_park_90_intervalsClockwise(90);
        driver.fowardSetDistance(tile_length * 0.95);
        scorer.score(driver);
    }
}