package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "AUTO: FOR TESTING PURPOSES ONLY")
public class Auto extends LinearOpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    double GOBILDA_TICKS_PER_METER;
    double TETRIX_TICKS_PER_METER;

    @Override
    public void runOpMode() {
        this.leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        this.rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        GOBILDA_TICKS_PER_METER = leftMotor.getMotorType().getTicksPerRev() * 0.11 * 3.1415 * 3;

        waitForStart();

        moveMeters(1);

        while (opModeIsActive()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());

            telemetry.update();
        }
    }

    public void moveMeters(double meters) {
        // Reset encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        leftMotor.setTargetPosition((int) (meters * GOBILDA_TICKS_PER_METER));
        rightMotor.setTargetPosition((int) (meters * GOBILDA_TICKS_PER_METER));

        // Set RUN_TO_POSITION mode
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power with the correct sign for forward movement
        leftMotor.setPower(0.2);
        rightMotor.setPower(0.2);

        // Monitor motor positions until they reach the target
        while (leftMotor.isBusy() && rightMotor.isBusy()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());

            telemetry.update();
        }

        // Stop motors after reaching the target position
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    // Positive value: turn clockwise
    // Negative value: Turn counter-clockwise
//    public void turn(double degrees) {
//        final double GOBILDA_TICKS_PER_METER = 4583.66236105;
//        final double TETRIX_TICKS_PER_METER = 2393.1492173;
//
//
//    }
//        final double TICKS_PER_DEGREE = 6.49731;
//
//        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        leftMotor.setTargetPosition((int) (TICKS_PER_DEGREE * degrees));
//        rightMotor.setTargetPosition((int) (TICKS_PER_DEGREE * -degrees));
//
//        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        leftMotor.setPower(0.5);
//        rightMotor.setPower(0.5);
//
//        while (leftMotor.isBusy() && rightMotor.isBusy()) {
//            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
//            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());
//
//            telemetry.update();
//        }
//
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);
//    }
}