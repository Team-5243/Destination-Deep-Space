/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionDrivePIDSubsystem;
import frc.robot.subsystems.VisionPID;
import frc.robot.subsystems.VisionSubsystem;

public class VisionAlignCamLed extends Command {
  //VisionPID visionPID;
  VisionDrivePIDSubsystem visionPID;
  VisionDrivePIDSubsystem visionAnglePID;
  VisionSubsystem vision;
  DriveSubsystem drive;

  public VisionAlignCamLed() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //visionPID = Robot.m_vision_pid;
    visionPID = Robot.m_vision_drive_pid;
    visionAnglePID = Robot.m_vision_angle_pid;
    vision = Robot.m_vision;
    drive = Robot.m_drivetrain;
    //requires(visionPID);
    requires(visionPID);
    requires(visionAnglePID);
    requires(vision);
    requires(drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive.initialAlign = false;
    drive.close = false;
    
    vision.setCamMode(0);
    vision.setLed(3);
    visionPID.reset();
    visionAnglePID.reset();
    //visionPID.enable();
    //visionPID.activateVision();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //vision.setLed(3);
    visionPID.update(vision.getArea() != 0 ? vision.getArea() : 50);
    visionAnglePID.update(vision.getX());
    drive.alignStraight();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return drive.isFinishedAlign();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.stopDrive();
    vision.setCamMode(1);
    vision.setLed(1);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drive.stopDrive();
    vision.setCamMode(1);
    vision.setLed(1);
  }
}
