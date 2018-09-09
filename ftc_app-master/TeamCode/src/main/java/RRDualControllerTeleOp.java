import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;

@TeleOp(name = "RR Dual Controller")
public class RRDualControllerTeleOp extends BasicOpMode_Iterative {

    private DcMotor left_front = null;
    private DcMotor left_back = null;
    private DcMotor right_front = null;
    private DcMotor right_back = null;
    private CRServo left_servo = null;
    private CRServo right_servo = null;
    private DcMotor lift_left = null;
    private DcMotor lift_right = null;
    private Turn turn = null;
    private LiftSystem lift = null;
    private Intake intake = null;

    private static final double NEGATIVE_MULTIPLIER = -1.0;
    private static final double POSITIVE_MULTIPLIER = 1.0;

    public double check_negative(double gamepad_val) {
        if(gamepad_val < 0.0) {
            return NEGATIVE_MULTIPLIER;
        } else {
            return POSITIVE_MULTIPLIER;
        }
    }

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

        //turning system
        turn = new PivotTurn(left_front, left_back, right_front, right_back);

        //mineral collection system
        intake = new Intake(left_servo, right_servo);

        //lifting sytem
        lift = new LiftSystem(lift_left, lift_right, intake);
    }

    @Override
    public void loop() {
        if(gamepad1.x) {
            turn = new PivotTurn(left_front, left_back, right_front, right_back);
        } else if(gamepad1.y) {
            turn = new SwivelTurn(left_front, left_back, right_front, right_back);
        } else if(gamepad1.b) {
            turn = new TankTurn(left_front, left_back, right_front, right_back);
        }
        turn.move(gamepad1.left_stick_x, gamepad1.left_stick_y, check_negative(gamepad1.left_stick_y));
        if(gamepad2.a){
            intake.spin(1.0);
        } else if(gamepad2.b) {
            intake.spin(-1.0);
        } else if(gamepad2.y) {
            lift.automatedLiftAndRelease();
        } else {
            intake.spin(0.0);
        }
        lift.lift(gamepad2.left_stick_y);
    }
}
