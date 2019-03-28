/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.instant.AbortMacro;
import frc.robot.commands.instant.SetArm;
import frc.robot.commands.instant.SetVisionMode;
import frc.robot.commands.instant.ToggleActuator;
import frc.robot.commands.instant.ToggleBackPistons;
import frc.robot.commands.instant.ToggleCompressor;
import frc.robot.commands.instant.ToggleCone;
import frc.robot.commands.instant.ToggleFrontPistons;
import frc.robot.commands.teleop.macro.Align;
import frc.robot.commands.teleop.macro.PIDTurn;
import frc.robot.commands.teleop.persistent.Drive;
import frc.robot.commands.teleop.persistent.PIDMoveArm;
import frc.robot.commands.teleop.persistent.Shoot;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CargoShooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.PCM;
import frc.util.commands.teleop.persistent.PersistentCommand;
import frc.util.drivers.ChickenPotPie;
import frc.util.drivers.LatchedEventListener;
import frc.util.units.angle.Degree;

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
    PersistentCommand.bindPersistent(new Drive(), Drivetrain.get());
    PersistentCommand.unbindPersistent(Drivetrain.get());
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Pot", Arm.get().getPot().get());
    SmartDashboard.putNumber("Pot Rate", Arm.get().getPot().getRate());
    SmartDashboard.putNumber("Distance Estimate", Limelight.get().getDistanceEstimate());
    SmartDashboard.putNumber("ta0", Limelight.get().getTa0());
    SmartDashboard.putNumber("ta1", Limelight.get().getTa1());
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
    Drivetrain.get().getGyro().reset();
    teleopInit();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    LatchedEventListener.listenAll();
  }

  @Override
  public void teleopInit() {
    Arm.get().setSetpoint(Arm.get().getPot().get());
    PCM.get().setClosedLoopControl(true);

    // Persistent commands
    PersistentCommand.bindPersistent(new Drive(), Drivetrain.get());
    PersistentCommand.bindPersistent(new Shoot(), CargoShooter.get());
    PersistentCommand.bindPersistent(new PIDMoveArm(), Arm.get());
    PersistentCommand.startAllPersistent();

    // Macro/instant commands
    OI.Buttons.toggleActuator.whenPressed(new ToggleActuator());
    OI.Buttons.toggleCone.whenPressed(new ToggleCone());
    OI.Buttons.toggleCompressor.whenPressed(new ToggleCompressor());
    OI.Buttons.toggleFrontPistons.whenPressed(new ToggleFrontPistons());
    OI.Buttons.toggleBackPistons.whenPressed(new ToggleBackPistons());
    // OI.Buttons.calibrateArm.whenPressed(new CalibrateArm());
    OI.Buttons.abortMacroPrimary.whenPressed(new AbortMacro());

    OI.Buttons.groundIntake.whenPressed(new SetArm(new Degree(35)));

    OI.Buttons.turn90.whenPressed(new PIDTurn(-90));
    OI.Buttons.turnN90.whenPressed(new PIDTurn(90));

    new LatchedEventListener(
      () -> OI.Controllers.gamepad.getTriggerAxis(Hand.kLeft) > 0.75,
      () -> {new AbortMacro().start();}
    );

    // Vision
    OI.Buttons.driverPipeline.whenPressed(new SetVisionMode(false));
    OI.Buttons.visionPipeline.whenPressed(new SetVisionMode(true));
    OI.Buttons.alignRobot.whenPressed(new Align());
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    LatchedEventListener.listenAll();
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
