package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="ParkingAutonomousEasyRed2024", group="Autonomous")
public class ParkingAutonomousEasyRed2024 extends LinearOpMode
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
//        driver.parkEasyRed();
        driver.forward_tiles(2
        );
    }
}