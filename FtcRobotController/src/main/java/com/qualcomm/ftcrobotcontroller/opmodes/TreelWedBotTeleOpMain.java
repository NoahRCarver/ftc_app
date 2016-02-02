
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class TreelWedBotTeleOpMain extends OpMode {

    DcMotor FRM, BRM; //Front Right Motor, Back Right Motor
    DcMotor FLM, BLM; //Front Left Motor, Back Left Motor
    DcMotor TDM, TIM; //Tread Delivery Motor, Tread Intake Motor
    Servo RP, LP, CA, SA; //BTN;  Right Paddle, Left paddle, Climber Arm, Button pusher.
    boolean RPaddleIsOpen=false;
    boolean LPaddleIsOpen=false;
    boolean ArmIsUp=false;
    boolean ArmIsOut = false;

    // position of the DropMen servo.
    final double RPClosedPosition = 0.05, LPClosedPosition = .80, CAClosedPosition = 1.0, SAClosedPosition = .5;// BTNPosition = 0.0;
    final double RPOpenPosition = 0.55, LPOpenPosition = .40, CAOpenPosition = 0.0, SAOpenPosition = 1.0;


    BaseDriveMotorControl driveScheme;

    public TreelWedBotTeleOpMain() {}


    @Override
    public void init(){
        /**
         * Hardware Map:
         * fr - front right drive motor
         * fl - front left drive motor
         * br - back right drive motor
         * bl - back left drive motor
         */
        FRM = hardwareMap.dcMotor.get("fr");
        BRM = hardwareMap.dcMotor.get("br");
        FLM = hardwareMap.dcMotor.get("fl");
        BLM = hardwareMap.dcMotor.get("bl");

        TDM = hardwareMap.dcMotor.get("out");
        TIM = hardwareMap.dcMotor.get("in");

        RP = hardwareMap.servo.get("rp"); //port 3
        LP = hardwareMap.servo.get("lp"); //port 1
        CA = hardwareMap.servo.get("ca"); //port 2
        SA = hardwareMap.servo.get("sa"); //port 4
       // BTN = hardwareMap.servo.get("btn");
        
        //init driveScheme
        driveScheme = new BaseDriveMotorControl(FRM, FLM);
        
        TDM.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        TIM.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    }

    @Override
    public void start(){}

    @Override
    public void init_loop(){}

    @Override
    public void loop() {
        driveScheme.tankDrive(gamepad1);

        if(gamepad2.dpad_up){
            TIM.setPower(1);
        }else{
            TIM.setPower(0);
        }

        if(gamepad2.dpad_left){
            TDM.setPower(.25);
        }else if(gamepad2.dpad_right){
            TDM.setPower(-.25);
        }else{
            TDM.setPower(0);
        }

        if (gamepad2.a) {
            if(!RPaddleIsOpen) {
                RPaddleIsOpen = true;
                RP.setPosition(RPOpenPosition);
            }

        }else{
            if(RPaddleIsOpen) {
                RPaddleIsOpen = false;
                RP.setPosition(RPClosedPosition);
            }

        }
        if (gamepad2.y) {
            if(!LPaddleIsOpen) {
                LPaddleIsOpen = true;
                LP.setPosition(LPOpenPosition);
            }

        }else{
            if(LPaddleIsOpen) {
                LPaddleIsOpen = false;
                LP.setPosition(LPClosedPosition);
            }

        }


        if (gamepad2.b) {
            if(!ArmIsUp) {
                ArmIsUp = true;
                CA.setPosition(CAOpenPosition);            }
        }
        else{
            if(ArmIsUp) {
                ArmIsUp = false;
                CA.setPosition(CAClosedPosition);
            }
        }




      if (gamepad2.x){
          if(!ArmIsOut) {
              ArmIsOut = true;
              SA.setPosition(SAOpenPosition);
          }
        }else{
          if(ArmIsOut){
              ArmIsOut = false;
              SA.setPosition(SAClosedPosition);
          }

      }


    }

    @Override
    public void stop() {

    }
}