/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.robot.subsystems.Limelight;
import frc.util.commands.instant.InstantCommand;

/**
 * An InstantCommand that toggles the vision pipeline, switching between the
 * driver and vision view.
 */
public class TogglePipeline extends InstantCommand {
  public TogglePipeline() {
  }

  @Override
  protected void initialize() {
    Limelight.get().toggleCamMode();
  }
}
