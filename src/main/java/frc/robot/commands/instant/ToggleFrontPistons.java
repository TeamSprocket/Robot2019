/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.robot.commands.teleop.persistent.Drive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HabClimb;
import frc.util.commands.instant.InstantCommand;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * An InstantCommand that actuates the pistons on the front of the bot.
 */
public class ToggleFrontPistons extends InstantCommand {
  @Override
  protected void initialize() {
    HabClimb.get().actuateFront();
    // PersistentCommand.unbindPersistent(Drivetrain.get());
    // PersistentCommand.bindPersistent(new Drive(true), Drivetrain.get());
    // PersistentCommand.startPersistent(Drivetrain.get());
  }
}

