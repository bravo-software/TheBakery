package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Centerstage.Intake;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;


@Autonomous(name="AutonomousHardBlue2024", group="Autonomous")
public class AutonomousHardBlue2024 extends LinearOpMode
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
        driver.forward_tiles(2); // maybe increase the value a little bit
        driver.turn_90_counter_clockwise(1);
        driver.forward_tiles(3.6);
        driver.turn_90_counter_clockwise(1);
        driver.forward_tiles(1);
        driver.turn_90_clockwise(1);
        scorer.score(driver);
        driver.turn_90_counter_clockwise(1);
        driver.forward_tiles(1);
        driver.turn_90_clockwise(1);
        driver.forward_tiles(0.15);

    }
}