package org.firstinspires.ftc.teamcode.msoe_ri3d;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;

@TeleOp(name="Basic: DriveTrainTest", group="Iterative OpMode")
public class drivetraintest extends BasicOpMode_Iterative {

    private DcMotor left_front = null;
    private DcMotor left_back = null;
    private DcMotor right_front = null;
    private DcMotor right_back = null;

    @Override
    public void init() {
        left_front = hardwareMap.get(DcMotor.class, "left_front");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_front = hardwareMap.get(DcMotor.class, "right_front");
        right_back = hardwareMap.get(DcMotor.class, "right_back");

        right_front.setDirection(DcMotor.Direction.FORWARD);
        right_back.setDirection(DcMotor.Direction.FORWARD);
        left_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        left_front.setPower(gamepad1.left_stick_y);
        left_back.setPower(gamepad1.left_stick_y);
        right_front.setPower(gamepad1.left_stick_y);
        right_back.setPower(gamepad1.left_stick_y);

        telemetry.addData("Status", gamepad1.left_stick_y);
    }
}
