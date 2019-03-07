/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.LiftModes;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.PIDLiftSubsystem;

public class LiftCommand extends Command {
  LiftSubsystem lift;
  //PIDLiftSubsystem lift_pid;
  
  public LiftCommand(LiftModes m) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //lift = Robot.m_lift;
    lift = Robot.m_lift;
    RobotMap.liftMode = m;

    //requires(lift);
    requires(lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  /**
   * Dual button for raising and lowering the lift requires activation checks for both
   * buttons lest, for example, the robot suspend and raise simultaneously, resulting
   * in a conflict in elevator movement.
   * 
   * @see RobotMap
   * @see PIDLiftSubsystem
   * @see RobotMap.LiftModes
   */
  @Override
  protected void execute() {
    if(RobotMap.liftMode.equals(LiftModes.RAISE)) {
      lift.elongate(true);
    } else if(RobotMap.liftMode.equals(LiftModes.LOWER)) {
      lift.elongate(false);
    // } else if(RobotMap.liftMode.equals(LiftModes.JUST_SUSPENDED)) {
    //   //Lift is to set position at which to suspend, but only if both buttons are not activated
    //   if(!Robot.m_oi.getRaise() && !Robot.m_oi.getLower()) {
    //     lift_pid.stopLift();
    //     lift_pid.setDesiredValue(lift_pid.encoderCount());
    //   }
    // } else {
    //   //Lift is to suspend, but only if both raise and lower buttons are not activated
    //   if(!Robot.m_oi.getRaise() && !Robot.m_oi.getLower()) {
    //     lift_pid.update(lift_pid.encoderCount());
    //     lift_pid.updateSpeed();
    //   }
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
    lift.stopLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    lift.stopLift();
  }
}
