package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "FRONT STAGE: RED")
public class FrontStageRedPark extends LinearOpMode {

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

        rightMotor.setDirection(DcMotorEx.Direction.REVERSE);
        hMotor.setDirection(DcMotorEx.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.REVERSE);

        GOBILDA_TICKS_PER_METER = leftMotor.getMotorType().getTicksPerRev() * 0.11 * 3.1415 * 3;
        TETRIX_TICKS_PER_METER = hMotor.getMotorType().getTicksPerRev() * 0.11 * 3.1415 * 3;

        telemetry.addData("Status", "Initialized!");

        waitForStart();

        leftServo.setPosition(0.08);
        rightServo.setPosition(0.08);

        moveMeters(0.8, 0.8, 0);
        sleep(100);

        moveMeters(0, 0,0.3);
        sleep(100);

        moveMeters(0.2, -0.2, 0);
        sleep(100);

        moveMeters(-2.8, -2.8, 0);
        sleep(1000);

        intakeMotor.setPower(0.4);
        leftServo.setPosition(1);
        rightServo.setPosition(1);

        sleep(1000);
        intakeMotor.setPower(0);
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
        leftMotor.setPower(0.2);
        rightMotor.setPower(0.2);
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
