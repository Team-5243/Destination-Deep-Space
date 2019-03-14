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

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX fl, fr, bl, br;
  DifferentialDrive drive;

  public DriveSubsystem(){
    fl = new WPI_TalonSRX(RobotMap.Drive.FRONT_LEFT.get());
    fr = new WPI_TalonSRX(RobotMap.Drive.FRONT_RIGHT.get());
    bl = new WPI_TalonSRX(RobotMap.Drive.BACK_LEFT.get());
    br = new WPI_TalonSRX(RobotMap.Drive.BACK_RIGHT.get());

    bl.follow(fl);
    br.follow(fr);

    drive = new DifferentialDrive(fl, fr);
  }

  public void tankDrive(){
    drive.tankDrive(Robot.m_oi.getLeft().getY(), Robot.m_oi.getRight().getY());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TankDrive());
  }
}
