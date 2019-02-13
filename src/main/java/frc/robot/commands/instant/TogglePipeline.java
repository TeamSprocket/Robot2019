/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.util.commands.instant.InstantCommand;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * An instant command that toggles the vision pipeline, switching between the normal and processed view.
 */
public class TogglePipeline extends InstantCommand {
  public TogglePipeline() {
  }

  @Override
  protected void initialize() {
    double pipeline = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
    System.out.println("Current Pipeline: " + pipeline);
    
    if(pipeline == 1)
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    else
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
  }
}
