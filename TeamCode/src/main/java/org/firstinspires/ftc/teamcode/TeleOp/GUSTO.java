package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
        int currentPosition = Math.abs(pixelManager.getLiftPosition());
        if (gamepad.getButton(GamepadKeys.Button.DPAD_UP) && currentPosition <= 460) {
            pixelManager.closeClaw();
            pixelManager.setLift(-0.5);
        }
        else if (gamepad.getButton(GamepadKeys.Button.DPAD_DOWN) /* && currentPosition >= 0 */) {
            pixelManager.closeClaw();
            if (currentPosition <= 200) {
                pixelManager.setLift(0.15);
            } else {
                pixelManager.setLift(0.5);
            }
        } else {
            double speedDivisor = 1;

            if (gamepad.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
                speedDivisor = 2;
            }

            if (gamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                speedDivisor = 0.5;
            }
            drive.arcadeDrive(gamepad.getLeftY() / speedDivisor, gamepad.getLeftX() / speedDivisor);

            pixelManager.setLift(0);

            if (gamepad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                pixelManager.closeClaw();
            } else {
                pixelManager.openClaw();
            }
        }

        telemetry.addData("LIFT", currentPosition);

        if (gamepad.getButton(GamepadKeys.Button.A)) {
            pixelManager.resetLiftEncoder();
        }

        if (gamepad.getButton(GamepadKeys.Button.X)) {
            planeLauncher.liftoff();
        }

        telemetry.update();
    }
}
