/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.instant.AbortMacro;
import frc.robot.commands.instant.CalibrateArm;
import frc.robot.commands.instant.ToggleBackPistons;
import frc.robot.commands.instant.ToggleCompressor;
import frc.robot.commands.instant.ToggleFrontPistons;
import frc.robot.commands.instant.TogglePipeline;
import frc.robot.commands.teleop.macro.Align;
import frc.robot.commands.teleop.persistent.Drive;
import frc.robot.commands.teleop.persistent.PIDMoveArm;
import frc.robot.commands.teleop.persistent.Shoot;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CargoShooter;
import frc.robot.subsystems.Drivetrain;
import frc.util.commands.teleop.persistent.PersistentCommand;
import frc.util.drivers.ChickenPotPie;
import frc.util.drivers.LatchedEventListener;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Pot", Arm.get().getPot().get());
    SmartDashboard.putNumber("Pot Rate", Arm.get().getPot().getRate());
    ChickenPotPie.updateAll();
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

    // Robot.drivetrain.getLeftEncoder().reset();
    // Robot.drivetrain.getRightEncoder().reset();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    
    SmartDashboard.putNumber("ARM_kP", 0.02);
    SmartDashboard.putNumber("ARM_kI", 0.0);
    SmartDashboard.putNumber("ARM_kD", 0.01);

    // HatchActuator.getInstance().actuate(false);
    
    PersistentCommand.bindPersistent(new Drive(), Drivetrain.get());
    PersistentCommand.bindPersistent(new Shoot(), CargoShooter.get());
    PersistentCommand.bindPersistent(new PIDMoveArm(), Arm.get());
    PersistentCommand.startAllPersistent();

    // // Robot
    // OI.Buttons.toggleActuator.whenPressed(new ToggleActuator());
    OI.Buttons.toggleCompressor.whenPressed(new ToggleCompressor());
    OI.Buttons.abortMacroPrimary.whenPressed(new AbortMacro());
    OI.Buttons.calibrateArm.whenPressed(new CalibrateArm());
    OI.Buttons.alignRobot.whenPressed(new Align());
    // OI.Buttons.moveForward.whenPressed(new MoveForwardGyroEncoder(2));
    // new LatchedEventListener(
    //   () -> OI.Controllers.gamepad.getTriggerAxis(Hand.kLeft) > 0.75,
    //   () -> {new AbortMacro().start();}
    // );

    // // Vision
    OI.Buttons.togglePipeline.whenPressed(new TogglePipeline());
    OI.Buttons.toggleFrontPistons.whenPressed(new ToggleFrontPistons());
    OI.Buttons.toggleBackPistons.whenPressed(new ToggleBackPistons());

    // OI.Buttons.armFeedForwardButton.whenPressed(new FeedForwardArm());

    // OI.Buttons.lowerBoundButton.whenPressed(new SetArm(ArmPosition.TEST));
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    LatchedEventListener.listenAll();
    // System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) + "\t" 
    //   + NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0));
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
