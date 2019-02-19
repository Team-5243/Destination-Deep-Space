/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static enum Drive {
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

	public static enum Trident{
		//Trident (placeholder)
		LEFT_FLYWHEELS(7),
		RIGHT_FLYWHEELS(6),

		//Hatch DoubleSolenoid
		HATCH_TOP_PISTON_F(4),
		HATCH_TOP_PISTON_R(5),
		HATCH_DOWN_PISTON_F(3),
		HATCH_DOWN_PISTON_R(2);

		private int port;
		
		private Trident(int port) {
			this.port = port;
		}

		public int get() {
			return port;
		}
	}

	public static enum Lift{
		//Pivot (placeholder)
		//LEFT_PIVOT(7),
		//RIGHT_PIVOT(8),

		//Lift (placeholder)
		LIFT(13),
		//RIGHT_LIFT(13),

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

	public static enum Buttons{
		/* XBoxController Mapping:

        The buttons on the controller follow this mapping
        1: A
        2: B
        3: X
        4: Y
        5: Left Bumper
        6: Right Bumper
        7: Back
        8: Start
        9: Left Joystick
        10: Right Joystick
        The axis on the controller follow this mapping
        (all output is between -1 and 1)
        1: Left Stick X Axis
        -Left:Negative ; Right: Positive
        2: Left Stick Y Axis
        -Up: Negative ; Down: Positive
        3: Triggers
        -Left: Positive ; Right: Negative
        4: Right Stick X Axis
        -Left: Negative ; Right: Positive
        5: Right Stick Y Axis
        -Up: Negative ; Down: Positive
        6: Directional Pad (Not recommended, buggy)
        */
		FLYWHEEL_INTAKE(5),
		FLYWHEEL_OUTTAKE(6),
		//PIVOT_DOWN(3),
		//PIVOT_UP(4),
		LIFT_RAISE(4),
		LIFT_LOWER(3),
		HATCH_PISTON(1),
		VISION_ALIGN(2);

		private int buttonNumber;

		private Buttons(int buttonNumber){
			this.buttonNumber = buttonNumber;
		}

		public int get(){
			return buttonNumber;
		}
	}


	public static LiftModes liftMode = LiftModes.SUSPEND; //Default to the lift not moving

	public static enum LiftModes {
		RAISE, LOWER, SUSPEND, JUST_SUSPENDED;
	}
}
