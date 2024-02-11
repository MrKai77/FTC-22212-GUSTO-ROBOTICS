package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.teamcode.Shared.Vision.TeamPropProcessorRed;

@Disabled
@Autonomous
public class TeamPropDetector extends LinearOpMode {

    private TeamPropProcessorRed teamPropProcessor;
    private VisionPortal portal;

    @Override
    public void runOpMode() throws InterruptedException {

        teamPropProcessor = new TeamPropProcessorRed();
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(teamPropProcessor)
                .build();


        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("Prop Position", teamPropProcessor.getPropPosition());
            telemetry.update();

            sleep(10);
        }

    }
}