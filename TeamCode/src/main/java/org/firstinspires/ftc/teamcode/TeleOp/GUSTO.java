package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Shared.DriveTrain;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;

@TeleOp(name = "SUPER GUSTO")
public class GUSTO extends OpMode {

    DriveTrain drive;
    PlaneLauncher planeLauncher;

    GamepadEx gamepad;

    Motor m_arm;
    Motor m_intake;

    Servo m_planeLauncher;

    private Motor liftMotor;
    private Servo clawServo;

    @Override
    public void init() {

        drive = new DriveTrain(hardwareMap);
        planeLauncher = new PlaneLauncher(hardwareMap);
        gamepad = new GamepadEx(gamepad1);

        liftMotor = new Motor(hardwareMap, "LiftMotor");
        liftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        clawServo = hardwareMap.get(Servo.class, "ClawServo");

        telemetry.addData("READY!", "");
        telemetry.update();
    }

    @Override
    public void loop() {
        double speedDivisor = gamepad.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON) ? 1 : 2;
        drive.m_drive.arcadeDrive(gamepad.getLeftY(), gamepad.getLeftX());

        if (gamepad1.x) {
            planeLauncher.liftoff();
        }

        if (gamepad1.dpad_up) {
            liftMotor.set(-0.7);
            clawServo.setPosition(0.25);
        } else if (gamepad1.dpad_down) {
            liftMotor.set(0.6);
            clawServo.setPosition(0.25);
        } else {
            liftMotor.set(0);

            double clawTarget = gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) / 4;
            clawServo.setPosition(clawTarget);
        }

        telemetry.update();
    }
}
