/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;


public class DriveSubsystem extends Subsystem {

    //Includes Chassis and Potential Piston Wheels

    WPI_TalonSRX fr, fl, br, bl;
    //SpeedControllerGroup left, right;
    DifferentialDrive drive;

    VisionSubsystem vis;
    public boolean initialAlign;
    public boolean close;

    public DriveSubsystem() {
        fr = new WPI_TalonSRX(RobotMap.Drive.FRONT_RIGHT.get());
        fl = new WPI_TalonSRX(RobotMap.Drive.FRONT_LEFT.get());
        br = new WPI_TalonSRX(RobotMap.Drive.BACK_RIGHT.get());
        bl = new WPI_TalonSRX(RobotMap.Drive.BACK_LEFT.get());

        //left = new SpeedControllerGroup(fl, bl);
        //right = new SpeedControllerGroup(fr, br);
        br.follow(fr);
        bl.follow(fl);

        fr.setInverted(true);
        br.setInverted(true);
        bl.setInverted(true);
        fl.setInverted(true);

        drive = new DifferentialDrive(fl, fr);

        vis = Robot.m_vision;
        initialAlign = false;
        close = false;
    }

    public double getLeftSpeed(){
        return fl.get();
    }

    public double getRightSpeed(){
        return fr.get();
    }

    public void tankDrive() {
        drive.tankDrive(Robot.m_oi.getLeftYAxis(), Robot.m_oi.getRightYAxis());
    }

    //TODO: Test to check speed values
    public void turnLeft() {
        drive.tankDrive(0, .4);
    }

    public void turnRight() {
        drive.tankDrive(.4, 0);
    }

    public void forward() {
        if(vis.getArea() < 15) {
            drive.tankDrive(.6, .6);
        } else {
            drive.tankDrive(.4, .4);
        }
    }

    public void stopDrive() {
        drive.tankDrive(0, 0);
    }

    public void setMotors(double left, double right) {
        fl.set(left);
        fr.set(right);
    }

    public void rotateLeft() {
        while(vis.getX() < -2) {
            turnLeft();
        }
    }

    public void rotateRight() {
        while(vis.getX() > 2) {
            turnRight();
        }
    }

    public void alignStraight() {
        if(!initialAlign) {
            if(vis.getX() < -1.5) {
                drive.tankDrive(.4, -.4);
            } else if(vis.getX() > 1.5) {
                drive.tankDrive(-.4, .4);
            } else if(vis.getArea() != 0) {
                initialAlign = true;
            } else {
                turnLeft();
            }
        } else {
            if(vis.getArea() < 15) {
                if(vis.getX() < -1.5) {
                    turnLeft();
                } else if(vis.getX() > 1.5) {
                    turnRight();
                } else if(vis.getArea() != 0) {
                    forward();
                } else {
                    turnLeft();
                }
            } else {
                if(!close) {
                    setMotors(0, 0);
                    close = true;
                    Timer.delay(0.5);
                } else {
                    if(vis.getX() < -.5) {
                        drive.tankDrive(0, -.2);
                    } else if(vis.getX() > .5) {
                        drive.tankDrive(-.2, 0);
                    } else if(vis.getArea() != 0) {
                        forward();
                    } else {
                        turnLeft();
                    }
                }
            }
        }
    }

    //Also cancel the auton alignment if the right stick button #5 is pressed
    public boolean isFinishedAlign(){
        return vis.getArea() >= 30 || !Robot.m_oi.isJoysticksNeutral();
    }
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
}
