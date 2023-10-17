package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
class GourmetGustaaf extends OpMode {
    private DcMotor liftMotor;
    private Servo planeLauncher;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");
        this.liftMotor = hardwareMap.get(DcMotor.class, "LiftMotor");
        this.planeLauncher = hardwareMap.get(Servo.class, "PlaneLauncher");
        telemetry.addData("Status", "Initialized!");
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_up) {
            liftMotor.setPower(0.5);
        }
        else if (gamepad1.dpad_down) {
            liftMotor.setPower(-0.5);
        }
        else {
            liftMotor.setPower(0);
        }

        planeLauncher.setPosition(gamepad1.right_trigger);
    }
}