package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Driver", group="TeleOp")
public class DriveTrain2 extends LinearOpMode {
    public DcMotor MotorFL, MotorBL;
    public DcMotor MotorFR, MotorBR;
    public DcMotor rot, intake, pulley;
    public Servo carriage;
    public boolean linearSlidesNotDown;
    public boolean linearSlidesNotUp;
        
    @Override
    public void runOpMode() {
        
        MotorFL = hardwareMap.get(DcMotor.class, "fL");
        MotorBL = hardwareMap.get(DcMotor.class, "bL");
        MotorFR = hardwareMap.get(DcMotor.class, "fR");
        MotorBR = hardwareMap.get(DcMotor.class, "bR");
        rot = hardwareMap.get(DcMotor.class, "ro");
        intake = hardwareMap.get(DcMotor.class, "in");
        pulley = hardwareMap.get(DcMotor.class, "pul");
        carriage = hardwareMap.get(Servo.class, "car");

        pulley.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //pulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //pulley.setTargetPosition(0);
        
        if (pulley.getCurrentPosition() <= -20) {
            linearSlidesNotDown = false;
            linearSlidesNotUp = true;
        }
        
        if (pulley.getCurrentPosition() >= 20) {
            linearSlidesNotDown = true;
            linearSlidesNotUp = false;
        }
        
        MotorFL.setDirection(DcMotor.Direction.REVERSE);
        MotorFR.setDirection(DcMotor.Direction.REVERSE);
        MotorBL.setDirection(DcMotor.Direction.REVERSE);
        MotorBR.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        MotorFL.setPower(0);
        MotorBL.setPower(0);
        MotorFR.setPower(0);
        MotorBR.setPower(0);
        waitForStart();

        while (opModeIsActive()) {
            
            //Linear Slides Control
            if (gamepad2.dpad_up && linearSlidesNotUp /*&& pulley.getCurrentPosition() < 100*/) {
                //pulley.setPower(-0.5);
                runSlides(-20, -0.5);
                linearSlidesNotUp = false;
                linearSlidesNotDown = true;
            } else if (gamepad2.dpad_down && linearSlidesNotDown /*&& pulley.getCurrentPosition() > findCap()*/) {
                //pulley.setPower(0.5);
                runSlides(20, 0.5);
                linearSlidesNotDown = false;
                linearSlidesNotUp = true;
            } else {
                pulley.setPower(0);
            }
            
            //Intake Controls
            if (gamepad2.right_trigger > 0) {
                intake.setPower(1.5);
            } else if (gamepad2.left_trigger > 0) {
                intake.setPower(-1.5);
                carriage.setPosition(0.85);
            } else {
                intake.setPower(0);
                carriage.setPosition(0.85);
            }
            
            //Carriage Controls
            if (gamepad2.x && linearSlidesNotDown) {
                carriage.setPosition(-1);
                sleep(500);
            } else {
                carriage.setPosition(0.9);
            }
            
            //Carasel controls
            if (gamepad2.right_bumper) {
                rot.setPower(0.5);
            } else if (gamepad2.left_bumper) {
                rot.setPower(-0.5);
            } else {
                rot.setPower(0);
            }
            
            if (gamepad1.right_stick_x != 0) {//turning
                Turn(gamepad1.right_stick_x);
            } else { //driving
                Drive(-gamepad1.left_stick_y);
            }
        }
    }

    //Front is 223rpm, Back is 312rpm
    void Drive (float speed){
        if(gamepad1.a) {
            MotorFL.setPower(1*speed);
            MotorBL.setPower(1*speed);
            MotorFR.setPower(1*-speed);
            MotorBR.setPower(1*-speed);
        }
        if(gamepad1.b) {
            MotorFL.setPower(0.2*speed);
            MotorBL.setPower(0.2*speed);
            MotorFR.setPower(0.2*-speed);
            MotorBR.setPower(0.2*-speed);
        }
        else{
            MotorFL.setPower(0.6 * speed);
            MotorBL.setPower(0.6 * speed);
            MotorFR.setPower(0.6 * -speed);
            MotorBR.setPower(0.6 * -speed);
        }
    }
    
