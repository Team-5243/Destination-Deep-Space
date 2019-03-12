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

public class FlyingHatchSubsystem extends Subsystem {

    //Includes Cargo and Hatch Mechanisms (Flywheels, Pistons, Compressor)

    private WPI_TalonSRX leftFlywheels, rightFlywheels;
    private DoubleSolenoid hatchPiston, middlePiston;
    private Compressor compressor;

    public FlyingHatchSubsystem() {
        leftFlywheels = new WPI_TalonSRX(RobotMap.FlyHatch.LEFT_FLYWHEELS.get());
        rightFlywheels = new WPI_TalonSRX(RobotMap.FlyHatch.RIGHT_FLYWHEELS.get());

        //TODO: Test flipping the forward and reverse values
        hatchPiston = new DoubleSolenoid(RobotMap.FlyHatch.HATCH_TOP_PISTON_F.get(), RobotMap.FlyHatch.HATCH_TOP_PISTON_R.get());
        // hatchBottomPiston = new DoubleSolenoid(RobotMap.FlyHatch.HATCH_DOWN_PISTON_F.get(), RobotMap.FlyHatch.HATCH_DOWN_PISTON_R.get());
        middlePiston = new DoubleSolenoid(RobotMap.FlyHatch.MIDDLE_PISTON_F.get(), RobotMap.FlyHatch.MIDDLE_PISTON_R.get());
        try{
            compressor = new Compressor();
            compressor.start();
        } catch (Exception e){
            e.printStackTrace();
        }

        rightFlywheels.follow(leftFlywheels);
        leftFlywheels.setInverted(true);

        retractAll();
    }

    //Hatch Piston and Middle Piston are reversed so their values opposite
    public void retractAll() {
        hatchPiston.set(Value.kForward);
        middlePiston.set(Value.kReverse);
    }

    public void spinyBois(boolean intake) {
        leftFlywheels.set(intake ? -.5d : 1d);   
    }

    public void stopFlywheels(){
        leftFlywheels.set(0);
    }

    public void neutralFlywheels(){
        leftFlywheels.set(-.3d);
    }

    //The pistons are supposed to move in opposite direction
    public void toggleHatchPiston() {
		if (hatchPiston.get().equals(Value.kReverse) || hatchPiston.get().equals(Value.kOff)) {
            hatchPiston.set(Value.kForward);
            middlePiston.set(Value.kForward);
            // hatchBottomPiston.set(Value.kForward);
		} else {
            hatchPiston.set(Value.kReverse);
            middlePiston.set(Value.kReverse);
            // hatchBottomPiston.set(Value.kReverse);
        }
    }

    public String getTopPiston(){
        return hatchPiston.get() == Value.kForward ? "Forward" : hatchPiston.get() == Value.kReverse ? "Reverse" : "Off";
    }

    public String getMiddlePiston(){
        return middlePiston.get() == Value.kForward ? "Forward" : middlePiston.get() == Value.kReverse ? "Reverse" : "Off";
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
