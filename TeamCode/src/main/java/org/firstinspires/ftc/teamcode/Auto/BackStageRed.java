package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Shared.DriveTrain;
import org.firstinspires.ftc.teamcode.Shared.PixelManager;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;

@Autonomous
public class BackStageRed extends LinearOpMode {
    DriveTrain drive;
    PlaneLauncher planeLauncher;
    PixelManager pixelManager;
    GamepadEx gamepad;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        drive = new DriveTrain(hardwareMap);
        planeLauncher = new PlaneLauncher(hardwareMap);
        pixelManager = new PixelManager(hardwareMap);
        gamepad = new GamepadEx(gamepad1);

        telemetry.addData("Status", "Initialized!");
        telemetry.update();

        waitForStart();

        pixelManager.closeClaw();
        drive.driveTo(0.8, 0.8, 0.5);
        sleep(1000);
        drive.driveTo(0.26, -0.26, 0.5);
        sleep(1000);
        drive.driveTo(-1.5, -1.5, 0.5);
        sleep(1000);

        pixelManager.setLift(-0.7);
        sleep(100);
        pixelManager.setLift(-0.5);
        sleep(1000);

        pixelManager.setLift(0.2);
        sleep(500);
        pixelManager.setLift(-0.3);
        sleep(1000);

        pixelManager.setLift(0);
        sleep(1000);
        pixelManager.openClaw();
        sleep(1000);
    }
}
