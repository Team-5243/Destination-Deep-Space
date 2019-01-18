/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TridentSubsystem extends Subsystem {

    private WPI_TalonSRX leftFlywheels, rightFlywheels;

    public TridentSubsystem() {
        leftFlywheels = new WPI_TalonSRX(4);
        rightFlywheels = new WPI_TalonSRX(1);
        rightFlywheels.setInverted(true);
        rightFlywheels.follow(leftFlywheels);
    }

    public void setFlywheels(boolean intake) {
        if(intake){
            leftFlywheels.set(.5d);
        } else {
            leftFlywheels.set(-.5d);
        }       
    }

    public void stopFlywheels(){
        leftFlywheels.set(0);
    }

    @Override
    public void initDefaultCommand() {
    }
}
