package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Shared.PixelManager;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;
import java.lang.Math.*;

@Autonomous(name = "BACKSTAGE: BLUE")
public class BackStageBluePark extends LinearOpMode {
    PixelManager pixelManager;

    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private DcMotorEx hMotor;

    double GOBILDA_TICKS_PER_METER;
    double TETRIX_TICKS_PER_METER;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");

        pixelManager = new PixelManager(hardwareMap);

        leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
        hMotor = hardwareMap.get(DcMotorEx.class, "SlideMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        hMotor.setDirection(DcMotorEx.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        hMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        GOBILDA_TICKS_PER_METER = leftMotor.getMotorType().getTicksPerRev() * 0.11 * Math.PI * 3 * 1.07;
        TETRIX_TICKS_PER_METER = (hMotor.getMotorType().getTicksPerRev() / (0.11*Math.PI) * (10.0/9.0) / 3);

        telemetry.addData("Status", "Initialized!");

        waitForStart();

        sleep(15000);

        moveMeters(0.8, 0.8, 0);
        sleep(100);

        moveMeters(-0.27*1.7, 0.27*1.7, 0);
        sleep(100);

        moveMeters(-2, -2, 0);
        sleep(100);

        pixelManager.setArmPower(-0.5);
        sleep(1000);
        pixelManager.setArmPower(0.2);
        sleep(500);
        pixelManager.setArmPower(0);

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
