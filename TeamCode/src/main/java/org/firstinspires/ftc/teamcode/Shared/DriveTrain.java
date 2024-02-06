package org.firstinspires.ftc.teamcode.Shared;

import androidx.annotation.NonNull;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.drivebase.HDrive;
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
}
