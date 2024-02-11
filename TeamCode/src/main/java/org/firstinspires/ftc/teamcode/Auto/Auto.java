package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Shared.DriveTrain;

import java.lang.Math.*;

@Disabled
@Autonomous(name = "AUTO: FOR TESTING PURPOSES ONLY")
public class Auto extends LinearOpMode {
//    private DcMotorEx leftMotor;
//    private DcMotorEx rightMotor;
//
//    double GOBILDA_TICKS_PER_METER;
//    double TETRIX_TICKS_PER_METER;


    DriveTrain driveTrain;

    @Override
    public void runOpMode() {
//        leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
//        rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
//
//
//        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//
//        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        driveTrain = new DriveTrain(hardwareMap);

//        GOBILDA_TICKS_PER_METER = leftMotor.getMotorType().getTicksPerRev() * 0.11 * Math.PI * 3 * 1.07;
//        TETRIX_TICKS_PER_METER = (hMotor.getMotorType().getTicksPerRev() / (0.11*Math.PI) * (10.0/9.0));

        waitForStart();

        driveTrain.driveTo(1, 1, 0.2);

//        while (opModeIsActive()) {
//            telemetry.addData("Left Motor:", leftMotor.getCurrentPosition());
//            telemetry.addData("Right Motor:", rightMotor.getCurrentPosition());
//
//            telemetry.update();
//        }
    }
}