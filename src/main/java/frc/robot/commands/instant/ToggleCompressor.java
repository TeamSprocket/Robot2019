/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.instant.InstantCommand;

/**
 * TODO: Add docs
 */
public class ToggleCompressor extends InstantCommand {
  @Override
  protected void initialize() {
    if(Robot.compressor.getClosedLoopControl()) {
      Robot.compressor.setClosedLoopControl(false);
      SmartDashboard.putBoolean("Compressing", false);
    } else {
      Robot.compressor.setClosedLoopControl(true);
      SmartDashboard.putBoolean("Compressing", true);
    }
  }
}
