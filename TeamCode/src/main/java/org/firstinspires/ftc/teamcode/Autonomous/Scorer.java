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
        pixelCollector.openClaw();
        pixelCollector.setWristLow();

        driver.fowardSetDistance(-0.5);
    }

    public void load() {
        pixelCollector.closeClaw();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pixelCollector.setWristHigh();
    }

}
