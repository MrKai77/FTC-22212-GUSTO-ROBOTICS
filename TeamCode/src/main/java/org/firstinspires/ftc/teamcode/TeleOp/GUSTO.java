package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
        double speedDivisor = gamepad.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON) ? 4 : 2;
        drive.m_drive.arcadeDrive(gamepad.getLeftY() / speedDivisor, gamepad.getLeftX() / speedDivisor);

        if (gamepad1.x) {
            planeLauncher.liftoff();
        }

        double position = -liftMotor.getCurrentPosition();

        if (gamepad1.dpad_up && position <= 420) {
            liftMotor.set(-0.6);
            clawServo.setPosition(0.25);
        } else if (gamepad1.dpad_down) {
            if (position <= 200) {
                liftMotor.set(0.2);
            } else {
                liftMotor.set(0.5);
            }

            clawServo.setPosition(0.25);
        } else {
            liftMotor.set(0);

            double clawTarget = gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) / 4;
            clawServo.setPosition(clawTarget);
        }

        telemetry.addData("Lift Motor", liftMotor.getCurrentPosition());

        if (gamepad1.a) {
            liftMotor.resetEncoder();
        }

        telemetry.update();
    }
}
