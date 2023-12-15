package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.teamcode.Shared.DriveTrain;
import org.firstinspires.ftc.teamcode.Shared.PixelManager;

@TeleOp
public class GUSTO extends OpMode {

    DriveTrain drive;
    PixelManager pixelManager;

    GamepadEx gamepad;

    // IMU STUFF
    IMU imu;
    IMU.Parameters parameters;

    Motor m_arm;
    Motor m_intake;

    @Override
    public void init() {

        drive = new DriveTrain(hardwareMap);
        pixelManager = new PixelManager(hardwareMap);
        gamepad = new GamepadEx(gamepad1)

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
    }

    @Override
    public void loop() {
        drive.easyDrive(gamepad.getLeftY(), gamepad.getLeftX(), gamepad.getRightX());

        if (gamepad.getButton(GamepadKeys.Button.DPAD_UP)) {
            pixelManager.setArmPosition(pixelManager.SCORING_POSITION);
        }
        else if (gamepad.getButton(GamepadKeys.Button.DPAD_DOWN)) {
            pixelManager.setArmPosition(pixelManager.FEEDING_POSITION);
        }
        else {
            pixelManager.setArmPosition(pixelManager.RESTING_POSITION);
        }
    }
}
