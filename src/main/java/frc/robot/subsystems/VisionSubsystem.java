/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private NetworkTable table; 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    table = NetworkTableInstance.getDefault().getTable("limelight");
    setLed(2);
  }

  public NetworkTable getTable() {
    return table;
  }

  public double getX() {
    return table.getEntry("tx").getDouble(0.0);
  }

  public double getY() {
    return table.getEntry("ty").getDouble(0.0);
  }

  public double getArea() {
    return table.getEntry("ta").getDouble(0.0);
  }

  public double getShortestSide() {
    return table.getEntry("tshort").getDouble(0.0);
  }

  public double getLongestSide() {
    return table.getEntry("tlong").getDouble(0.0);
  }

  public double getHorizontal() {
    return table.getEntry("thor").getDouble(0.0);
  }

  public double getVertical() {
    return table.getEntry("tvert").getDouble(0.0);
  }

  public double getVerticalOffset() {
    return table.getEntry("ty").getDouble(0.0);
  }

  public double getHorizontalOffset() {
    return table.getEntry("tx").getDouble(0.0);
  }

  public void setLed(int mode) {
    table.getEntry("ledMode").setNumber(mode);
  }

}
