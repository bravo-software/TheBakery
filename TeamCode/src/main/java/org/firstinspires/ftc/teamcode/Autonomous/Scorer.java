package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Centerstage.Intake;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;

public class Scorer
{
    public LinearSlides slides;
    public Intake intake;


    public Scorer(HardwareMap map)
    {
        this.slides = new LinearSlides(map, "Slides", 1540);
        this.intake = new Intake(map, "wrist", "claw");
        this.intake.setWristMid();
        this.intake.openClaw();
    }

    public void score(Driver driver)
    {
        slides.extend();
        intake.setWristHigh();
        wait(1500);
        intake.openClaw();
        wait(1500);
        intake.setWristLow();
        wait(1500);
        slides.reset();

        driver.backwardsSetDistance(0.5);
    }

    public void load() {
        intake.setWristLow();
        wait(1000);
        intake.closeClaw();
        wait(1000);
        intake.xd();
    }

    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }

}
