package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;

@TeleOp(name = "Rover Ruckus Teleop")
public class RoverRuckusTeleop extends BasicOpMode_Iterative{

    private DcMotor left_front = null;
    private DcMotor left_back = null;
    private DcMotor right_front = null;
    private DcMotor right_back = null;
    private CRServo left_servo = null;
    private CRServo right_servo = null;
    private DcMotor lift_left = null;
    private DcMotor lift_right = null;

    private static final double MIN_LEFT_TURN_VALUE = -0.1;
    private static final double MIN_RIGHT_TURN_VALUE = 0.1;
    private static final double NEGATIVE_MULTIPLIER = -1.0;
    private static final double POSITIVE_MULTIPLIER = 1.0;

    @Override
    public void init() {
        //left drive motors
        left_front = hardwareMap.get(DcMotor.class, "left_front");
        left_back = hardwareMap.get(DcMotor.class, "left_back");

        //right drive motors
        right_front = hardwareMap.get(DcMotor.class, "right_front");
        right_back = hardwareMap.get(DcMotor.class, "right_back");

        //continuous VEX servos
        left_servo = hardwareMap.get(CRServo.class, "left_servo");
        right_servo = hardwareMap.get(CRServo.class, "right_servo");

        //lift motors
        lift_left = hardwareMap.get(DcMotor.class, "lift_left");
        lift_right = hardwareMap.get(DcMotor.class, "lift_right");

        //reverse left motors
        left_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotor.Direction.REVERSE);
        left_servo.setDirection(CRServo.Direction.REVERSE);
        lift_left.setDirection(DcMotor.Direction.REVERSE);
    }

    public void assign_wheel_speed(double x,  double y, double negative_multiplier) {
        if (MIN_LEFT_TURN_VALUE > x || x > MIN_RIGHT_TURN_VALUE) {
            if (x < MIN_LEFT_TURN_VALUE) {
                left_front.setPower(0);
                left_back.setPower(0);
                right_front.setPower(-x * negative_multiplier);
                right_back.setPower(-x * negative_multiplier);
            } else if (x > MIN_RIGHT_TURN_VALUE) {
                right_front.setPower(0);
                right_back.setPower(0);
                left_back.setPower(x * negative_multiplier);
                left_front.setPower(x * negative_multiplier);
            }
        } else {
        left_front.setPower(y);
        left_back.setPower(y);
        right_back.setPower(y);
        right_front.setPower(y);
        }
    }

    public double check_negative(double gamepad_val) {
        if(gamepad_val < 0.0) {
            return NEGATIVE_MULTIPLIER;
        } else {
            return POSITIVE_MULTIPLIER;
        }
    }

    public void assign_lift_speed(double speed) {
        lift_left.setPower(speed);
        lift_right.setPower(speed);
    }

    public void start_stop_collection(float forward, float backward) {
        if(forward > 0.1) {
            left_servo.setPower(1.0);
            right_servo.setPower(1.0);
        } else if (backward > 0.1) {
            left_servo.setPower(-1.0);
            right_servo.setPower(-1.0);
        } else {
            left_servo.setPower(0.0);
            right_servo.setPower(0.0);
        }
    }

    @Override
    public void loop() {
        assign_wheel_speed(gamepad1.left_stick_x, gamepad1.left_stick_y, check_negative(gamepad1.left_stick_y));
        assign_lift_speed(gamepad1.right_stick_y);
        start_stop_collection(gamepad1.right_trigger, gamepad1.left_trigger);
    }
}
