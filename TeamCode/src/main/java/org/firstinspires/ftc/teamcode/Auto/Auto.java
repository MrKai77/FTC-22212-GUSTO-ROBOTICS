package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import java.lang.Math.*;

@Autonomous(name = "AUTO: FOR TESTING PURPOSES ONLY")
public class Auto extends LinearOpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    double GOBILDA_TICKS_PER_METER;
    double TETRIX_TICKS_PER_METER;

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");


        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        GOBILDA_TICKS_PER_METER = leftMotor.getMotorType().getTicksPerRev() * 0.11 * Math.PI * 3 * 1.07;
        TETRIX_TICKS_PER_METER = (hMotor.getMotorType().getTicksPerRev() / (0.11*Math.PI) * (10.0/9.0));

        waitForStart();

        moveMeters(1, 1, 0);

//        while (opModeIsActive()) {
//            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
//            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());
//
//            telemetry.update();
//        }
    }

    public void moveMeters(double rightMeters, double leftMeters, double hMeters) {
        final double targetRight = rightMeters * GOBILDA_TICKS_PER_METER;
        final double targetLeft = leftMeters * GOBILDA_TICKS_PER_METER;

        // Reset encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        leftMotor.setTargetPosition((int) (targetRight));
        rightMotor.setTargetPosition((int) (targetLeft));

        // Set RUN_TO_POSITION mode
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power with the correct sign for forward movement
        leftMotor.setPower(0.2);
        rightMotor.setPower(0.2);

        // Monitor motor positions until they reach the target
        while (leftMotor.isBusy() || rightMotor.isBusy()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());

            telemetry.update();
        }

        // Stop motors after reaching the target position
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}