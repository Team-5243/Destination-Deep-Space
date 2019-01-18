/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class RaiseSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX leftRaise, rightRaise;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public RaiseSubsystem() {
    leftRaise = new WPI_TalonSRX(RobotMap.leftRaise.get());
    rightRaise = new WPI_TalonSRX(RobotMap.rightRaise.get());

    leftRaise.follow(rightRaise);
  }

  public void elongate(boolean taller) {
    if(taller) {
      rightRaise.set(ControlMode.PercentOutput, .5d);
    }
    else {
      rightRaise.set(ControlMode.PercentOutput, -.5d);
    }
  }

  public void stopMotors() {
    rightRaise.set(ControlMode.PercentOutput, 0);
  }

}
