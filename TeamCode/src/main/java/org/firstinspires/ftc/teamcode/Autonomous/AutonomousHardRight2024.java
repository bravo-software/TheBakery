package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousHardRight2024", group="Autonomous")
public class AutonomousHardRight2024 extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
//        driver = new Driver(hardwareMap);
//        scorer = new Scorer(hardwareMap);
//        waitForStart();
//        scorer.load();
//        driver.forwardSetDistance(tile_length * 2.3);
//        driver.turn_park_90_intervalsClockwise(90);
//        driver.forwardSetDistance(tile_length * 4);
//        driver.turn_park_90_intervalsClockwise(90);
//        driver.forwardSetDistance(tile_length * 1);
//        driver.turn_park_90_intervalsCounterClockwise(90);
//        scorer.score(driver);

        driver = new Driver(hardwareMap);
        scorer = new Scorer(hardwareMap);
        waitForStart();
        scorer.load();
        scorer.wait(1000);
        scorer.score(driver);
//        scorer.wait(1000);
//        scorer.pixelCollector.setWristHigh();
//        scorer.wait(1000);
//        scorer.slides.extend();
//        scorer.wait(1000);
//        driver.forward(tile_length * 3);
        scorer.wait(100000000);

    }
}