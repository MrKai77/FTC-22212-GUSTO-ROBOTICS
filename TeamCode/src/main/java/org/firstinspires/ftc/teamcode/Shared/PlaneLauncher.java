package org.firstinspires.ftc.teamcode.Shared;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

// Looks useless but trust me, it's useful haha
public class PlaneLauncher {
    final private Servo m_planeLauncher;

    final private double UNLAUNCHED_POSITION = 0;
    final private double LAUNCHED_POSITION = 1;

    public PlaneLauncher(@NonNull HardwareMap hMap) {
        m_planeLauncher = hMap.get(Servo.class, "PlaneLauncher");
        m_planeLauncher.setPosition(UNLAUNCHED_POSITION);
    }

    public void liftoff() {
        m_planeLauncher.setPosition(LAUNCHED_POSITION);
    }
}
