package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GUSTO extends OpMode {

    // DRIVE
    Motor m_right, m_left, m_slide;
    HDrive drive;

    // IMU STUFF
    BNO055IMU imu;
    BNO055IMU.Parameters parameters;
    Orientation lastAngles = new Orientation();
    double globalAngle;

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

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        imu.initialize(parameters);

        telemetry.addData("GYRO", "Calibrating...");
        telemetry.update();

        while (!imu.isGyroCalibrated()) { }

        telemetry.addData("GYRO", "Ready!");
        telemetry.addData("Calibration status", imu.getCalibrationStatus().toString());
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

    private void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    private double getAngle() {

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;
        if (deltaAngle < -180) {
            deltaAngle += 360;
        } else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }

        globalAngle += deltaAngle;
        lastAngles = angles;

        return globalAngle;
    }
}
