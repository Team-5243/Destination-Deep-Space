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
public class RobotMap {

	public enum Drive{
		//TankDrive	
		FRONT_RIGHT(0), 
		FRONT_LEFT(3),
		BACK_RIGHT(1),
		BACK_LEFT(2);

		private int port;
	
		private Drive(int port) {
			this.port = port;
		}

		public int get() {
			return port;
		}
	}	

	public enum Trident{
		//Trident (placeholder)
		LEFT_FLYWHEELS(5),
		RIGHT_FLYWHEELS(6),

		//Hatch DoubleSolenoid (placeholder)
		HATCH_PISTON_F(2),
		HATCH_PISTON_R(3);

		private int port;
		
		private Trident(int port) {
			this.port = port;
		}

		public int get() {
			return port;
		}
	}

	public enum Lift{
		//Pivot (placeholder)
		//LEFT_PIVOT(7),
		//RIGHT_PIVOT(8),

		//Lift (placeholder)
		LEFT_LIFT(12),
		RIGHT_LIFT(13),

		ENCODER_CHANNEL_A(0),
		ENCODER_CHANNEL_B(1);

		private int port;
		
		private Lift(int port) {
			this.port = port;
		}

		public int get() {
			return port;
		}
	}
}
