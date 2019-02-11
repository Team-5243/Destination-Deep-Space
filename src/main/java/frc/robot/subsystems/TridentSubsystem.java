/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class TridentSubsystem extends Subsystem {

    //Includes Cargo and Hatch Mechanisms (Flywheels, Pistons, Compressor)

    private WPI_TalonSRX leftFlywheels, rightFlywheels;
    private DoubleSolenoid hatchPiston;
    private Compressor compressor;

    public TridentSubsystem() {
        leftFlywheels = new WPI_TalonSRX(RobotMap.LEFT_FLYWHEELS.get());
        rightFlywheels = new WPI_TalonSRX(RobotMap.RIGHT_FLYWHEELS.get());

        hatchPiston = new DoubleSolenoid(RobotMap.HATCH_PISTON_F.get(), RobotMap.HATCH_PISTON_R.get());

        try{
            compressor = new Compressor();
            compressor.start();
        } catch (Exception e){
            e.printStackTrace();
        }

        rightFlywheels.follow(leftFlywheels);

        leftFlywheels.setInverted(true);
    }

    public void spinyBois(boolean intake) {
        leftFlywheels.set(intake ? -.5d : 1d);   
    }

    public void stopFlywheels(){
        leftFlywheels.set(0);
    }

    public void toggleHatchPiston() {
		if (hatchPiston.get().equals(Value.kReverse) || hatchPiston.get().equals(Value.kOff)) {
			hatchPiston.set(Value.kForward);
		} else {
			hatchPiston.set(Value.kReverse);
        }
    }

    public void setClosedLoopControl(boolean on) {
		if (compressor != null)
			compressor.setClosedLoopControl(on);
	}
	
	public void disableCompressor() {
		compressor.stop();
    }
    
	public boolean compressorEnabled() {
		return compressor.enabled();
	}

    @Override
    public void initDefaultCommand() {
    }



}
