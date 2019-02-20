/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.RobotMap.JoysticksButtons.FLYWHEEL_INTAKE;
import static frc.robot.RobotMap.JoysticksButtons.FLYWHEEL_OUTTAKE;
import static frc.robot.RobotMap.JoysticksButtons.HATCH_PISTON;
import static frc.robot.RobotMap.JoysticksButtons.LIFT_LOWER;
import static frc.robot.RobotMap.JoysticksButtons.LIFT_RAISE;
import static frc.robot.RobotMap.JoysticksButtons.VISION_ALIGN;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap.Joysticks;
import frc.robot.RobotMap.JoysticksButtons;
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

    public boolean isJoysticks;
    public OI() {
        //Change this boolean based on the control
        // joystickInit();
        // isJoysticks = true;
        // if(getLeftStick() == null && getRightStick() == null){
        //     xBoxInit();
        //     isJoysticks = false;
        // }
        isJoysticks = true;
        
        if(isJoysticks) joystickInit();
        else            xBoxInit();
    }

    public void joystickInit(){
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

        for(JoysticksButtons button : JoysticksButtons.values()) {
            button.setJoystickButton(new JoystickButton(button.getJoystick().get(), button.getButton()));
        }

        FLYWHEEL_INTAKE.getJoystickButton().whileHeld(new FlywheelsCommand(true));
        FLYWHEEL_OUTTAKE.getJoystickButton().whileHeld(new FlywheelsCommand(false));

        HATCH_PISTON.getJoystickButton().whenPressed(new ToggleHatchPiston());
        VISION_ALIGN.getJoystickButton().whenPressed(new VisionAlignCommand());

        LIFT_RAISE.getJoystickButton().whileHeld(new LiftRaiseCommand());
        LIFT_LOWER.getJoystickButton().whileHeld(new LiftLowerCommand());
    }

    public void xBoxInit(){
        controller = new XboxController(0);
        /* XBox Controller Guide YAY:
        Intake - Left Bumper
        Outtake - Right Bumper
        Raise Lift - Y
        Lower Lift - X
        Piston - A
        Vision Alignment - B
        */

        b_intake = new JoystickButton(controller, RobotMap.XBoxButtons.FLYWHEEL_INTAKE.get());

        b_outtake = new JoystickButton(controller, RobotMap.XBoxButtons.FLYWHEEL_OUTTAKE.get());

        b_raise = new JoystickButton(controller, RobotMap.XBoxButtons.LIFT_RAISE.get());
        b_lower = new JoystickButton(controller, RobotMap.XBoxButtons.LIFT_LOWER.get());

        // b_pDown = new JoystickButton(left, 3);
        // b_pUp = new JoystickButton(left, 4);

        b_piston = new JoystickButton(controller, RobotMap.XBoxButtons.HATCH_PISTON.get());

        b_align = new JoystickButton(controller, RobotMap.XBoxButtons.VISION_ALIGN.get());

        b_intake.whileHeld(new FlywheelsCommand(true));
        b_outtake.whileHeld(new FlywheelsCommand(false));

        b_piston.whenPressed(new ToggleHatchPiston());
        b_align.whenPressed(new VisionAlignCommand());

        b_raise.whileHeld(new LiftRaiseCommand());
        b_lower.whileHeld(new LiftLowerCommand());
    }

    public boolean getRaise() {
        return isJoysticks ? getLeftStick().getRawButtonPressed(5) :  controller.getYButtonPressed();
    }

    public boolean getLower() {
        return isJoysticks ? getLeftStick().getRawButtonPressed(6) : controller.getXButtonPressed();
    }

    public Joystick getLeftStick(){
        return isJoysticks ? RobotMap.Joysticks.LEFT.get() : null;
    }

    public Joystick getRightStick(){
        return isJoysticks ? RobotMap.Joysticks.RIGHT.get() : null;
    }

    public double getLeftYAxis(){
        return isJoysticks ? getLeftStick().getY() : getController().getY(Hand.kLeft);
    }

    public double getRightYAxis(){
        return isJoysticks ? getRightStick().getY() : getController().getY(Hand.kRight);
    }

    public XboxController getController(){
        return isJoysticks ? null : controller;
    }

    public boolean isJoysticksNeutral(){
        return isJoysticks ? Math.abs(getLeftStick().getY()) < 0.1 && Math.abs(getLeftStick().getX()) < 0.1 &&
            Math.abs(getRightStick().getY()) < 0.1 && Math.abs(getRightStick().getX()) < 0.1 :
            Math.abs(getController().getY(Hand.kLeft)) < 0.1 && Math.abs(getController().getX(Hand.kLeft)) < 0.1 &&
            Math.abs(getController().getY(Hand.kRight)) < 0.1 && Math.abs(getController().getX(Hand.kRight)) < 0.1;
    }

}
