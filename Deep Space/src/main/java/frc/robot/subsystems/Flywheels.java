/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.RunFlywheels;

public class Flywheels extends Subsystem {

    private WPI_TalonSRX left, right;

    public Flywheels() {
        left = new WPI_TalonSRX(4);
        right = new WPI_TalonSRX(1);
        right.setInverted(true);
        right.follow(left);
    }

    public void runFlywheels() {
        if (Robot.m_oi.getRight().getRawButton(3))
            left.set(ControlMode.PercentOutput, .5d);
        else if (Robot.m_oi.getRight().getRawButton(4))
            left.set(ControlMode.PercentOutput, -.5d);
        else 
            left.set(0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RunFlywheels());
    }
}
