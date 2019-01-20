/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;


public class DriveSubsystem extends Subsystem {

    //Includes Chassis and Potential Piston Wheels

    WPI_TalonSRX fr, fl, br, bl;
    //SpeedControllerGroup left, right;
    DifferentialDrive drive;

    public DriveSubsystem() {
        fr = new WPI_TalonSRX(RobotMap.frontRight.get());
        fl = new WPI_TalonSRX(RobotMap.frontLeft.get());
        br = new WPI_TalonSRX(RobotMap.backRight.get());
        bl = new WPI_TalonSRX(RobotMap.backLeft.get());

        //left = new SpeedControllerGroup(fl, bl);
        //right = new SpeedControllerGroup(fr, br);
        br.follow(fr);
        bl.follow(fl);

        fr.setInverted(true);
        br.setInverted(true);
        bl.setInverted(true);
        fl.setInverted(true);

        drive = new DifferentialDrive(fl, fr);
    }

    public void tankDrive() {
        drive.tankDrive(Robot.m_oi.getLeft().getY(), Robot.m_oi.getRight().getY());
    }

    public void setMotors(double left, double right) {
        fl.set(left);
        fr.set(right);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
}
