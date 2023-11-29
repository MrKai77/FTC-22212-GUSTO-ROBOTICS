package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@Autonomous(name = "AUTO: FOR TESTING PURPOSES ONLY")
public class Auto extends LinearOpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    @Override
    public void runOpMode() {
        this.leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        this.rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
        this.leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        waitForStart();

        turn(90);
        moveMeters(1);

        while (opModeIsActive()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());

            telemetry.update();
        }
    }

    public void moveMeters(double meters) {
        final double TICKS_PER_METER = 1711.55;

        // Reset encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        leftMotor.setTargetPosition((int) (meters * TICKS_PER_METER));
        rightMotor.setTargetPosition((int) (meters * TICKS_PER_METER));

        // Set RUN_TO_POSITION mode
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set power with the correct sign for forward movement
        leftMotor.setPower(0.1);
        rightMotor.setPower(0.1);

        // Monitor motor positions until they reach the target
        while (leftMotor.isBusy() && rightMotor.isBusy()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());

            if (Math.abs(leftMotor.getCurrentPosition()) >= meters * TICKS_PER_METER || Math.abs(rightMotor.getCurrentPosition()) >= meters * TICKS_PER_METER) {
                break;
            }

            telemetry.update();
        }

        // Stop motors after reaching the target position
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    // Positive value: turn clockwise
    // Negative value: Turn counter-clockwise
    public void turn(double degrees) {
        final double TICKS_PER_DEGREE = 6.49731;

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition((int) (TICKS_PER_DEGREE * degrees));
        rightMotor.setTargetPosition((int) (TICKS_PER_DEGREE * -degrees));

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);

        while (leftMotor.isBusy() && rightMotor.isBusy()) {
            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());

            telemetry.update();
        }

        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}

// add camera functionality to robot code after first competition?
