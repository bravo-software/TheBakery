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
        scorer.pixelCollector.setWristMid();
        scorer.pixelCollector.openClaw();
        waitForStart();
        scorer.load();
//        scorer.wait(1000);
//        driver.turn_park_90_intervalsClockwise(90);
//        driver.fowardSetDistance(tile_length);
//        scorer.wait(500);
//        driver.turn_park_90_intervalsCounterClockwise(90);
//        scorer.wait(1000);
//        driver.fowardSetDistance(tile_length);
//        scorer.wait(1000);
//        driver.turn_park_90_intervalsClockwise(90);
//        scorer.wait(1000);
//        driver.fowardSetDistance(tile_length * 1);
        scorer.score(driver);
    }
}