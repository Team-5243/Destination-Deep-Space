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
import frc.robot.commands.ExtendPiston;
import frc.robot.commands.FlywheelsCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.commands.LiftLowerCommand;
import frc.robot.commands.LiftRaiseCommand;
import frc.robot.commands.RetractPiston;
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
            Button 4: Pistons for Hatch
            Button 6: Vision Align
		*/

        for(Buttons button : Buttons.values()) {
            button.setJoystickButton(new JoystickButton(button.getJoystick().get(), button.getButton()));
        }

        //b_intake = new JoystickButton(left, 1);
        //b_outtake = new JoystickButton(right, 1);

        //b_raise = new JoystickButton(left, 5);
        //b_lower = new JoystickButton(left, 6);

        //b_pDown = new JoystickButton(left, 3);
        //b_pUp = new JoystickButton(left, 4);

        //b_piston = new JoystickButton(right, 4);

        //b_align = new JoystickButton(right, 6);

        FLYWHEEL_INTAKE.getJoystickButton().whileHeld(new FlywheelsCommand(true));
        FLYWHEEL_OUTTAKE.getJoystickButton().whileHeld(new FlywheelsCommand(false));

        HATCH_PISTON_TOGGLE.getJoystickButton().whenPressed(new ToggleHatchPiston());
        //HATCH_PISTON_RETRACT.getJoystickButton().whenPressed(new RetractPiston());
        VISION_ALIGN.getJoystickButton().whenPressed(new VisionAlignCommand());

        LIFT_RAISE.getJoystickButton().whileHeld(new LiftRaiseCommand());
        LIFT_LOWER.getJoystickButton().whileHeld(new LiftLowerCommand());
        //LIFT_RAISE.getJoystickButton().whileHeld(new LiftCommand(LiftModes.RAISE));
        //LIFT_LOWER.getJoystickButton().whileHeld(new LiftCommand(LiftModes.LOWER));

        // LIFT_RAISE.getJoystickButton().whenReleased(new LiftCommand(LiftModes.JUST_SUSPENDED));
        // LIFT_LOWER.getJoystickButton().whenReleased(new LiftCommand(LiftModes.JUST_SUSPENDED));

        // LIFT_RAISE.getJoystickButton().whenInactive(new LiftCommand(LiftModes.SUSPEND));
        // LIFT_LOWER.getJoystickButton().whenInactive(new LiftCommand(LiftModes.SUSPEND));

        //b_intake.whileHeld(new FlywheelsCommand(true));
        //b_outtake.whileHeld(new FlywheelsCommand(false));

        //b_pUp.whileHeld(new PivotCommand(true));
        //b_pDown.whileHeld(new PivotCommand(false));

        /*b_piston.whenPressed(new ToggleHatchPiston());

        b_align.whenPressed(new VisionAlignCommand());

        b_raise.whileHeld(new LiftCommand(LiftModes.RAISE));
        b_lower.whileHeld(new LiftCommand(LiftModes.LOWER));

        b_raise.whenReleased(new LiftCommand(LiftModes.JUST_SUSPENDED));
        b_lower.whenReleased(new LiftCommand(LiftModes.JUST_SUSPENDED));

        b_raise.whenInactive(new LiftCommand(LiftModes.SUSPEND));
        b_lower.whenInactive(new LiftCommand(LiftModes.SUSPEND));*/
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
