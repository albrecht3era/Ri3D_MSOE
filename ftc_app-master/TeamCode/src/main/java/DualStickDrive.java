import com.qualcomm.robotcore.hardware.DcMotor;

public class DualStickDrive extends Turn {

    public DualStickDrive(DcMotor left_front, DcMotor left_back, DcMotor right_front, DcMotor right_back) {
        super(left_front, left_back, right_front, right_back);
    }

    @Override
    void move(double left, double right, double negative_multiplier) {
        left_front.setPower(normalize_speed(left));
        left_back.setPower(normalize_speed(left));
        right_back.setPower(normalize_speed(right));
        right_front.setPower(normalize_speed(right));
    }

    double normalize_speed(double speed) {
        return Math.log(Math.abs(speed)*20)/Math.log(20)*speed;
        //return (speed > 0.7 ? speed/2.0 : speed);
        //log(value*20)/log(20) * value
    }
}
