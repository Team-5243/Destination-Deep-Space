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
	public static enum Drive{
		FRONT_RIGHT(5), 
		FRONT_LEFT(6),
		BACK_RIGHT(2),
		BACK_LEFT(3);

		private int port;

		private Drive(int port){
			this.port = port;
		}

		public int get(){
			return port;
		}
	}
}
