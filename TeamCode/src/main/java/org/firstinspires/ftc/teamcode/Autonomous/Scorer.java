package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Centerstage.PixelCollector;
import org.firstinspires.ftc.teamcode.Core.LinearSlides;

public class Scorer
{
    private LinearSlides slides;
    public PixelCollector pixelCollector;


    public Scorer(HardwareMap map)
    {
        this.slides = new LinearSlides(map, "Slides", 1540);
        this.pixelCollector = new PixelCollector(map, "wrist", "claw");
    }

    public void score(Driver driver)
    {
        slides.extend();
        wait(1500);
        pixelCollector.openClaw();
        wait(1500);
        pixelCollector.setWristLow();
        wait(1500);

        driver.backwardsSetDistance(0.5);
    }

    public void load() {
        pixelCollector.setWristLow();
        wait(1000);
        pixelCollector.closeClaw();
        wait(1000);
        pixelCollector.xd();
    }

    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }

}
