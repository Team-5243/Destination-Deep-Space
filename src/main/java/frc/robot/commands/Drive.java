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

public class Drive extends Command {
  DriveSubsystem drive;
  int direction;
  public Drive(int d) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    drive = Robot.m_drivetrain;
    requires(drive);
    direction = d;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(direction == 0)
      drive.turnLeft();
    else if(direction == 1)
      drive.forward();
    else if(direction == 2)
      drive.turnRight();
    else 
      drive.setMotors(0, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.setMotors(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drive.setMotors(0, 0);
  }
}
