import com.qualcomm.robotcore.hardware.DcMotor;

public class TankTurn extends Turn {

    private static final double MIN_LEFT_TURN_VALUE = -0.1;
    private static final double MIN_RIGHT_TURN_VALUE = 0.1;

    public TankTurn(DcMotor left_front, DcMotor left_back, DcMotor right_front, DcMotor right_back) {
        super(left_front, left_back, right_front, right_back);
    }

    @Override
    void move(double x, double y, double negative_multiplier) {
        if (MIN_LEFT_TURN_VALUE > x || x > MIN_RIGHT_TURN_VALUE) {
            if (x < MIN_LEFT_TURN_VALUE) {
                left_front.setPower(x * negative_multiplier);
                left_back.setPower(x * negative_multiplier);
                right_front.setPower(-x * negative_multiplier);
                right_back.setPower(-x * negative_multiplier);
            } else if (y > MIN_RIGHT_TURN_VALUE) {
                right_front.setPower(-x * negative_multiplier);
                right_back.setPower(-x * negative_multiplier);
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
}
