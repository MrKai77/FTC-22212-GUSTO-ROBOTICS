package org.firstinspires.ftc.teamcode.Shared;

import androidx.annotation.NonNull;
import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    final private Motor m_right, m_left, m_slide;
    public HDrive drive;

    public DriveTrain(@NonNull HardwareMap hMap) {
        m_right = new Motor(hMap, "RightMotor");
        m_left = new Motor(hMap, "LeftMotor");
        m_slide = new Motor(hMap, "SlideMotor");

        m_right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_slide.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        m_left.setInverted(true);
        m_slide.setInverted(true);

        // These are in radians
        drive = new HDrive(m_left, m_right, m_slide, 0, 0, Math.PI/2);
    }

    public void easyDrive(double y, double x, double rotation) {
        double leftPower = y;
        double rightPower = y;
        double hPower = x;

        leftPower -= rotation - hPower / 10;
        rightPower += rotation - hPower / 10;

        m_left.set(leftPower);
        m_right.set(rightPower);
        m_slide.set(hPower);
    }
}
