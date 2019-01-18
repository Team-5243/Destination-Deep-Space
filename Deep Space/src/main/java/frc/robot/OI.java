/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.RaiseRobot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick left, right;
    private JoystickButton tallButton, shortButton;

    public OI() {
        left = new Joystick(0);
        right = new Joystick(1);
        tallButton = new JoystickButton(left, 4);
        shortButton = new JoystickButton(left, 5);

        //WARNING --- The RaiseRobot command has no min/max limit set
        tallButton.whileHeld(new RaiseRobot(true)); 
        shortButton.whileHeld(new RaiseRobot(false));
    }

    public Joystick getLeft() {
        return left;
    }

    public Joystick getRight() {
        return right;
    }

    


}
