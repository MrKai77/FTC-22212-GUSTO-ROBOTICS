package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@Autonomous(name = "Frontstage: Blue (PARK)")
public class FrontStageBluePark extends LinearOpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    @Override
    public void runOpMode() {
        this.leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        this.rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
//        this.rightMotor.setDirection(DcMotor.Direction.REVERSE);
//        this.leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        waitForStart();

        final double TICKS_PER_METER = 1711.55;
        int leftTarget = (int) (leftMotor.getCurrentPosition() + TICKS_PER_METER);
        leftMotor.setTargetPosition(leftTarget);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setPower(0.1);

        while (opModeIsActive() && leftMotor.isBusy()) {
            telemetry.addData("Position:", leftMotor.getCurrentPosition());
            telemetry.addData("Target:", leftTarget);

            telemetry.update();
        }
    }

//    public void moveMeters(double meters) {
//        final double TICKS_PER_METER = 1711.55;
//
//        int leftTarget = (int) (leftMotor.getCurrentPosition() + (meters * TICKS_PER_METER));
//        int rightTarget = (int) (rightMotor.getCurrentPosition() + (meters * TICKS_PER_METER));
//
//        // Set target position
//        leftMotor.setTargetPosition(leftTarget);
//        rightMotor.setTargetPosition(rightTarget);
//
//        // Set RUN_TO_POSITION mode
//        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        // Set power with the correct sign for forward movement
//        leftMotor.setPower(0.1);
//        rightMotor.setPower(0.1);
//
//        // Monitor motor positions until they reach the target
//        while (leftMotor.isBusy() && rightMotor.isBusy()) {
//            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
//            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());
//            telemetry.addData("Left Target:", leftTarget);
//            telemetry.addData("Right Target:", rightTarget);
//
//            telemetry.update();
//        }
//
//        // Stop motors after reaching the target position
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);
//    }
//
//    // Positive value: turn clockwise
//    // Negative value: Turn counter-clockwise
//    public void turn(double degrees) {
//        final double TICKS_PER_DEGREE = 6.49731;
//
//        double leftTarget = leftMotor.getCurrentPosition() + (TICKS_PER_DEGREE * degrees);
//        double rightTarget = rightMotor.getCurrentPosition() + (TICKS_PER_DEGREE * -degrees);
//
//        leftMotor.setTargetPosition((int) (leftTarget));
//        rightMotor.setTargetPosition((int) (rightTarget));
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
//            telemetry.addData("Left Target:", leftTarget);
//            telemetry.addData("Right Target:", rightTarget);
//
//            telemetry.update();
//        }
//
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);
//    }
}
