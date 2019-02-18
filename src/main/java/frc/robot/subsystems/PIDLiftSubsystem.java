/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftCommand;

/**
 * Add your docs here.
 */
public class PIDLiftSubsystem extends PIDSubsystem {
  
  public WPI_TalonSRX rightLift, leftLift;
  private Encoder encoder;

  public PIDLiftSubsystem() {
    super("SubsystemName", 1, 2, 3);
    rightLift = new WPI_TalonSRX(RobotMap.Lift.RIGHT_LIFT.get());
    leftLift = new WPI_TalonSRX(RobotMap.Lift.LEFT_LIFT.get());
    leftLift.follow(rightLift);
    encoder = new Encoder(RobotMap.Lift.ENCODER_CHANNEL_A.get(), RobotMap.Lift.ENCODER_CHANNEL_B.get(), false, Encoder.EncodingType.k4X);
    encoder.setDistancePerPulse(.005);
    setAbsoluteTolerance(.5);
  }

  public void resetEncoder() {
    encoder.reset();
  }

  public void setTarget(double a) {
    setSetpoint(a);
  }

  public double getTarget() {
    return getSetpoint();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftCommand(2));
  }

  @Override
  protected double returnPIDInput() {
    return encoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    rightLift.set(clamp(output, -.5, .5)); 
  }

  private double clamp(double a, double mi, double ma) {
    return Math.max(mi, Math.min(ma, a));
  }
}
