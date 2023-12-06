package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.*;

public class GUSTO extends OpMode {

    // DRIVE
    Motor m_right, m_left, m_slide;
    HDrive drive;

    // IMU STUFF
    IMU imu;
    IMU.Parameters parameters;

    // GAMEPAD
    GamepadEx driverOp;

    @Override
    public void init() {

        // MARK: DRIVE
        telemetry.addData("DRIVE", "Initializing...");
        telemetry.update();
        m_right = new Motor(hardwareMap, "RightMotor");
        m_left = new Motor(hardwareMap, "LeftMotor");
        m_slide = new Motor(hardwareMap, "SlideMotor");

        m_right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_slide.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new HDrive(m_left, m_right, m_slide);

        telemetry.addData("DRIVE", "Ready!");

        // MARK: GYRO
        telemetry.addData("GYRO", "Initializing...");
        telemetry.update();

        imu = hardwareMap.get(IMU.class, "imu");
        parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.DOWN
                )
        );
        imu.initialize(parameters);

        telemetry.addData("GYRO", "Ready!");
        telemetry.update();

        // MARK: GAMEPAD
        driverOp = new GamepadEx(gamepad1);

        telemetry.addData("READY!", "");
        telemetry.update();
    }

    @Override
    public void loop() {
        drive.driveFieldCentric(
                driverOp.getLeftX(),
                driverOp.getLeftY(),
                driverOp.getRightX(),
                getAngle()
        );
    }

    private double getAngle() {
        // Create an object to receive the IMU angles
        YawPitchRollAngles robotOrientation;
        robotOrientation = imu.getRobotYawPitchRollAngles();

        double yaw   = robotOrientation.getYaw(AngleUnit.DEGREES);
//        double pitch = robotOrientation.getPitch(AngleUnit.DEGREES);
//        double roll  = robotOrientation.getRoll(AngleUnit.DEGREES);

        return  yaw;
    }
}
