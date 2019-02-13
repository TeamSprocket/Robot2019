/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.util.commands.instant.InstantCommand;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * An instant command that cancels all currently running macro commands.
 */
public class AbortMacro extends InstantCommand {
  public AbortMacro() {
    requires(Robot.drivetrain);
    requires(Robot.hatchActuator);
    requires(Robot.cargoShooter);
  }

  @Override
  protected void initialize() {
    PersistentCommand.startAllPersistent();
    DriverStation.reportWarning("All MacroCommands aborted!", false);
  }
}
