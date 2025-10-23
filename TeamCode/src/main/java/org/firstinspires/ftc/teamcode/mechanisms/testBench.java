package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class testBench {
    private DcMotor motor;
    private DcMotor motor1;
    private double tickPerRev;
    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "m");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tickPerRev = motor.getMotorType().getTicksPerRev();

        motor1 = hwMap.get(DcMotor.class, "m1");
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tickPerRev = motor1.getMotorType().getTicksPerRev();


    }

    public void setMotorSpeed(double speed) {
        motor.setPower(speed);
        motor1.setPower(-speed);
    }
    public double getMotorRevs() {
        return motor.getCurrentPosition() / tickPerRev;
    }
    public double getMotorRevs1() {
        return motor.getCurrentPosition() / tickPerRev;
    }
}
