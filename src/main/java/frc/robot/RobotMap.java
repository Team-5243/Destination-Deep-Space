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
		//Trident
		LEFT_FLYWHEELS(7),
		RIGHT_FLYWHEELS(6),

		//Hatch DoubleSolenoid (placeholder)
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

		//Lift
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
		/*
        Left Joystick:
            Trigger: Flywheels Intake
			Button 3: Pivot Up 	 | Button 4: Pivot Down
            Button 5: Raise Lift | Button 6: Lower Lift
        
        Right Joystick:
            Trigger: Flywheels Outtake
            Button 4: Pistons for Hatch
            Button 6: Vision Align
		*/

		FLYWHEEL_INTAKE(Joysticks.LEFT, 1),
		//PIVOT_UP(Joysticks.LEFT, 3),
		//PIVOT_DOWN(Joysticks.LEFT, 4),
		LIFT_RAISE(Joysticks.LEFT, 5),
		LIFT_LOWER(Joysticks.LEFT, 6),
		
		FLYWHEEL_OUTTAKE(Joysticks.RIGHT, 1),
		HATCH_PISTON(Joysticks.RIGHT, 4),
		VISION_ALIGN(Joysticks.RIGHT, 6);

		private int buttonNumber;
		private Joysticks joystick;
		private JoystickButton button;

		private Buttons(Joysticks joystick, int buttonNumber){
			this.buttonNumber = buttonNumber;
			this.joystick = joystick;
		}

		public int getButton(){
			return buttonNumber;
		}

		public Joysticks getJoystick() {
			return joystick;
		}

		public void setJoystickButton(JoystickButton button) {
			this.button = button;
		}

		public JoystickButton getJoystickButton() {
			return button;
		}
	}

	public static enum Joysticks {
		LEFT, RIGHT;

		private Joystick joystick;

		public void setJoystick(Joystick joystick) {
			this.joystick = joystick;
		}

		public Joystick get() {
			return joystick;
		}
	}

	public static LiftModes liftMode = LiftModes.SUSPEND; //Default to the lift not moving

	public static enum LiftModes {
		RAISE, LOWER, SUSPEND, JUST_SUSPENDED;
	}
}
