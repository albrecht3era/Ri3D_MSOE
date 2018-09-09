import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftSystem {

    private Intake intake = null;
    private DcMotor lift_left = null;
    private DcMotor lift_right = null;

    public LiftSystem(DcMotor lift_left, DcMotor lift_right, Intake intake) {
        this.lift_left = lift_left;
        this.lift_right = lift_right;
        this.intake = intake;
    }

    public void lift(double power) {
        lift_left.setPower(power);
        lift_right.setPower(power);
    }

    public void automatedLiftAndRelease() {
        lift_left.setPower(.75);
        lift_right.setPower(.75);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lift_left.setPower(0);
        lift_right.setPower(0);
        intake.spin(-1.0);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        intake.spin(0.0);
    }
}
