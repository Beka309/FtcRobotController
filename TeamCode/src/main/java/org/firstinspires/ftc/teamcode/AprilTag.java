package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import java.util.List;

@Autonomous
public class AprilTag extends LinearOpMode {

    private Limelight3A limelight;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8); // Switch to AprilTag pipeline
        limelight.start();

        final int TARGET_TAG_ID = 20; // Blue goal (24 for red)
        final double TARGET_DISTANCE = 36.0; // Calibrate this!

        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                // Get AprilTag results
                List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();

                for (LLResultTypes.FiducialResult fiducial : fiducials) {
                    if (fiducial.getFiducialId() == TARGET_TAG_ID) {
                        // Found our target tag
                        double tx = fiducial.getTargetXDegrees(); // Horizontal offset
                        double ty = fiducial.getTargetYDegrees(); // Vertical offset
                        double ta = fiducial.getTargetArea(); // Size (distance proxy)

                        telemetry.addData("Tag Found", TARGET_TAG_ID);
                        telemetry.addData("X Offset", tx);
                        telemetry.addData("Y Offset", ty);
                        telemetry.update();

                        // Navigate and shoot (implement based on your robot)
                        // alignToTag(tx, ty);
                        // fireLauncher();
                        break;
                    }
                }
            }
        }
    }
}
