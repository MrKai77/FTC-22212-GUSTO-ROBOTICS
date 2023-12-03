package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "FRONTSTAGE: BLUE")
public class FrontStageBluePark extends LinearOpMode {

    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private DcMotorEx hMotor;

    private Servo leftServo;
    private Servo rightServo;

    private DcMotorEx intakeMotor;

    double GOBILDA_TICKS_PER_METER;
    double TETRIX_TICKS_PER_METER;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");

        leftServo = hardwareMap.get(Servo.class, "LeftServo");
        rightServo = hardwareMap.get(Servo.class, "RightServo");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "IntakeMotor");

        leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
        hMotor = hardwareMap.get(DcMotorEx.class, "HMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        hMotor.setDirection(DcMotorEx.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        hMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        GOBILDA_TICKS_PER_METER = leftMotor.getMotorType().getTicksPerRev() * 0.11 * 3.1415 * 3 * 1.07;
        TETRIX_TICKS_PER_METER = (hMotor.getMotorType().getTicksPerRev() / (0.11*3.1415)) * (10.0/9.0);

        telemetry.addData("Status", "Initialized!");

        waitForStart();

        leftServo.setPosition(0.2);
        rightServo.setPosition(0.2);

        moveMeters(0.8, 0.8, 0);
        sleep(100);

        moveMeters(0, 0,0.3);
        sleep(100);

        moveMeters(-0.27, 0.27, 0);
        sleep(100);

        moveMeters(-2.8, -2.8, 0);
        sleep(1000);

        leftServo.setPosition(1);
        rightServo.setPosition(1);

        sleep(2000);

        leftServo.setPosition(0.2);
        rightServo.setPosition(0.2);

        sleep(2000);

        moveMeters(0.1, 0.1,1);
        sleep(100);

        moveMeters(-2, -2, 0);
    }

    public void moveMeters(double rightMeters, double leftMeters, double hMeters) {
        // Reset encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        leftMotor.setTargetPosition((int) (leftMeters * GOBILDA_TICKS_PER_METER));
        rightMotor.setTargetPosition((int) (rightMeters * GOBILDA_TICKS_PER_METER));
        hMotor.setTargetPosition((int) (hMeters * TETRIX_TICKS_PER_METER));

        // Set RUN_TO_POSITION mode
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power with the correct sign for forward movement
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        hMotor.setPower(0.2);

        // Monitor motor positions until they reach the target
        while (leftMotor.isBusy() || rightMotor.isBusy() || hMotor.isBusy()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());
            telemetry.addData("H Motor:", hMotor.getCurrentPosition());

            telemetry.update();
        }

        // Stop motors after reaching the target position
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        hMotor.setPower(0);
    }
}
