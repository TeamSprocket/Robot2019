/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.util.commands.instant.InstantCommand;
import frc.robot.subsystems.HabClimb;

/**
 * An InstantCommand that actuates the pistons on the back of the robot.
 */
public class ToggleBackPistons extends InstantCommand {
  @Override
  protected void initialize() {
    HabClimb.get().actuateBack();
  }
}

