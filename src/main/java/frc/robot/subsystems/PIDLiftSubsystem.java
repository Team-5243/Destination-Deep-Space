/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

/**
 * This {@code class} extends the {@code LiftSubsystem class} in order to effectively
 * suspend the elevator system once the robot is signaled to maintain its position as
 * it is currently extended.  The PID system ensures that gravity not affect the
 * elevator system while in suspension mode, acting as a programatical solution to a
 * hard stop for the lift system.
 * 
 * There does exist a built-in {@code PIDSubsystem} that WPILib provides, but creating
 * a more direct approach that permits quicker tweaking compelled us to make our own
 * PID for the {@code LiftSubsystem}.
 * 
 * @see LiftSubsystem
 * @see RobotMap
 * @see PIDSubsystem
 * @see Timer
 */
public class PIDLiftSubsystem extends LiftSubsystem {
  public double p, i, d;
  public Timer runtime;
  public double output;
  public double previousTime;
  public double desiredValue;
  public double startTime;
  public double previousError;
  public double max;
  public double min;

  private double integralValue;
  private boolean resetIntegral;
  private boolean resetIntegralPeriodically;

  public PIDLiftSubsystem(double p, double i, double d, double desiredValue,
                                double min, double max, boolean resetIntegralPeriodically) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.desiredValue = desiredValue;
    this.min = min;
    this.max = max;
    this.resetIntegralPeriodically = resetIntegralPeriodically;
    runtime = new Timer();
    runtime.reset();
    runtime.start();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * This {@code method} refreshes all of the values required to essentially
   * create a new PID
   * 
   * @see Timer
   * @see #resetEncoder()
   */
  public void reset() {
    startTime = runtime.getFPGATimestamp();
    previousTime = 0d;
    previousError = 0d;

    integralValue = 0d;

    output = 0d;

    resetIntegral = false;
    resetEncoder();
  }

  public double trimValue(double value, double min, double max) {
    return value < min ? min : value > max ? max : value;
  }

  public void update(double currentValue) {
    double error = desiredValue - currentValue;
    //Proportional term
    output += p * error;
    //Integral term
    integralValue += i * error * (runtime.getFPGATimestamp() - previousTime);
    output += integralValue;
    //Derivative term
    output += d * (error - previousError) / (runtime.getFPGATimestamp() - previousTime);

    //Scale output to possible motor values
    output = trimValue(output, min, max);

    previousError = error;
    previousTime = runtime.getFPGATimestamp() - startTime;

    if((int)(previousTime) % 4 == 0 && !resetIntegral && resetIntegralPeriodically) {
      integralValue = 0d;
      resetIntegral = true;
    }

    if((int)(previousTime) % 4 != 0) {
      resetIntegral = false;
    }
  }

  public void setDesiredValue(double value) {
    desiredValue = value;
  }

  public double getOutput() {
    return output;
  }

  public void updateSpeed() {
    setLift(getOutput());
  }
}
