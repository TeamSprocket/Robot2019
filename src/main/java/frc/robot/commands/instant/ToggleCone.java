/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.HatchActuator;
import frc.util.commands.instant.InstantCommand;

/**
 * An InstantCommand that toggles the solenoids of the HatchActuator.
 */
public class ToggleCone extends InstantCommand {
  public ToggleCone() {
    requires(HatchActuator.get());
  }

  @Override
  protected void initialize() {
    if(HatchActuator.get().isOpen()) {
      HatchActuator.get().open(false);
      SmartDashboard.putBoolean("Actuator Out", false);
    } else {
      HatchActuator.get().open(true);
      SmartDashboard.putBoolean("Actuator Out", true);
    }
  }
}
