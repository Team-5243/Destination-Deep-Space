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
    static final double MAX_ACCELERATION = 0.00001;
    WPI_TalonSRX fr, fl, br, bl;
    //SpeedControllerGroup left, right;
    DifferentialDrive drive;
    VisionSubsystem vis;

    //Timer runtime;
    //public double previousTimeStamp;

    public DriveSubsystem() {
        fr = new WPI_TalonSRX(RobotMap.frontRight.get());
        fl = new WPI_TalonSRX(RobotMap.frontLeft.get());
        br = new WPI_TalonSRX(RobotMap.backRight.get());
        bl = new WPI_TalonSRX(RobotMap.backLeft.get());

        vis = Robot.m_vision;

        //runtime = new Timer();
        //left = new SpeedControllerGroup(fl, bl);
        //right = new SpeedControllerGroup(fr, br);
        br.follow(fr);
        bl.follow(fl);

        // fr.setSafetyEnabled(false);
        // fl.setSafetyEnabled(false);
        // br.setSafetyEnabled(false);
        // bl.setSafetyEnabled(false);


        fr.setInverted(true);
        br.setInverted(true);
        bl.setInverted(true);
        fl.setInverted(true);

        drive = new DifferentialDrive(fl, fr);
    }

    public void resetTimer() {
        //runtime.reset();
    }

    public void startTimer() {
        //runtime.start();
    }

    public void tankDrive() {
        drive.tankDrive(Robot.m_oi.getLeft().getY(), Robot.m_oi.getRight().getY());
    }

    // public double getLeftSpeed(){
    //     return fl.get();
    // }

    // public double getRightSpeed(){
    //     return fr.get();
    // }

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
        drive.tankDrive(.35, -.35);
        //drive.tankDrive(fl.get(), fr.get() > -.7 ? fr.get() - 0.01 : -.7);
    }

    public void turnRight() {
        //slowStartStop(-.35, .35);
        drive.tankDrive(-.35, .35);
        //drive.tankDrive(fl.get() > -.7 ? fl.get() - 0.01 : -.7, fr.get());
    }

    public void forward() {
        //slowStartStop(-.7, -.7);
        drive.tankDrive(-.7, -.7);
    }

    public void stop() {
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
        
        if(vis.getX() < -2) {
            turnLeft();
        } else if(vis.getX() > 2) {
            turnRight();
        } else {
            forward();
        }

        // while(vis.getArea() <= 80) {
        //     if(vis.getX() < -2)
        //       turnLeft();
        //     else if(vis.getX() > 2)
        //       turnRight();
        //     else
        //       forward();  
        //   }
        //   stop();

        //previousTimeStamp = runtime.get();
    }

    public boolean isFinishedAlign(){
        return vis.getArea() >= 80;
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
