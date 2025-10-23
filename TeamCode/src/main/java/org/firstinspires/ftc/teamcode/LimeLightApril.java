/*Pose3d is less common in standard FTC programming but is available through WPILib
(FIRST's official Java library, often adapted for FTC via FTCLib or similar). I
t extends Pose2d to 3D space, adding a pitch and roll to handle scenarios with elevation changes,
 though FTC fields are typically flat (z=0). It's primarily used for advanced vision systems (
 e.g., AprilTag pose estimation with PhotonVision) where depth or tilt matters.
 Key Components:
Translation3d: The (x, y, z) position, with z for height.
Rotation3d: Full 3D orientation, including yaw (heading), pitch, and roll.
Representation:
A Pose3d can be thought of as a vector: [x, y, z, yaw, pitch, roll].
Common Uses in FTC:
3D Vision Pose Estimation: When using cameras to detect tags at varying heights
(e.g., in PhotonVision's PhotonPoseEstimator), it provides a 3D estimate that can be projected to
2D for the robot base.

Rare in Pure FTC: Most teams stick to 2D since robots don't fly or climb vertically in
 a way that requires full 3D tracking during matches. - GROK
 */



package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.mechanisms.TestBenchIMU;
import org.firstinspires.ftc.teamcode.mechanisms.testBench;

@Autonomous
public class LimeLightApril extends OpMode {
    private Limelight3A limelight3A;

    TestBenchIMU bench = new TestBenchIMU();

    private double distance;


    @Override
    public void init() {
        bench.init(hardwareMap);
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(8); //Pipeline chosen in web interface
    }

    @Override
    public void start() {
        limelight3A.start(); //Depending on how long it takes to start, we can change it
    }

    @Override
    public void loop() {

        YawPitchRollAngles orientation = bench.getOrientation();
        limelight3A.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));


        LLResult llResult  = limelight3A.getLatestResult();
        if(llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            distance = getDistanceFromTag(llResult.getTa());
            telemetry.addData("Target X" , llResult.getTx());
            telemetry.addData("Target Area" , llResult.getTa());
            telemetry.addData("Botpose" , botpose.toString());

        }
    }

    public double getDistanceFromTag(double ta) {
        //double scale = //input scale
        //double distance = (scale / ta);
        return distance;
    }
}
