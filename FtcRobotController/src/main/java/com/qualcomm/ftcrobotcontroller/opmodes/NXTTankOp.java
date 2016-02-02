

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
public class NXTTankOp extends OpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;
    
    //BaseDriveMotorControl = driveScheme;

    /**
     * Puppet Constructor
     */
    public NXTTankOp() {

    }


    @Override
    public void init(){
        leftMotor = hardwareMap.dcMotor.get("lmotor");
        rightMotor = hardwareMap.dcMotor.get("rmotor");



    }

    /*
     *Code that runs after init, but before loop starts
     */
    @Override
    public void start(){



    }




    /*
     * Code that runs repeatedly when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init_loop()
     */
    @Override
    public void init_loop() {

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        // set the mode
        // Nxt devices start up in "write" mode by default, so no need to switch device modes here.
        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    /*
     * This method will be called repeatedly in a loop
     * 
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {
         /*
          * Gamepad 1
          * Controls: tank
          */
        // Joystick Drive
        // note that if y equal -1 then joystick is pushed all of the way forward.
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;
        //float left = 1;
        //float right = 1;

        // clip the right/left values so that the values never exceed +/- 1
        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        left = (float)scaleInput(left);
        right =  (float)scaleInput(right);

        // write the values to the motors
        rightMotor.setPower(right);
        leftMotor.setPower(left);
    }


    /*
     * Code to run when the op mode is first disabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {

    }

    /*
     * This method scales the joystick input so for low joystick values, the 
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        int mult = 12;
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        if(gamepad1.left_bumper){
            mult = 16;
        }


        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * mult);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}