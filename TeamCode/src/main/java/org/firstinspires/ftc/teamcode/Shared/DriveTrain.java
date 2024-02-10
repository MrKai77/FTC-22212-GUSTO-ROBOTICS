package org.firstinspires.ftc.teamcode.Shared;

import androidx.annotation.NonNull;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    final private Motor m_right, m_left;
    final public DifferentialDrive m_drive;

    public DriveTrain(@NonNull HardwareMap hMap) {
        m_right = new Motor(hMap, "RightMotor");
        m_left = new Motor(hMap, "LeftMotor");

        m_right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        m_left.setInverted(true);

        // These are in radians
        m_drive = new DifferentialDrive(m_left, m_right);
    }

    public void arcadeDrive(double speed, double rotation) {
        m_drive.arcadeDrive(speed / 2, rotation / 2);
    }

    public void driveTo(double rightMeters, double leftMeters, double speed) {
        final double GOBILDA_TICKS_PER_METER = m_right.motor.getMotorType().getTicksPerRev() * Math.PI * 0.11;
        m_right.setRunMode(Motor.RunMode.PositionControl);
        m_left.setRunMode(Motor.RunMode.PositionControl);
        m_right.resetEncoder();
        m_left.resetEncoder();

        final int rightTarget = (int) (rightMeters * GOBILDA_TICKS_PER_METER);
        final int leftTarget = (int) (leftMeters * GOBILDA_TICKS_PER_METER);

        m_right.setTargetPosition(rightTarget);
        m_left.setTargetPosition(leftTarget);

        m_right.set(speed);
        m_left.set(speed);

        while (
                -Math.abs(rightTarget) < m_right.getCurrentPosition() && m_right.getCurrentPosition() < Math.abs(rightTarget) &&
                -Math.abs(leftTarget) < m_left.getCurrentPosition() && m_left.getCurrentPosition() < Math.abs(leftTarget)
        ) {
            System.out.println("TEST");
        }

        m_drive.stop();
    }
}
