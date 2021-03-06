/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.PCM;
import frc.util.commands.instant.InstantCommand;

/**
 * An InstantCommand used to toggle the closed loop control state of the
 * compressor.
 */
public class ToggleCompressor extends InstantCommand {
  @Override
  protected void initialize() {
    if(PCM.get().getClosedLoopControl()) {
      PCM.get().setClosedLoopControl(false);
      SmartDashboard.putBoolean("Compressing", false);
    } else {
      PCM.get().setClosedLoopControl(true);
      SmartDashboard.putBoolean("Compressing", true);
    }
  }
}
