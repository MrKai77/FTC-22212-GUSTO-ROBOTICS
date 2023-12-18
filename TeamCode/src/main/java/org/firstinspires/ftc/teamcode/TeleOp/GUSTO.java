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
import org.firstinspires.ftc.teamcode.Shared.PixelManager;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;

@TeleOp
public class GUSTO extends OpMode {

    DriveTrain drive;
    PixelManager pixelManager;
    PlaneLauncher planeLauncher;

    GamepadEx gamepad;

    // IMU STUFF
    IMU imu;
    IMU.Parameters parameters;

    Motor m_arm;
    Motor m_intake;

    Servo m_planeLauncher;

    boolean isLifting = false;

    @Override
    public void init() {

        drive = new DriveTrain(hardwareMap);
        pixelManager = new PixelManager(hardwareMap);
        planeLauncher = new PlaneLauncher(hardwareMap);
        gamepad = new GamepadEx(gamepad1);

        imu = hardwareMap.get(IMU.class, "imu");
        parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );
        imu.initialize(parameters);

        telemetry.addData("READY!", "");
        telemetry.update();

        m_planeLauncher = hardwareMap.get(Servo.class, "PlaneLauncher");
        m_planeLauncher.setPosition(0.5);
    }

    @Override
    public void loop() {
        final boolean previousIsLifting = isLifting;
        final double intakePower = (gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)/2) / 2;

        double speedDivisor = gamepad.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON) ? 1 : 2;
        drive.easyDrive(gamepad.getLeftY() / speedDivisor, gamepad.getLeftX() / speedDivisor, -gamepad.getRightX());

        if (gamepad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            pixelManager.setArmPower(-0.5);
            pixelManager.setIntakePower(0.4);
            isLifting = true;
        } else if (gamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
            pixelManager.setArmPower(0.5);
            pixelManager.setIntakePower(-0.4);
            isLifting = false;

        } else if (intakePower != 0) {
            pixelManager.setArmPower(0.15);
            pixelManager.setIntakePower(intakePower);
        } else {
            pixelManager.setArmPower(0);
            pixelManager.setIntakePower(0);
        }

//        if (gamepad.getButton(GamepadKeys.Button.DPAD_UP)) {
//            pixelManager.setArmPosition(pixelManager.SCORING_POSITION);
//        }
//        else if (gamepad.getButton(GamepadKeys.Button.DPAD_DOWN)) {
//            pixelManager.setArmPosition(pixelManager.FEEDING_POSITION);
//        }
//        else {
//            pixelManager.setArmPosition(pixelManager.RESTING_POSITION);
//        }

        if (gamepad.getButton(GamepadKeys.Button.Y)) {
            m_planeLauncher.setPosition(0.7);
            telemetry.addData("Plane Launcher", "Launched");
        } else {
            m_planeLauncher.setPosition(0.5);
        }

        telemetry.update();
    }
}
