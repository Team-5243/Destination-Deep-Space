/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.FlywheelsCommand;
import frc.robot.commands.LiftLowerCommand;
import frc.robot.commands.LiftRaiseCommand;
//import frc.robot.commands.PivotCommand;
import frc.robot.commands.ToggleHatchPiston;
import frc.robot.commands.VisionAlignCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private XboxController controller;
    private Button b_intake, b_outtake, b_raise, b_lower, b_piston, b_align; /* b_pDown, b_pUp, */ 
    public OI() {
        controller = new XboxController(0);
        /*
        XBox Controller Guide YAY:
        Intake - leftBumper
        Outtake - rightBumper
        RaiseLift - Y
        LowerLift - X
        Piston - A
        VisionAlign - B

        */
        b_intake = new JoystickButton(controller, RobotMap.Buttons.FLYWHEEL_INTAKE.get());

        b_outtake = new JoystickButton(controller, RobotMap.Buttons.FLYWHEEL_OUTTAKE.get());

        b_raise = new JoystickButton(controller, RobotMap.Buttons.LIFT_RAISE.get());
        b_lower = new JoystickButton(controller, RobotMap.Buttons.LIFT_LOWER.get());

       // b_pDown = new JoystickButton(left, 3);
       // b_pUp = new JoystickButton(left, 4);

        b_piston = new JoystickButton(controller, RobotMap.Buttons.HATCH_PISTON.get());

        b_align = new JoystickButton(controller, RobotMap.Buttons.VISION_ALIGN.get());

        b_intake.whileHeld(new FlywheelsCommand(true));
        b_outtake.whileHeld(new FlywheelsCommand(false));

        b_piston.whenPressed(new ToggleHatchPiston());
        b_align.whenPressed(new VisionAlignCommand());

        b_raise.whileHeld(new LiftRaiseCommand());
        b_lower.whileHeld(new LiftLowerCommand());
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
        return controller.getYButtonPressed();
    }

    public boolean getLower() {
        return controller.getXButtonPressed();
    }

    public XboxController getController(){
        return controller;
    }

    public boolean isJoysticksNeutral(){
        return Math.abs(getController().getY(Hand.kLeft)) < 0.1 && Math.abs(getController().getX(Hand.kLeft)) < 0.1 &&
                Math.abs(getController().getY(Hand.kRight)) < 0.1 && Math.abs(getController().getX(Hand.kRight)) < 0.1;
    }

}
