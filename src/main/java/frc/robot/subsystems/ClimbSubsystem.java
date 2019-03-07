/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimbSubsystem extends Subsystem {
  
  //Includes Climb Pistons

  DoubleSolenoid frontRightClimb, frontLeftClimb, backLeftClimb, backRightClimb;
  
  public ClimbSubsystem() {
    // frontRightClimb = new DoubleSolenoid(RobotMap.Climb.FRONT_RIGHT.getForward(), RobotMap.Climb.FRONT_RIGHT.getReverse());
    // frontLeftClimb = new DoubleSolenoid(RobotMap.Climb.FRONT_LEFT.getForward(), RobotMap.Climb.FRONT_LEFT.getReverse());
    // backLeftClimb = new DoubleSolenoid(RobotMap.Climb.BACK_LEFT.getForward(), RobotMap.Climb.BACK_LEFT.getReverse());
    // backRightClimb = new DoubleSolenoid(RobotMap.Climb.BACK_RIGHT.getForward(), RobotMap.Climb.BACK_RIGHT.getReverse());
  }

  public void toggleFrontClimb(){
    // if (frontLeftClimb.get().equals(Value.kReverse) || frontLeftClimb.get().equals(Value.kOff)) {
    //   frontLeftClimb.set(Value.kForward);
    //   frontRightClimb.set(Value.kForward);
    // } else {
    //   frontLeftClimb.set(Value.kReverse);
    //   frontRightClimb.set(Value.kReverse);
    // }
  }

  public void toggleBackClimb(){
    // if (backLeftClimb.get().equals(Value.kReverse) || backLeftClimb.get().equals(Value.kOff)) {
    //   backLeftClimb.set(Value.kForward);
    //   backRightClimb.set(Value.kForward);
    // } else {
    //   backLeftClimb.set(Value.kReverse);
    //   backRightClimb.set(Value.kReverse);
    // }
  }

  public String getFrontLeftPiston(){
    //return frontLeftClimb.get() == Value.kForward ? "Forward" : frontLeftClimb.get() == Value.kReverse ? "Reverse" : "Off";
    return "";
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
