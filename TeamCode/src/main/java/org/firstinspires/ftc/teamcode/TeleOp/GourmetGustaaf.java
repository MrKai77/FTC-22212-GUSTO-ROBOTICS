package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Gourmet Gustaaf")
public class GourmetGustaaf extends OpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private DcMotorEx hMotor;

    private Servo leftServo;
    private Servo rightServo;

    private DcMotorEx intakeMotor;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");

        leftServo = hardwareMap.get(Servo.class, "LeftServo");
        rightServo = hardwareMap.get(Servo.class, "RightServo");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "IntakeMotor");

        leftMotor = hardwareMap.get(DcMotorEx.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "RightMotor");
        hMotor = hardwareMap.get(DcMotorEx.class, "HMotor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        hMotor.setDirection(DcMotor.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Status", "Initialized!");
    }

    @Override
    public void loop() {
        setDrive();

        if (gamepad1.dpad_up) {
            intakeMotor.setPower(-0.2);
            leftServo.setPosition(1);
            rightServo.setPosition(1);
        }
        else if (gamepad1.dpad_down) {
            intakeMotor.setPower(gamepad1.right_trigger - (gamepad1.left_trigger / 2));
            leftServo.setPosition(1);
            rightServo.setPosition(1);
        }
        else {
            intakeMotor.setPower(gamepad1.right_trigger - (gamepad1.left_trigger / 2));
            leftServo.setPosition(0.8);
            rightServo.setPosition(0.8);
        }
    }

    public void setDrive() {
        double leftPower = gamepad1.left_stick_y / 2;
        double rightPower = gamepad1.left_stick_y / 2;
        double hPower = gamepad1.left_stick_x / 2;

        leftPower -= gamepad1.right_stick_x;
        rightPower += gamepad1.right_stick_x;

        leftMotor.setPower(leftPower / 2);
        rightMotor.setPower(rightPower / 2);
        hMotor.setPower(hPower);
    }
}