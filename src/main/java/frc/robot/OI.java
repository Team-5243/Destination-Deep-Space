/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick left, right;
    //private JoystickButton b_intake, b_outtake, b_raise, b_lower;

    public OI() {
        left = new Joystick(0);
        right = new Joystick(1);

        // b_intake = new JoystickButton(left, 4);
        // b_outtake = new JoystickButton(left, 3);

        // b_raise = new JoystickButton(left, 5);
        // b_lower = new JoystickButton(left, 6);

        // b_intake.whileHeld(new RunFlywheels(true));
        // b_outtake.whileHeld(new RunFlywheels(false));
    }

    public Joystick getLeft() {
        return left;
    }

    public Joystick getRight() {
        return right;
    }
}
