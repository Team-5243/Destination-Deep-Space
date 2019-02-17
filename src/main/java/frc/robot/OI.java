/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.FlywheelsCommand;
import frc.robot.commands.LiftCommand;
//import frc.robot.commands.PivotCommand;
import frc.robot.commands.ToggleHatchPiston;
import frc.robot.commands.VisionAlignCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick left, right;
    private JoystickButton b_intake, b_outtake, b_raise, b_lower, b_piston, b_align; /* b_pDown, b_pUp, */ 
    public OI() {
        left = new Joystick(0);
        right = new Joystick(1);

        /*
        Left Joystick:
            Trigger: Flywheels Intake
            Button 3 & 4: Pivot
            Button 5 & 6: Lift
        
        Right Joystick:
            Trigger: Flywheels Outtake
            Button 4: Double Solenoid for Hatch
            Button 5: Cancel Vision Align
            Button 6: Vision Align
        */

        b_intake = new JoystickButton(left, 1);
        b_outtake = new JoystickButton(right, 1);

        b_raise = new JoystickButton(left, 5);
        b_lower = new JoystickButton(left, 6);

        //b_pDown = new JoystickButton(left, 3);
        //b_pUp = new JoystickButton(left, 4);

        b_piston = new JoystickButton(right, 4);

        b_align = new JoystickButton(right, 6);

        b_intake.whileHeld(new FlywheelsCommand(true));
        b_outtake.whileHeld(new FlywheelsCommand(false));

        b_raise.whileHeld(new LiftCommand(true)); 
        b_lower.whileHeld(new LiftCommand(false));

        //b_pUp.whileHeld(new PivotCommand(true));
        //b_pDown.whileHeld(new PivotCommand(false));

        b_piston.whenPressed(new ToggleHatchPiston());

        b_align.whenPressed(new VisionAlignCommand());
    }

    public Joystick getLeft() {
        return left;
    }

    public Joystick getRight() {
        return right;
    }

    //Right button #5 also cancels the auto alignment
    public boolean isJoysticksNeutral(){
        return Math.abs(left.getY()) < 0.1 && Math.abs(left.getX()) < 0.1 &&
                Math.abs(right.getY()) < 0.1 && Math.abs(right.getX()) < 0.1 && !right.getRawButton(5);
    }

}
