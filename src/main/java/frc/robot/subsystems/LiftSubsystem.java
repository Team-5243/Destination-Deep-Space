/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {

  //Includes Lifts and Pivots

  WPI_TalonSRX leftLift, rightLift; //, leftPivot, rightPivot;
  Encoder encoder;

  public LiftSubsystem() {
    leftLift = new WPI_TalonSRX(RobotMap.Lift.LEFT_LIFT.get());
    rightLift = new WPI_TalonSRX(RobotMap.Lift.RIGHT_LIFT.get());
    encoder = new Encoder(RobotMap.Lift.ENCODER_CHANNEL_A.get(), RobotMap.Lift.ENCODER_CHANNEL_B.get(), false, Encoder.EncodingType.k4X);
		encoder.setDistancePerPulse(5);
    //leftLift.setInverted(true);
    //rightLift.setInverted(true);
    //leftPivot = new WPI_TalonSRX(RobotMap.leftPivot.get());
    //rightPivot = new WPI_TalonSRX(RobotMap.rightPivot.get());

    rightLift.follow(leftLift);
    //rightPivot.follow(leftPivot);
  }


  //TODO: test encoder hard limits
  public void elongate(boolean taller) {
    if(taller /*&& getDistance() < 1000*/) {
      leftLift.set(1d);
    }
    else if (!taller /*getDistance() > 0*/){
      leftLift.set(-1d);
    }
    else{
      stopLift();
    }
  }

  public void stopLift() {
    leftLift.set(0);
  }

  	/**
	 * 
	 * @return some count of the encoder - no idea
	 */
	public double encoderCount() {
		return encoder.get();

	}

	/**
	 * 
	 * @return the supposed distance the encoder has gone - no idea
	 */
	public double getDistance() {
		return encoder.getDistance();
	}

	/**
	 * Resets the encoder to its default state
	 */
	public void resetEncoder() {
		encoder.reset();
	}


  // public void setPivots(boolean up){
  //   leftLift.set(up ? .5 : -.5);
  // }

  // public void stopPivots(){
  //     leftPivot.set(0);
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
