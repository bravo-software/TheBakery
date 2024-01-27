package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousEasyLeft2024", group="Autonomous")
public class AutonomousEasyLeft2024 extends LinearOpMode
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
        scorer.score(driver);
        driver.forwardSetDistance(tile_length * 2);
        driver.turn_park_90_intervalsClockwise(90);
        driver.forwardSetDistance(tile_length * 3);
        scorer.score(driver);
        driver.forwardSetDistance(tile_length * 1.7);
        scorer.wait(100);
        scorer.pixelCollector.setWristHigh();
        scorer.wait(100);
        driver.backwardsSetDistance(tile_length * 0.2);
        scorer.wait(1000);
        driver.forwardSetDistance(tile_length);
        driver.turn_park_90_intervalsClockwise(90);
        driver.forwardSetDistance(tile_length);
        scorer.wait(500);
        driver.turn_park_90_intervalsCounterClockwise(90);
        scorer.wait(1000);
        driver.forwardSetDistance(tile_length);
        scorer.wait(1000);
        scorer.score(driver);
    }
}