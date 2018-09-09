import com.qualcomm.robotcore.hardware.DcMotor;

abstract class Turn {

    DcMotor left_front = null;
    DcMotor left_back = null;
    DcMotor right_front = null;
    DcMotor right_back = null;

    public Turn(DcMotor left_front, DcMotor left_back, DcMotor right_front, DcMotor right_back) {
        this.left_front = left_front;
        this.left_back = left_back;
        this.right_front = right_front;
        this.right_back = right_back;
    }

    abstract void move(double x, double y, double negative_multiplier);
}
