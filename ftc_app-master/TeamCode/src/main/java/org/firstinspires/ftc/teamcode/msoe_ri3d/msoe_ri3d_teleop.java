package org.firstinspires.ftc.teamcode.msoe_ri3d;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;

@TeleOp(name="Basic: ServoTest", group="Iterative OpMode")
public class msoe_ri3d_teleop extends BasicOpMode_Iterative {

    private CRServo leftMotor = null;
    private CRServo rightMotor = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        leftMotor = hardwareMap.get(CRServo.class, "left_motor");
        rightMotor = hardwareMap.get(CRServo.class, "right_motor");
        /*
        leftMotor.setDirection(CRServo.Direction.REVERSE);
        rightMotor.setDirection(CRServo.Direction.FORWARD);

        telemetry.addData("Status", "Initializing Complete");
        */
    }

    @Override
    public void loop() {
        /*
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.left_stick_y);
        telemetry.addData("Left Motor Status", leftMotor.getPower());
        telemetry.addData("Right Motor Status", rightMotor.getPower());
        telemetry.addData("Gamepad Input", gamepad1.left_stick_y);
        */
    }
}
