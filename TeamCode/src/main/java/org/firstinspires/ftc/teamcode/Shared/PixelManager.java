package org.firstinspires.ftc.teamcode.Shared;

import androidx.annotation.NonNull;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class PixelManager {
    private final Motor liftMotor;
    private final SimpleServo clawServo;

    public PixelManager(@NonNull HardwareMap hMap) {
        liftMotor = new Motor(hMap, "LiftMotor");
        clawServo = new SimpleServo(hMap, "ClawServo", 0, 360, AngleUnit.DEGREES);

        liftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        liftMotor.resetEncoder();
    }

    public void closeClaw() {
        clawServo.setPosition(0.25);
    }

    public void openClaw() {
        clawServo.setPosition(0);
    }

    public void setLift(double speed) {
        liftMotor.set(speed);
    }

    public int getLiftPosition() {
        return liftMotor.getCurrentPosition();
    }
    public void resetLiftEncoder() {
        liftMotor.resetEncoder();
    }
}
