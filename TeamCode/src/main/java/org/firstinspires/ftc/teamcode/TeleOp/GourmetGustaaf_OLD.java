package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "Gourmet Gustaaf OLD")
public class GourmetGustaaf_OLD extends OpMode {
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

        this.leftMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized!");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up) {
            liftMotor.setPower(1);
            clawRotationServo.setPosition(1);
        }
        else if (gamepad1.dpad_down) {
            liftMotor.setPower(-0.8);
            clawRotationServo.setPosition(1);
        }
        else {
            liftMotor.setPower(0);

            if (gamepad1.right_bumper) {
                clawRotationServo.setPosition(0.5);
            } else {
                clawRotationServo.setPosition(1-gamepad1.right_trigger);
            }
        }

//        planeLaunchServo.setPosition(gamepad1.x ? 0.5 : 0.8);

        if (gamepad1.left_bumper) {
            leftMotor.setPower(gamepad1.left_stick_y / 6);
            rightMotor.setPower(gamepad1.right_stick_y / 6);
        } else {
            leftMotor.setPower(gamepad1.left_stick_y / 2);
            rightMotor.setPower(gamepad1.right_stick_y / 2);
        }

//        if (gamepad1.y) {
//            moveArm();
//        }

        clawServo.setPosition(0.5 + gamepad1.left_trigger / 2);
    }

    public void moveArm() {
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setTargetPosition(4800);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        clawServo.setPosition(1);
        liftMotor.setPower(1);

        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}