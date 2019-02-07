/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.FlashBoi;
import frc.robot.commands.RunCommandGroup;
import frc.robot.commands.VisionAlignCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick left, right;
    private JoystickButton blinky, bright, dead, auton;

    public OI() {
        left = new Joystick(0);
        right = new Joystick(1);

        blinky = new JoystickButton(right, 3);
        bright = new JoystickButton(right, 4);
        dead = new JoystickButton(right, 5);
        auton = new JoystickButton(left, 6);

        blinky.whenPressed(new FlashBoi(2));
        bright.whenPressed(new FlashBoi(3));
        dead.whenPressed(new FlashBoi(1));
        auton.whenPressed(new VisionAlignCommand());
    }

    public Joystick getLeft() {
        return left;
    }

    public Joystick getRight() {
        return right;
    }


    


}
