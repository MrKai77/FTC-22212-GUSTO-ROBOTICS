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
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() {
        this.leftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        this.rightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
        this.rightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        leftMotor.setPower(0.5);
        rightMotor.setPower(-0.5);

        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < 1000) && (runtime.milliseconds() > 5000)) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        }

//        runtime.reset();
//        while (opModeIsActive() && (runtime.milliseconds() < 5)) {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
//        }
    }
}

