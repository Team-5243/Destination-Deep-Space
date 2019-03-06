/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap.Buttons;
import frc.robot.RobotMap.Joysticks;
import frc.robot.RobotMap.LiftModes;
import frc.robot.commands.FlywheelsCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.commands.LiftLowerCommand;
import frc.robot.commands.LiftRaiseCommand;
import frc.robot.commands.ToggleClimbPistons;
//import frc.robot.commands.PivotCommand;
import frc.robot.commands.ToggleHatchPiston;
//import frc.robot.commands.PivotCommand;
import frc.robot.commands.VisionAlignCommand;
import static frc.robot.RobotMap.Buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //private JoystickButton b_intake, b_outtake, b_raise, b_lower, b_piston, b_align; /* b_pDown, b_pUp, */ 
    public OI() {
        Joysticks.LEFT.setJoystick(new Joystick(0));
        Joysticks.RIGHT.setJoystick(new Joystick(1));

        /*
        Left Joystick:
            Trigger: Flywheels Intake
			Button 3: Pivot Up 	 | Button 4: Pivot Down
            Button 5: Raise Lift | Button 6: Lower Lift
        
        Right Joystick:
            Trigger: Flywheels Outtake
            Button 4: Hatch Pistons
            Button 6: Vision Align
            Button 5: Front Climb | Button 3: Back Climb
		*/

        for(Buttons button : Buttons.values()) {
            button.setJoystickButton(new JoystickButton(button.getJoystick().get(), button.getButton()));
        }

        FLYWHEEL_INTAKE.getJoystickButton().whileHeld(new FlywheelsCommand(true));
        FLYWHEEL_OUTTAKE.getJoystickButton().whileHeld(new FlywheelsCommand(false));

        HATCH_PISTON.getJoystickButton().whenPressed(new ToggleHatchPiston());
        //HATCH_PISTON_RETRACT.getJoystickButton().whenPressed(new RetractPiston());
        VISION_ALIGN.getJoystickButton().whenPressed(new VisionAlignCommand());

        FRONT_CLIMB.getJoystickButton().whenPressed(new ToggleClimbPistons(true));
        BACK_CLIMB.getJoystickButton().whenPressed(new ToggleClimbPistons(false));

        LIFT_RAISE.getJoystickButton().whileHeld(new LiftRaiseCommand());
        LIFT_LOWER.getJoystickButton().whileHeld(new LiftLowerCommand());
        //LIFT_RAISE.getJoystickButton().whileHeld(new LiftCommand(LiftModes.RAISE));
        //LIFT_LOWER.getJoystickButton().whileHeld(new LiftCommand(LiftModes.LOWER));

        // LIFT_RAISE.getJoystickButton().whenReleased(new LiftCommand(LiftModes.JUST_SUSPENDED));
        // LIFT_LOWER.getJoystickButton().whenReleased(new LiftCommand(LiftModes.JUST_SUSPENDED));

        // LIFT_RAISE.getJoystickButton().whenInactive(new LiftCommand(LiftModes.SUSPEND));
        // LIFT_LOWER.getJoystickButton().whenInactive(new LiftCommand(LiftModes.SUSPEND));
    }

    public boolean getRaise() {
        return getLeft().getRawButtonPressed(5);
    }

    public boolean getLower() {
        return getLeft().getRawButtonPressed(6);
    }

    public Joystick getLeft() {
        return Joysticks.LEFT.get();
    }

    public Joystick getRight() {
        return Joysticks.RIGHT.get();
    }

    public boolean isJoysticksNeutral(){
        return Math.abs(getLeft().getY()) < 0.1 && Math.abs(getLeft().getX()) < 0.1 &&
                Math.abs(getRight().getY()) < 0.1 && Math.abs(getRight().getX()) < 0.1;
    }

}
