package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousHardRed2024", group="Autonomous")
public class AutonomousHardRed2024 extends LinearOpMode
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
//        driver.forward_tiles(2);
//        driver.turn_90_clockwise(1);
//        driver.forward_tiles(3);
//        driver.turn_90_clockwise(1);
//        driver.forward_tiles(1);
//        driver.turn_90_counter_clockwise(1);
//        scorer.score(driver);


    }
}