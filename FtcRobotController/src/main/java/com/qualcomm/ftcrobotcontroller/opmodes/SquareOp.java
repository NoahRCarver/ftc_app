

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
public class SquareOp extends OpMode {


    DcMotor rightMotor1, rightMotor2;
    DcMotor leftMotor1, leftMotor2;
    DcMotorController.DeviceMode devMode;
    DcMotorController leftController, rightController;
    int numOpLoops = 1;

    /**
     * Puppet Constructor
     */
    public SquareOp() {

    }

    @Override
    public void init() {
        /*
         * Use the hardwareMap to get the dc motors and servos by name. Note
         * that the names of the devices must match the names used when you
         * configured your robot and created the configuration file.
         */
        
        /*
         * For the Robot we assume the following,
         *   There are four motors 2 "Right_Motors" and 2 "Left_Motors"
         *   "left_Motor1" and "left_Motor2" is on the Left side of the bot.
         *   "right_Motors" is on the Front side of the bot.
         */
        leftMotor1 = hardwareMap.dcMotor.get("lmotor1");
        leftMotor2 = hardwareMap.dcMotor.get("lmotor2");
        rightMotor1 = hardwareMap.dcMotor.get("rmotor1");
        rightMotor2 = hardwareMap.dcMotor.get("rmotor2");

       rightController = hardwareMap.dcMotorController.get("rmotors");
       leftController = hardwareMap.dcMotorController.get("lmotors");
    }

    /*
     * Code that runs repeatedly when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init_loop()
     */
    @Override
    public void init_loop() {

        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        // set the mode
        // Nxt devices start up in "write" mode by default, so no need to switch device modes here.
        leftMotor1.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        leftMotor2.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor1.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor2.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    /*
     * This method will be called repeatedly in a loop
     * 
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {

        // The op mode should only use "write" methods (setPower, setChannelMode, etc) while in
        // WRITE_ONLY mode or SWITCHING_TO_WRITE_MODE
        // if (allowedToWrite()) {
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
        //left = (float)scaleInput(left);
        // right =  (float)scaleInput(right);

        // write the values to the motors
        rightMotor1.setPower(right);
        rightMotor2.setPower(right);
        leftMotor1.setPower(left);
        leftMotor2.setPower(left);
    }

    // To read any values from the NXT controllers, we need to switch into READ_ONLY mode.
    // It takes time for the hardware to switch, so you can't switch modes within one loop of the
    // op mode. Every 17th loop, this op mode switches to READ_ONLY mode, and gets the current power.
    //if (numOpLoops % 17 == 0){

    // Note: If you are using the NxtDcMotorController, you need to switch into "read" mode
    // before doing a read, and into "write" mode before doing a write. This is because
    // the NxtDcMotorController is on the I2C interface, and can only do one at a time. If you are
    // using the USBDcMotorController, there is no need to switch, because USB can handle reads
    // and writes without changing modes. The NxtDcMotorControllers start up in "write" mode.
    // This method does nothing on USB devices, but is needed on Nxt devices.

    // rightController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
    //leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

    //}

    // Every 17 loops, switch to read mode so we can read data from the NXT device.
    // Only necessary on NXT devices.
    // if ((rightController.getMotorControllerDeviceMode() == DcMotorController.DeviceMode.READ_ONLY) && (leftController.getMotorControllerDeviceMode() == DcMotorController.DeviceMode.READ_ONLY)) {

    // Update the reads after some loops, when the command has successfully propagated through.
    // telemetry.addData("Text", "free flow text");
    //  telemetry.addData("left motor", leftMotor1.getPower());
    //telemetry.addData("right motor", rightMotor1.getPower());
    //telemetry.addData("RunMode: ", leftMotor1.getChannelMode().toString());

    // Only needed on Nxt devices, but not on USB devices
    // rightController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
    //  leftController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

    // Reset the loop
    // numOpLoops = 0;
    // }

    // Update the current devMode
    // devMode = rightController.getMotorControllerDeviceMode();
    // numOpLoops++;
    //}

    // If the device is in either of these two modes, the op mode is allowed to write to the HW.
    //private boolean allowedToWrite(){
    // return (devMode == DcMotorController.DeviceMode.WRITE_ONLY);
    //}

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
    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}

