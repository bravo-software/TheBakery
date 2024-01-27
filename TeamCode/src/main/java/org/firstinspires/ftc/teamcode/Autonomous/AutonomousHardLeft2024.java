package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousHardLeft2024", group="Autonomous")
public class AutonomousHardLeft2024 extends LinearOpMode
{
    Driver driver;
    Scorer scorer;
    int tile_length = 24; //in inches
    public void runOpMode()
    {
        driver = new Driver(hardwareMap);
        scorer = new Scorer(hardwareMap);
        waitForStart();
//        scorer.load();
        driver.forwardSetDistance(tile_length * 1.7);
        scorer.pixelCollector.setWristHigh();
        driver.backwardsSetDistance(tile_length * 0.2);//        driver.turn_park_90_intervalsClockwise(90);
//        driver.fowardSetDistance(tile_length * 1);
//        driver.turn_park_90_intervalsCounterClockwise(90);
//        driver.fowardSetDistance(tile_length * 0.50);
//        scorer.score(driver);
    }
}