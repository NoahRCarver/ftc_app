

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class TankAutoOp extends OpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    /**
     * Puppet Constructor
     */
    public TankAutoOp() {

    }


    @Override
    public void init(){
        leftMotor = hardwareMap.dcMotor.get("lmotor");
        rightMotor = hardwareMap.dcMotor.get("rmotor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        // set the mode
        // Nxt devices start up in "write" mode by default, so no need to switch device modes here.
        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);


    }

    /*
     *Code that runs after init, but before loop starts
     */
    @Override
    public void start(){

        try {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            wait(250);
            rightMotor.setPower(0);
            wait(250);
            leftMotor.setPower(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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

    }


    /*
     * Code to run when the op mode is first disabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {

    }
}