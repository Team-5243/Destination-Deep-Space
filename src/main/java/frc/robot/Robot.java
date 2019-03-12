/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.PIDLiftSubsystem;
import frc.robot.subsystems.FlyingHatchSubsystem;
import frc.robot.subsystems.VisionSubsystem;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static VisionSubsystem m_vision = new VisionSubsystem(); //Parth, Noli tenere (don't touch). It makes us unable to deploy - Rudo
                                                                  //Rudo, I know what that means. Don't tell me what to do - Parth
                                                                  //Quack - Thai
                                                                  //Only on Tuesdays, Thai! - Jason
  public static DriveSubsystem m_drivetrain = new DriveSubsystem();
  public static FlyingHatchSubsystem m_flyhatch = new FlyingHatchSubsystem();
  public static ClimbSubsystem m_climb = new ClimbSubsystem();
  public static LiftSubsystem m_lift = new LiftSubsystem();
  public static PIDLiftSubsystem m_lift_pid = new PIDLiftSubsystem(1d, 1d, 1d, 0d, -.5, .5, false);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    //m_lift.resetEncoder();
    //m_lift_pid.resetEncoder();
    updateSmartDashboard();
  }

  public void updateSmartDashboard(){
    SmartDashboard.putNumber("Encoder Value", m_lift.getDistance());
    SmartDashboard.putNumber("Lift Speed", m_lift.getLiftSpeed());
    SmartDashboard.putString("Hatch Solenoid Mode", m_flyhatch.getTopPiston());
    SmartDashboard.putString("Hatch Middle Solenoid", m_flyhatch.getMiddlePiston());
    SmartDashboard.putString("Front Climb Solenoid Mode", m_climb.getFrontLeftPiston());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //System.out.println("Encoder value: " + m_lift_pid.getDistance());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    m_flyhatch.disableCompressor();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    //Ensure all pistons are retracted and limelight camera is set to driver mode with LED turns off.
    m_flyhatch.retractAll();
    m_vision.setCamMode(1);
    m_vision.setLed(1);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    //Auto Compressor
    m_flyhatch.setClosedLoopControl(true);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
