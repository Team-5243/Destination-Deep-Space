/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public enum RobotMap {
	//TankDrive
	frontRight(1), 
	frontLeft(4),
	backRight(2),
	backLeft(3),

	//Trident (placeholder)
	leftFlywheels(5),
	rightFlywheels(6),

	//Pivot (placeholder)
	leftPivot(7),
	rightPivot(8),

	//Lift (placeholder)
	leftLift(9),
	rightLift(10),

	//Hatch DoubleSolenoid (placeholder)
	hatchPistonF(2),
	hatchPistonR(3);

	private int port;
	
	private RobotMap(int port) {
		this.port = port;
	}

	public int get() {
		return port;
	}

}
