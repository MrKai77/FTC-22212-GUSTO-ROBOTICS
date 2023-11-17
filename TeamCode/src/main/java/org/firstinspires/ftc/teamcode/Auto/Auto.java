package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class Auto extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() {
        this.leftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        this.rightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
        this.rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

//        leftMotor.setPower(0.5);
//        rightMotor.setPower(-0.5);
//
//        sleep(4300);
//
//        leftMotor.setPower(1);
//        rightMotor.setPower(1);
//
//        sleep(5000);
//
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);

        turnRight();
    }

    public void moveMeters(int meters, double speed) {
        double TICKS_PER_METER = 1711.55;

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setTargetPosition((int) (TICKS_PER_METER * meters));
        rightMotor.setTargetPosition((int) (TICKS_PER_METER * meters));

        leftMotor.setPower(speed);
        rightMotor.setPower(speed);

//        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void turnRight() {
        double TICKS_PER_RIGHT_ANGLE = 292.379;
//        double TICKS_PER_RIGHT_ANGLE = leftMotor.getMotorType().getTicksPerRev();

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition((int) (TICKS_PER_RIGHT_ANGLE));
        rightMotor.setTargetPosition((int) (-TICKS_PER_RIGHT_ANGLE));

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(0.1);
        rightMotor.setPower(-0.1);

        // Wait for the motor to reach the target position
        while (opModeIsActive() && leftMotor.isBusy() || rightMotor.isBusy()) {
        }

        // Stop the motor
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}

// add camera functionality to robot code after first competition?
