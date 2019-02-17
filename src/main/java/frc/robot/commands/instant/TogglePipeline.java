/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.util.commands.instant.InstantCommand;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * An InstantCommand that toggles the vision pipeline, switching between the
 * normal and processed view.
 */
public class TogglePipeline extends InstantCommand {
  public TogglePipeline() {
  }

  @Override
  protected void initialize() {
    if(getPipelineEntry().getDouble(0) == 1) {
      getPipelineEntry().setNumber(0);
      DriverStation.reportWarning("Switched to pipeline 0", false);
    } else {
      getPipelineEntry().setNumber(1);
      DriverStation.reportWarning("Switched to pipeline 1", false);
    }
  }

  private NetworkTableEntry getPipelineEntry() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");
  }
}
