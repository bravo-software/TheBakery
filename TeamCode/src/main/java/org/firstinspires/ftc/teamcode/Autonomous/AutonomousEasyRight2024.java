//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//@Autonomous(name="AutonomousEasyRight2024", group="Autonomous")
//public class AutonomousEasyRight2024 extends LinearOpMode
//{
//    Driver driver;
//    Scorer scorer;
//    int tile_length = 24; //in inches
//    public void runOpMode()
//    {
//        driver = new Driver(hardwareMap);
//        scorer = new Scorer(hardwareMap);
//
//        waitForStart();
//        scorer.load();
//        scorer.wait(1000);
//        driver.forwardSetDistance(tile_length * 2);
//        driver.turn_park_90_intervalsCounterClockwise(90);
//        driver.forwardSetDistance(tile_length * 1);
//        driver.turn_park_90_intervalsClockwise(90);
//        scorer.score(driver);
//
//    }
//}