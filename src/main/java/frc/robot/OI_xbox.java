/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.RobotMap.Buttons.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap.Buttons;
import frc.robot.RobotMap.Joysticks;
import frc.robot.commands.BackToggleCommand;
import frc.robot.commands.ChangeLedMode;
import frc.robot.commands.FlywheelsCommand;
import frc.robot.commands.FrontToggleCommand;
import frc.robot.commands.LiftLowerCommand;
import frc.robot.commands.LiftRaiseCommand;
import frc.robot.commands.RetractAllHatch;
import frc.robot.commands.ToggleClimbPistons;
//import frc.robot.commands.PivotCommand;
import frc.robot.commands.ToggleHatchPiston;
import frc.robot.commands.VisionAlignCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI_xbox {

    public static XboxController c;
    public static JoystickButton left, right;
    
    public OI_xbox() {
        c = new XboxController(0);
    }

    public double getLeft() {
        return c.getY(Hand.kLeft);
    }

    public double getRight() {
        return c.getY(Hand.kRight);
    }

}
