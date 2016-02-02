

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * AutoOpMode
 * 
 * drives robot in a square
 */
public class AutoOp extends LinearOpMode {
    
    DcMotor FRM, BRM;
    DcMotor FLM, BLM;
    BaseDriveMotorControl driveScheme;
    Servo  CA; //BTN;  Right Paddle, Left paddle, Climber Arm, Button pusher.

    public AutoOp() {/*who needs constructors?*/}
    
    @Override
    public void runOpMode() throws InterruptedException{



        FRM = hardwareMap.dcMotor.get("fr");
        BRM = hardwareMap.dcMotor.get("br");
        FLM = hardwareMap.dcMotor.get("fl");
        BLM = hardwareMap.dcMotor.get("bl");


        CA = hardwareMap.servo.get("ca"); //port 4
        // BTN = hardwareMap.servo.get("btn");

        //init driveScheme
        driveScheme = new BaseDriveMotorControl(FRM, FLM);
        
        
        //////////////// END INIT STEP /////////////////
        waitForStart();


        driveScheme.measuredDrive(110, 110);
       /*
        while(FRM.getTargetPosition()>FRM.getCurrentPosition()){
            sleep(001);
        }
        driveScheme.measuredDrive(6.67588, -6.67588);
        while(FRM.getTargetPosition()>FRM.getCurrentPosition()){
            sleep(001);
        }
        driveScheme.measuredDrive(12.5, 12.5);
        while(FRM.getTargetPosition()>FRM.getCurrentPosition()){
            sleep(001);
        }
       // CA.setPosition(1.0);
        while (FRM.getTargetPosition()>FRM.getCurrentPosition()){
            sleep(001);
        }
        //CA.setPosition(0);
        */
        
    }
    

}