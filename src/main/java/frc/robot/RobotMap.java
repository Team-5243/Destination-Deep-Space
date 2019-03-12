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

	public static enum FlyHatch {
		//Trident
		LEFT_FLYWHEELS(7),
		RIGHT_FLYWHEELS(6),

		//Hatch DoubleSolenoid (placeholder)
		HATCH_TOP_PISTON_F(3),
		HATCH_TOP_PISTON_R(2),
		MIDDLE_PISTON_F(4),
		MIDDLE_PISTON_R(5);
		// HATCH_DOWN_PISTON_F(3),
		// HATCH_DOWN_PISTON_R(2);

		private int port;
		
		private FlyHatch(int port) {
			this.port = port;
		}

		public int get() {
			return port;
		}
	}

	public static enum Climb {
		BACK_RIGHT(6, 7),
		BACK_LEFT(8, 9),
		FRONT_RIGHT(10, 11),
		FRONT_LEFT(12, 13);

		private int forward;
		private int reverse;

		private Climb(int forward, int reverse) {
			this.forward = forward;
			this.reverse = reverse;
		}

		public int getForward(){
			return forward;
		}

		public int getReverse(){
			return reverse;
		}
	}

	public static enum Lift {
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

	public static enum Vision {
		OFF(1),
		BLINK(2),
		ON(3);

		private int mode;

		private Vision(int mode){
			this.mode = mode;
		}

		public int get(){
			return mode;
		}
	}

	public static enum Buttons {
		/*
        Left Joystick:
            Trigger: Flywheels Intake
			Button 4: Retract All Hatch Pistons
            Button 5: Raise Lift | Button 6: Lower Lift
        
        Right Joystick:
            Trigger: Flywheels Outtake
            Button 4: Hatch Pistons
            Button 6: Vision Align
            Button 5: Front Climb | Button 3: Back Climb

            Button 2: Toggle Cam Mode
            Button 10: Led Off | Button 11: Led Blink | Button 12: Led On
		*/

		FLYWHEEL_INTAKE(Joysticks.LEFT, 1),
		RETRACT_ALL(Joysticks.LEFT, 4),
		LIFT_RAISE(Joysticks.LEFT, 5),
		LIFT_LOWER(Joysticks.LEFT, 6),
		
		FLYWHEEL_OUTTAKE(Joysticks.RIGHT, 1),
		HATCH_PISTON(Joysticks.RIGHT, 4),
		VISION_ALIGN(Joysticks.RIGHT, 6),
		FRONT_CLIMB(Joysticks.RIGHT, 5),
		BACK_CLIMB(Joysticks.RIGHT, 3),

		CAM_MODE(Joysticks.RIGHT, 2),
		LED_OFF(Joysticks.RIGHT, 10),
		LED_BLINK(Joysticks.RIGHT, 11),
		LED_ON(Joysticks.RIGHT, 12);

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
