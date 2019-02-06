/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.TridentSubsystem;

public class Pivot extends Command {
  private TridentSubsystem trident;
  private boolean up;

  public Pivot(boolean upDown) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    trident = Robot.m_trident;
    requires(trident);
    up = upDown;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(up){
      trident.pivotUp();
    }
    else{
      trident.pivotDown();
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
    trident.stopPivots();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    trident.stopPivots();
  }
}
