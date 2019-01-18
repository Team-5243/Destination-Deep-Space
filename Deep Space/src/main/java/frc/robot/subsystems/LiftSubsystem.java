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
public class LiftSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX leftLift, rightLift;

  public LiftSubsystem() {
    leftLift = new WPI_TalonSRX(RobotMap.leftLift.get());
    rightLift = new WPI_TalonSRX(RobotMap.rightLift.get());

    leftLift.follow(rightLift);
  }

  public void elongate(boolean taller) {
    if(taller) {
      rightLift.set(.5d);
    }
    else {
      rightLift.set(-.5d);
    }
  }

  public void stopMotors() {
    rightLift.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
