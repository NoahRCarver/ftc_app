

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class TestOp extends OpMode {

    DcMotor frMotor;
    DcMotor flMotor;
    DcMotor brMotor;
    DcMotor blMotor;

    /**
     * Puppet Constructor
     */
    public TestOp() {

    }

    @Override
    public void init() {
        frMotor = hardwareMap.dcMotor.get("frmotor");
        flMotor = hardwareMap.dcMotor.get("flmotor");
        brMotor = hardwareMap.dcMotor.get("brmotor");
        blMotor = hardwareMap.dcMotor.get("blmotor");
    }

    /*
     * Code that runs repeatedly when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init_loop()
     */
    @Override
    public void init_loop() {

    }

    /*
     * This method will be called repeatedly in a loop
     * 
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {

        frMotor.setPower(-gamepad1.right_stick_y);
        brMotor.setPower(-gamepad1.right_stick_y);

        flMotor.setPower(gamepad1.left_stick_y);
        blMotor.setPower(gamepad1.left_stick_y);
    }

    /*
     * Code to run when the op mode is first disabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {

    }
}

