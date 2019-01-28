/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.VisionSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class ReceiveData extends Command {

  VisionSubsystem visionSubsystem;

  public ReceiveData() {
    // Use requires() here to declare subsystem dependencies
    visionSubsystem = Robot.visionSubsystem;
    requires(visionSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    visionSubsystem.openPort();
    RobotMap.finishServer = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    visionSubsystem.receiveData();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //visionSubsystem.closePort();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //visionSubsystem.closePort();
  }
}
