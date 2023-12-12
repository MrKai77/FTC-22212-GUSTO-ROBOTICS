package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.*;

@TeleOp
public class GUSTO extends OpMode {

    // DRIVE
    Motor m_right, m_left, m_slide;
    HDrive drive;

    // IMU STUFF
    IMU imu;
    IMU.Parameters parameters;

    // GAMEPAD
    GamepadEx driverOp;

    Motor m_arm;
    Motor m_intake;

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

        m_left.setInverted(true);
        m_slide.setInverted(true);

        drive = new HDrive(m_left, m_right, m_slide);

        telemetry.addData("DRIVE", "Ready!");

        // MARK: GYRO
        telemetry.addData("GYRO", "Initializing...");
        telemetry.update();

        imu = hardwareMap.get(IMU.class, "imu");
        parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );
        imu.initialize(parameters);

        telemetry.addData("GYRO", "Ready!");
        telemetry.update();

        // MARK: GAMEPAD
        driverOp = new GamepadEx(gamepad1);

        m_intake = new Motor(hardwareMap, "IntakeMotor");
        m_arm = new Motor(hardwareMap, "ArmMotor");

        telemetry.addData("READY!", "");
        telemetry.update();
    }

    @Override
    public void loop() {
//        drive.driveFieldCentric(
//                driverOp.getLeftY(),
//                driverOp.getLeftX(),
//                driverOp.getRightX(),
//                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)
//        );

//        drive.driveRobotCentric(
//                driverOp.getLeftY(),
//                driverOp.getLeftX(),
//                driverOp.getRightX()
//        );

        setDrive();

        telemetry.addData("Arm", m_arm.encoder.getPosition());

        m_intake.set(driverOp.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));
    }

    public void setDrive() {
        double leftPower = driverOp.getLeftY();
        double rightPower = driverOp.getLeftY();
        double hPower = driverOp.getLeftX();

        leftPower -= driverOp.getRightX() / 2 - hPower / 10;
        rightPower += driverOp.getRightX() / 2 - hPower / 10;

        m_left.set(leftPower);
        m_right.set(rightPower);

        m_slide.set(hPower);
    }
}
