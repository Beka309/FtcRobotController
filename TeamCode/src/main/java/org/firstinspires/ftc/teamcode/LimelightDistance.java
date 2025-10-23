package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.mechanisms.TestBenchIMU;
@TeleOp
public class LimelightDistance extends OpMode {

    private Limelight3A limelight3A;

    TestBenchIMU bench = new TestBenchIMU();

    private double distance;

    @Override
    public void init() {
        bench.init(hardwareMap);
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(8);
        telemetry.addLine("Hello World");
    }

    @Override
    public void start() {
        limelight3A.start();
    }

    @Override
    public void loop() {
        YawPitchRollAngles orientation = bench.getOrientation();
        limelight3A.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));

        LLResult llResult = limelight3A.getLatestResult();

        if(llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            //telemetry.addData("Calculated Distance", distance);
            telemetry.addData("Target X", llResult.getTx());
            telemetry.addData("Target A", llResult.getTa());
            telemetry.addData("Botpose", botpose.toString());




        }
    }
}