    private void runSlides(double pos, double power){
        double TICKS_PER_MOTOR_REV = 537.6;
       //countable events per revolution for output shaft, THIS NUMBER CHANGES DEPENDING ON THE TYPE OF MOTOR. VISIT SPECIFICATIONS ON THE WEBSITE THE TEAM GOT THE MOTOR FROM.
        double DRIVE_GEAR_REDUCTION = 1.0;
       //drive gear reduction
        double WHEEL_DIAMETER_INCHES = 1.5;
       //diameter of wheels
        double COUNTS_PER_INCH = (TICKS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
           (WHEEL_DIAMETER_INCHES * 3.1415);
           
        int newPos;
    
        if (opModeIsActive()) {
            
            pulley.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            
            newPos = (int)(pos * COUNTS_PER_INCH);
          
            pulley.setTargetPosition(newPos);

            pulley.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            pulley.setPower(power);

            while (opModeIsActive() && pulley.isBusy())
            {
               //Wait for it to finish rotating
            }
          
            pulley.setPower(0);

            pulley.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            sleep(250);
            /*
            1. Pause to prevent current movements to affect any future movements.
            */
       }
    }

    void Turn (float speed){
        if(gamepad1.a)
        {
            MotorFL.setPower(1*speed);
            MotorBL.setPower(1*speed);
            MotorFR.setPower(1*speed);
            MotorBR.setPower(1*speed);

        }
        if(gamepad1.b)
        {
            MotorFL.setPower(0.3*speed);
            MotorBL.setPower(0.3*speed);
            MotorFR.setPower(0.3*speed);
            MotorBR.setPower(0.3*speed);

        }
        else {
            MotorFL.setPower(0.6 * speed);
            MotorBL.setPower(0.6 * speed);
            MotorFR.setPower(0.6 * speed);
            MotorBR.setPower(0.6 * speed);
        }
    }

    void Strafe (float speed){
        if(gamepad1.a)
        {
            MotorFL.setPower(0.9*speed);
            MotorBL.setPower(0.9*-speed);
            MotorFR.setPower(0.9*speed);
            MotorBR.setPower(0.9*-speed);
        }
        if(gamepad1.b)
        {
            MotorFL.setPower(0.3*speed);
            MotorBL.setPower(0.3*-speed);
            MotorFR.setPower(0.3*speed);
            MotorBR.setPower(0.3*-speed);
        }
        else {
            MotorFL.setPower(0.6 * speed);
            MotorBL.setPower(0.6 * -speed);
            MotorFR.setPower(0.6 * speed);
            MotorBR.setPower(0.6 * -speed);
        }
    }

    /**
     * Control a mecanum drive base with three double inputs
     *
     * @param Strafe  is the first double X value which represents how the base should strafe
     * @param Forward is the only double Y value which represents how the base should drive forward
    //* @param Turn    is the second double X value which represents how the base should turn
     */
    public void mecanum(double Strafe, double Forward) {
        //Find the magnitude of the controller's input
        double r = Math.hypot(Strafe, Forward);

        //returns point from +X axis to point (forward, strafe)
        double robotAngle = Math.atan2(Forward, Strafe) - Math.PI / 4;

        //Quantity to turn by (turn)
        // double rightX = Turn;

        //double vX represents the velocities sent to each motor
        final double v1 = (r * Math.cos(robotAngle)); // + rightX;
        final double v2 = (r * Math.sin(robotAngle)); // - rightX;
        final double v3 = (r * Math.sin(robotAngle)); // + rightX;
        final double v4 = (r * Math.cos(robotAngle)); // - rightX;

        MotorFL.setPower(v1);
        MotorFR.setPower(v2);
        MotorBL.setPower(v3);
        MotorBR.setPower(v4);
    }
}
