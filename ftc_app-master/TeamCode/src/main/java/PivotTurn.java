import com.qualcomm.robotcore.hardware.DcMotor;

public class PivotTurn extends Turn {

    private static final double MIN_LEFT_TURN_VALUE = -0.1;
    private static final double MIN_RIGHT_TURN_VALUE = 0.1;


    public PivotTurn(DcMotor left_front, DcMotor left_back, DcMotor right_front, DcMotor right_back) {
        super(left_front, left_back, right_front, right_back);
    }

    @Override
    void move(double x, double y, double negative_multiplier) {
        if (MIN_LEFT_TURN_VALUE > y || y > MIN_RIGHT_TURN_VALUE) {
            if (y < MIN_LEFT_TURN_VALUE) {
                left_front.setPower(0);
                left_back.setPower(0);
                right_front.setPower(-y * negative_multiplier);
                right_back.setPower(-y * negative_multiplier);
            } else if (y > MIN_RIGHT_TURN_VALUE) {
                right_front.setPower(0);
                right_back.setPower(0);
                left_back.setPower(y * negative_multiplier);
                left_front.setPower(y * negative_multiplier);
            }
        } else {
            left_front.setPower(x);
            left_back.setPower(x);
            right_back.setPower(x);
            right_front.setPower(x);
        }
    }
}

