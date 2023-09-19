package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "PowerPlay", group = "Autonomous")
public class PowerPlayAutonomous extends LinearOpMode {
    Servo claw, claw2;
    DcMotor arm, arm2;
    DcMotor motorFL, motorBL, motorFR, motorBR;
    double integral = 0;
    double kP = 0.2;
    double kI = 1;
    double kD = 0.2;
    double lastError = 0;
    double error;
    ElapsedTime timer = new ElapsedTime();

    private static final String TFOD_MODEL_ASSET = "model_20230204_111440.tflite";

    private static final String[] LABELS = {
      "Red",
      "Green",
      "Blue"
    };

    private static final String VUFORIA_KEY =
            "AYbIlMv/////AAABmZLfFZLmq0wYmDKF2wigCNkxg1+q9bUOIcBWDofk+xQPt8nKjak13jlM1KgkYFyaE+RkRzHKGM+bweSLibsp3K921oe9wILACQ9Fw8a3lpfXHwDfE90R0c44iusRTB8ZJ2PYiyzL3dSnIxP9OE4yz6hA35KgzIjcXL4OXEyb6ySly3A2XRC5JxaaW1Ay8WzA3doE7K8cxkPqnp6f+JoOZDHcp6p4NB7ddpHSNsutqXWY0gNgBzOjgriRXr2rS/ZY02NW2gjBfcSsbw9kfW8Qk1aaHgGc9TtESlzNm6+HsJf9ec2iHbfIe+AdJnZpQJvgrDR9UiYOk+NytWaDovuETfCsuliIrB/HrVVB0ufDSeRP";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        
        claw = hardwareMap.get(Servo.class, "claw");
        claw2 = hardwareMap.get(Servo.class, "claw2");
        
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
    
        motorFL= hardwareMap.dcMotor.get("fL");
        motorBL= hardwareMap.dcMotor.get("bL");
        motorFR= hardwareMap.dcMotor.get("fR");
        motorBR= hardwareMap.dcMotor.get("bR");

        motorFL.setDirection(DcMotor.Direction.REVERSE);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.0, 16.0/9.0);
        }

        waitForStart();

        if (opModeIsActive()) {
            // claw.setPosition(1);
            // claw2.setPosition(0.8);
            // sleep(500);
            resetEncoders();
            // strafe(9);
            // move(9);
            while (opModeIsActive()) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Objects Detected", updatedRecognitions.size());

                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals("Red")) {
                                telemetry.addData("# Red Detected", 1);
                                strafe(7);
                                move(8);
                            } else if (recognition.getLabel().equals("Green")) {
                                telemetry.addData("# Green Detected", 2);
                                move(8);
                            } else {
                                telemetry.addData("# Blue Detected", 3);
                                strafe(-7);
                                move(8);
                            }
                        }
                    } else {
                        telemetry.addData("# Objects Detected", 0);
                    }
                    telemetry.update();
                }
            }
        }
    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void resetEncoders() {
        motorFL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        tfod.loadModelFromFile(TFOD_MODEL_ASSET, LABELS);
    }
    
    private int getTicks(double inches) {
        double TICKS_PER_MOTOR_REV = 537.6;
        double DRIVE_GEAR_REDUCTION = 1.0;
        double WHEEL_DIAMETER_INCHES = 1.5;
        double COUNTS_PER_INCH = (TICKS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
          (WHEEL_DIAMETER_INCHES * 3.1415);
        
        return (int)(inches * COUNTS_PER_INCH);
    }
    
    private double pid(double target, double state) {
        target = getTicks(target);
        error = target - state;
        telemetry.addData("Current Ticks #", motorFL.getCurrentPosition());
        telemetry.addData("Target #", target);
        integral += error * timer.seconds();
        double deriv = (error - lastError)/timer.seconds();
        lastError = error;
        if (Math.abs(error) < 2) integral = 0;
        timer.reset();
        telemetry.addData("P #", error);
        // telemetry.addData("I #", integral);
        // telemetry.addData("D #", deriv);
        return Math.tanh((kP * error) + (kD * deriv) + (kI * integral)) * 0.3f;
    }
    
    private void move(double inches) {
        double power = pid(inches, motorFL.getCurrentPosition());
        while (Math.abs(error) > 2) {
            telemetry.addData("Move", 0);
            motorFL.setPower(power);
            motorBL.setPower(power);
            motorFR.setPower(power);
            motorBR.setPower(power);
            power = pid(inches, motorFL.getCurrentPosition());
            telemetry.addData("Power #", power);
            telemetry.update();
        }
        motorFL.setPower(0);
        motorBL.setPower(0);
        motorFR.setPower(0);
        motorBR.setPower(0);
        resetEncoders();
    }
    
    private void strafe(double inches) {
        double power = pid(inches, -motorFL.getCurrentPosition());
        while (Math.abs(error) > 2) {
            telemetry.addData("Strafe", 0);
            motorFL.setPower(power);
            motorBL.setPower(-power);
            motorFR.setPower(-power);
            motorBR.setPower(power);
            power = pid(inches, -motorFL.getCurrentPosition());
            telemetry.addData("Power #", power);
            telemetry.update();
        }
        motorFL.setPower(0);
        motorBL.setPower(0);
        motorFR.setPower(0);
        motorBR.setPower(0);
        resetEncoders();
    }
    
    private void moveArm(double power, double sec) {
        timer.reset();
        double start = timer.seconds();
        double curr = timer.seconds();
        arm.setPower(power);
        arm2.setPower(power);
        while(curr - start < sec) {curr = timer.seconds();}
        arm.setPower(0.35);
        arm2.setPower(0.35);
    }
    
}
