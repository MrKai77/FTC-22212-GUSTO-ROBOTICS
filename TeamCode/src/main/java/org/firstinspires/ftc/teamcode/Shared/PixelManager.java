package org.firstinspires.ftc.teamcode.Shared;

import androidx.annotation.NonNull;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

public class PixelManager {
    final private Motor m_intake, m_arm;

    final public int FEEDING_POSITION = 0;
    final public int RESTING_POSITION = 100;
    final public  int SCORING_POSITION = 1000;

    public PixelManager(@NonNull HardwareMap hMap) {
        m_intake = new Motor(hMap, "IntakeMotor");
        m_arm = new Motor(hMap, "ArmMotor");

        m_arm.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_arm.setInverted(true);
    }

    public void setIntakePower(double power) {
        m_intake.set(power);
    }

    public void setArmPower(double power) {
        m_arm.set(power / 2);
    }

    public void setArmPosition(int targetPosition) {
        m_arm.setTargetPosition(targetPosition);

        m_arm.setRunMode(Motor.RunMode.PositionControl);
        m_arm.set(0.8);

        while (m_arm.motor.isBusy()) {
            telemetry.addData("Arm Motor:", m_arm.getCurrentPosition());
            telemetry.update();
        }

        m_arm.set(0);
    }
}