package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Shared.DriveTrain;
import org.firstinspires.ftc.teamcode.Shared.PixelManager;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;

@TeleOp(name = "SUPER GUSTO")
public class GUSTO extends OpMode {

    DriveTrain drive;
    PlaneLauncher planeLauncher;
    PixelManager pixelManager;
    GamepadEx gamepad;

    @Override
    public void init() {

        drive = new DriveTrain(hardwareMap);
        planeLauncher = new PlaneLauncher(hardwareMap);
        pixelManager = new PixelManager(hardwareMap);
        gamepad = new GamepadEx(gamepad1);

        telemetry.addData("READY!", "");
        telemetry.update();
    }

    @Override
    public void loop() {
        double speedDivisor = gamepad.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON) ? 2 : 1;
        drive.arcadeDrive(gamepad.getLeftY() / speedDivisor, gamepad.getLeftX() / speedDivisor);

        if (gamepad.getButton(GamepadKeys.Button.X)) {
            planeLauncher.liftoff();
        }

//        pixelManager.setLift(gamepad.getRightY() / 3);
        int currentPosition = Math.abs(pixelManager.getLiftPosition());
        if (gamepad.getButton(GamepadKeys.Button.DPAD_UP) && currentPosition <= 460) {
            pixelManager.setLift(-0.5);
        }
        else if (gamepad.getButton(GamepadKeys.Button.DPAD_DOWN) && currentPosition >= 0) {
            if (currentPosition <= 200) {
                pixelManager.setLift(0.1);
            } else {
                pixelManager.setLift(0.5);
            }
        } else {
            pixelManager.setLift(0);
        }

        telemetry.addData("LIFT", currentPosition);

        if (gamepad1.a) {
            pixelManager.resetLiftEncoder();
        }

        telemetry.update();
    }
}
