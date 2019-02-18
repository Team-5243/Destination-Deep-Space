/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.PIDLiftSubsystem;

public class LiftCommand extends Command {
  LiftSubsystem lift;
  int mode;
  public LiftCommand(int m) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    lift = Robot.m_lift;
    mode = m;
    requires(lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (mode == 0) {
      lift.elongate(true);
    } else if (mode == 1) {
      lift.elongate(false);
    }
    else {
      lift.stopLift();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_lift.stopLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_lift.stopLift();
  }
}
