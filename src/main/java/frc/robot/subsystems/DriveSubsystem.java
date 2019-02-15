/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;


public class DriveSubsystem extends Subsystem {
    //static final double MAX_ACCELERATION = 0.00001;
    WPI_TalonSRX fr, fl, br, bl;
    //SpeedControllerGroup left, right;
    DifferentialDrive drive;

    VisionSubsystem vis;
    public boolean initialAlign;
    public boolean close;

    public DriveSubsystem() {
        fr = new WPI_TalonSRX(RobotMap.Motors.frontRight.get());
        fl = new WPI_TalonSRX(RobotMap.Motors.frontLeft.get());
        br = new WPI_TalonSRX(RobotMap.Motors.backRight.get());
        bl = new WPI_TalonSRX(RobotMap.Motors.backLeft.get());

        //left = new SpeedControllerGroup(fl, bl);
        //right = new SpeedControllerGroup(fr, br);

        fr.setInverted(true);
        br.setInverted(true);
        bl.setInverted(true);
        fl.setInverted(true);

        br.follow(fr);
        bl.follow(fl);

        // fr.setSafetyEnabled(false);
        // fl.setSafetyEnabled(false);
        // br.setSafetyEnabled(false);
        // bl.setSafetyEnabled(false);

        drive = new DifferentialDrive(fl, fr);

        vis = Robot.m_vision;
        initialAlign = false;
        close = false;
    }

    public void tankDrive() {
        drive.tankDrive(Robot.m_oi.getLeft().getY(), Robot.m_oi.getRight().getY());
    }

    public double getLeftSpeed(){
        return fl.get();
    }

    public double getRightSpeed(){
        return fr.get();
    }

    public void slowStartStop(double leftDesiredSpeed, double rightDesiredSpeed) {
        //double leftSpeed = fl.get();
        //double rightSpeed = fr.get();

        //double leftAcceleration = (leftDesiredSpeed - leftSpeed); // / (runtime.get() - previousTimeStamp);
        //double rightAcceleration = (rightDesiredSpeed - rightSpeed); // / (runtime.get() - previousTimeStamp);
        
        //drive.tankDrive(Math.abs(leftAcceleration) > MAX_ACCELERATION ? (leftSpeed + MAX_ACCELERATION) : (leftSpeed + leftAcceleration), 
        //                Math.abs(rightAcceleration) > MAX_ACCELERATION ? (rightSpeed + MAX_ACCELERATION) : (rightSpeed + rightAcceleration));
    }

    public void turnLeft() {
        //slowStartStop(.35, -.35);
        //double speed = Math.pow(vis.getX(), 2) / 512 + 0.25;
        //drive.tankDrive(speed, -speed);
        drive.tankDrive(0, -.5);
        //drive.tankDrive(fl.get(), fr.get() > -.7 ? fr.get() - 0.1 : -.7);
    }

    public void turnRight() {
        //slowStartStop(-.35, .35);
        //double speed = Math.pow(vis.getX(), 2) / 512 + 0.25;
        //drive.tankDrive(-speed, speed);
        drive.tankDrive(-.5, 0);
        //drive.tankDrive(fl.get() > -.7 ? fl.get() - 0.1 : -.7, fr.get());
    }

    public void forward() {
        //slowStartStop(-.7, -.7);
        if(vis.getArea() < 15) {
            drive.tankDrive(-.6, -.6);
        } else {
            drive.tankDrive(-.3, -.3);
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
        // forward();
        // Timer.delay(.5);
        // rotateLeft();
        // rotateRight();

        if(!initialAlign) {
            if(vis.getX() < -1.5) {
                drive.tankDrive(.5, -.5);
            } else if(vis.getX() > 1.5) {
                drive.tankDrive(-.5, .5);
            } else if(vis.getArea() != 0) {
                initialAlign = true;
            } else {
                turnLeft();
            }
        } else {
            if(vis.getArea() < 1) {
                if(vis.getX() < -3) {
                    turnLeft();
                } else if(vis.getX() > 3) {
                    turnRight();
                } else if(vis.getArea() != 0) {
                    forward();
                } else {
                    stopDrive();
                }
            } else {
                // if(!close) {
                //     setMotors(0, 0);
                //     close = true;
                //     Timer.delay(0.5);
                // } else {
                    if(vis.getX() < -1.5) {
                        drive.tankDrive(0, -.45);
                    } else if(vis.getX() > 1.5) {
                        drive.tankDrive(-.45, 0);
                    } else if(vis.getArea() != 0) {
                        forward();
                    } else {
                        stopDrive();
                    }
                //}
            }
        }
    }

    public boolean isFinishedAlign(){
        return vis.getArea() >= 3 /*30 <- 45*/ || !Robot.m_oi.isJoysticksNeutral();
    }

    /*
      while(m_vision.getArea() <= 80) {
        if(m_vision.getX() < -2) {
          m_drivetrain.turnLeft();
        }
        else if(m_vision.getX() > 2)
          m_drivetrain.turnRight();
        else
          m_drivetrain.forward();  
      }
      m_drivetrain.stop();
    */

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
}
