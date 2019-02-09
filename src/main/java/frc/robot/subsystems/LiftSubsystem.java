/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {

  //Includes Lift and Pivot

  WPI_TalonSRX leftLift, rightLift, leftPivot, rightPivot;

  public LiftSubsystem() {
    leftLift = new WPI_TalonSRX(RobotMap.leftLift.get());
    rightLift = new WPI_TalonSRX(RobotMap.rightLift.get());

    leftPivot = new WPI_TalonSRX(RobotMap.leftPivot.get());
    rightPivot = new WPI_TalonSRX(RobotMap.rightPivot.get());

    rightLift.follow(leftLift);
    rightPivot.follow(leftPivot);
  }

  public void elongate(boolean taller) {
    if(taller) {
      leftLift.set(.5d);
    }
    else {
      leftLift.set(-.5d);
    }
  }

  public void stopLift() {
    leftLift.set(0);
  }

  public void setPivots(boolean up){
    leftLift.set(up ? .5 : -.5);
  }

  public void stopPivots(){
      leftPivot.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
