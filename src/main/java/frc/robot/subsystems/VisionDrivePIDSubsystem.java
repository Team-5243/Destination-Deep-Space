/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class VisionDrivePIDSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
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

  public VisionDrivePIDSubsystem(double p, double i, double d, double desiredValue,
                                double min, double max) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.desiredValue = desiredValue;
    this.min = min;
    this.max = max;
    runtime = new Timer();
    runtime.reset();
    runtime.start();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void reset() {
    startTime = runtime.getFPGATimestamp();
    previousTime = 0d;
    previousError = 0d;

    integralValue = 0d;

    output = 0d;

    resetIntegral = false;
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

    if((int)(previousTime) % 4 == 0 && !resetIntegral) {
      integralValue = 0d;
      resetIntegral = true;
    }

    if((int)(previousTime) % 4 != 0) {
      resetIntegral = false;
    }
  }
}
