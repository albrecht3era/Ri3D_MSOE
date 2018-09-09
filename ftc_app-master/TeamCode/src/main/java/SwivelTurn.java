import com.qualcomm.robotcore.hardware.DcMotor;

public class SwivelTurn extends Turn{

    public SwivelTurn(DcMotor left_front, DcMotor left_back, DcMotor right_front, DcMotor right_back) {
        super(left_front, left_back, right_front, right_back);
    }

    @Override
    void move(double x, double y, double negative_multiplier) {
        left_front.setPower((y+x)*negative_multiplier);
        left_back.setPower((y+x)*negative_multiplier);
        right_front.setPower((y-x)*negative_multiplier);
        right_back.setPower((y-x)*negative_multiplier);
    }
}
