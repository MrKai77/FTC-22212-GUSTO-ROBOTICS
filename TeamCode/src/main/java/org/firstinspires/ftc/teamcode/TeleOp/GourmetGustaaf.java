package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Gourmet Gustaaf")
public class GourmetGustaaf extends OpMode {
    private DcMotor liftMotor;
    private Servo planeLaunchServo;

    private Servo clawServo;
    private Servo clawRotationServo;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");

        this.liftMotor = hardwareMap.get(DcMotor.class, "LiftMotor");
        this.planeLaunchServo = hardwareMap.get(Servo.class, "PlaneLauncher");
        this.clawRotationServo = hardwareMap.get(Servo.class, "ClawRotation");
        this.clawServo = hardwareMap.get(Servo.class, "ClawServo");

        this.leftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        this.rightMotor = hardwareMap.get(DcMotor.class, "RightMotor");

        this.rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized!");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up) {
            liftMotor.setPower(1);
        }
        else if (gamepad1.dpad_down) {
            liftMotor.setPower(-1);
        }
        else {
            liftMotor.setPower(0);
        }

        planeLaunchServo.setPosition(gamepad1.x ? 0.5 : 0.8);

        leftMotor.setPower(calculateExponentialCurve(gamepad1.left_stick_y));
        rightMotor.setPower(calculateExponentialCurve(gamepad1.right_stick_y));

        clawRotationServo.setPosition(gamepad1.right_trigger/2+0.5);
        clawServo.setPosition(gamepad1.left_trigger);
    }

    private double calculateExponentialCurve(double input) {
        return Math.pow(input, 5);
    }
}