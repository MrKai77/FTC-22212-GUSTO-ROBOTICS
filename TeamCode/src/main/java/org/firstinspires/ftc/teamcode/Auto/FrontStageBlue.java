package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Shared.DriveTrain;
import org.firstinspires.ftc.teamcode.Shared.PixelManager;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;

@Autonomous
public class FrontStageBlue extends LinearOpMode {
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

        drive.driveTo(0.81, 0.81, 0.5);
        sleep(5000);
        drive.driveTo(Math.PI * 0.4, Math.PI * -0.4, 0.5);
        sleep(5000);
        drive.driveTo(-3, -3, 0.5);
    }
}
