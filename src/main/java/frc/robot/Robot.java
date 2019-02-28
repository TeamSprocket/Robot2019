/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import java.util.HashMap;
import java.util.Map;

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
import frc.robot.commands.teleop.macro.ActuateHatch;
import frc.robot.commands.teleop.macro.Align;
import frc.robot.commands.teleop.persistent.Drive;
import frc.robot.commands.teleop.persistent.MoveArm;
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
public final class Robot extends TimedRobot {
  private Robot() {
  }

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("ARM_kP", 0.015);
    SmartDashboard.putNumber("ARM_kI", 0.00);
    SmartDashboard.putNumber("ARM_kD", 0.04);

    SmartDashboard.putNumber("ALIGN_SPEED_BASE", 0.2);
    SmartDashboard.putNumber("ALIGN_SPEED_INCREMENT", 0.2);
    SmartDashboard.putNumber("ALIGN_TURN_BASE", 0.0);
    SmartDashboard.putNumber("ALIGN_TURN_INCREMENT", 0.2);
    SmartDashboard.putNumber("ALIGN_MAX_AREA", 15);
    SmartDashboard.putNumber("ALIGN_kP", 0.13);
    SmartDashboard.putNumber("ALIGN_kI", 0.0001);
    SmartDashboard.putNumber("ALIGN_kD", 0.3);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Pot", Arm.get().getPot().get());
    SmartDashboard.putNumber("Pot Rate", Arm.get().getPot().getRate());
    ChickenPotPie.updateAll();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    Arm.get().setSetpoint(Arm.get().getPot().get());

    // Persistent commands
    PersistentCommand.bindPersistent(new Drive(), Drivetrain.get());
    PersistentCommand.bindPersistent(new Shoot(), CargoShooter.get());
    PersistentCommand.bindPersistent(new PIDMoveArm(), Arm.get());
    PersistentCommand.startAllPersistent();

    // Macro/instant commands
    OI.Buttons.toggleActuator.whenPressed(new ActuateHatch());
    OI.Buttons.toggleCompressor.whenPressed(new ToggleCompressor());
    OI.Buttons.toggleFrontPistons.whenPressed(new ToggleFrontPistons());
    OI.Buttons.toggleBackPistons.whenPressed(new ToggleBackPistons());
    OI.Buttons.calibrateArm.whenPressed(new CalibrateArm());
    OI.Buttons.abortMacroPrimary.whenPressed(new AbortMacro());

    // OI.Buttons.moveForward.whenPressed(new MoveForwardGyroEncoder(2));
    new LatchedEventListener(
      () -> OI.Controllers.gamepad.getTriggerAxis(Hand.kLeft) > 0.75,
      () -> {new AbortMacro().start();}
    );

    // Vision
    OI.Buttons.togglePipeline.whenPressed(new TogglePipeline());
    OI.Buttons.alignRobot.whenPressed(new Align());
    

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

  // Singleton instance and getter
  private static final Robot instance = new Robot();

	public static Robot get() {
		return instance;
	}
}
