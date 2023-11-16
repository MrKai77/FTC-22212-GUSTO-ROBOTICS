package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Gourmet Gustaaf")
public class GourmetGustaaf extends OpMode {
    private DcMotorEx liftMotor;
    private Servo planeLaunchServo;

    private Servo clawServo;
    private Servo clawRotationServo;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");

        this.liftMotor = hardwareMap.get(DcMotorEx.class, "LiftMotor");
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

        if (gamepad1.y) {
            moveArm();
        }

        clawRotationServo.setPosition(gamepad1.right_trigger/2+0.5);
        clawServo.setPosition(gamepad1.left_trigger);
    }

    public void moveArm() {
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setTargetPosition(4800);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        clawServo.setPosition(1);
        liftMotor.setPower(1);

        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    private double calculateExponentialCurve(double input) {
        return Math.pow(input, 5);
    }
}