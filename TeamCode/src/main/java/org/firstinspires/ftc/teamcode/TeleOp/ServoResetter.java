package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Shared.PlaneLauncher;

@TeleOp
public class ServoResetter extends OpMode {

    PlaneLauncher planeLauncher;

    GamepadEx gamepad;
    Servo m_planeLauncher;

    @Override
    public void init() {

        planeLauncher = new PlaneLauncher(hardwareMap);
        gamepad = new GamepadEx(gamepad1);

        telemetry.addData("READY!", "");
        telemetry.update();

        m_planeLauncher = hardwareMap.get(Servo.class, "Servo");
        m_planeLauncher.setPosition(0);
    }

    @Override
    public void loop() {

        if (gamepad.getButton(GamepadKeys.Button.Y)) {
            m_planeLauncher.setPosition(0.7);
            telemetry.addData("Plane Launcher", "Launched");
        } else {
            m_planeLauncher.setPosition(0);
        }

        telemetry.update();
    }
}
