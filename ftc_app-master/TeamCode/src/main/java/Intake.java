import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {

    private CRServo left_servo = null;
    private CRServo right_servo = null;

    public Intake(CRServo left_servo, CRServo right_servo) {
        this.left_servo = left_servo;
        this.right_servo = right_servo;
    }

    public void spin(double speed) {
        left_servo.setPower(speed);
        right_servo.setPower(speed);
    }
}
