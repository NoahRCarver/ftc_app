package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ScienceOlympiadAuto extends LinearOpMode {
    DcMotor left, right;
    BaseDriveMotorControl driverino;

    public ScienceOlympiadAuto(){}

    @Override
    public void runOpMode() throws InterruptedException{
        left = hardwareMap.dcMotor.get("left"); //port 1
        right = hardwareMap.dcMotor.get("right"); //port 2

        driverino = new BaseDriveMotorControl(left, right);

        waitForStart();

        driverino.measuredDrive(5, 5);//should go 2 meters
    }

}
