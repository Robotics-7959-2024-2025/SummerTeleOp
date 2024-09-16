package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Summer OpMode")
public class SummerOpMode extends LinearOpMode {

    // Declare OpMode members.
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private float maxVelocity = 1.0f;

    private int ctrlPow=3;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            /*if (gamepad1.left_stick_x < 0 && !(gamepad1.left_stick_x > 0)) {

                telemetry.addData("Strafing left", "true");
                telemetry.update();

                //motor power can be between -5 and 5.
                frontRight.setPower(0.5);
                frontLeft.setPower(-0.5);
                backRight.setPower(0.5);
                backLeft.setPower(-0.5);
            }
            //STRAFE RIGHT
            // the right wheels need to go outwards and the left wheels need to go inwards
            else if (gamepad1.left_stick_x > 0 && !(gamepad1.left_stick_x < 0)) {
                telemetry.addData("Strafing right", "true");
                telemetry.update();
                frontLeft.setPower(0.5);
                frontRight.setPower(-0.5);
                backLeft.setPower(0.5);
                backRight.setPower(-0.5);
            }
            //robot moving FORWARD (completely)
            //gamepad 1 is positive and not negative
            else if (gamepad1.left_stick_y < 0 && !(gamepad1.left_stick_y > 0)) {
                //currently the robot has the front wheels moving inward and the back wheels going forward
                telemetry.addData("Moving forward", "true");
                telemetry.update();
                frontRight.setPower(-0.5);
                frontLeft.setPower(-0.5);
                backRight.setPower(-0.5);
                backLeft.setPower(-0.5);
            }*/
            frontRight.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y+gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            frontLeft.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y-gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            backRight.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y-gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            backLeft.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y+gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));

            frontRight.setPower(gamepad1.right_stick_x*1);
            frontLeft.setPower(gamepad1.right_stick_x*-1);
            backRight.setPower(gamepad1.right_stick_x*1);
            backLeft.setPower(gamepad1.right_stick_x*-1);
        }
    }
}
