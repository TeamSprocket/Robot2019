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
 * An instant command that toggles the hatch actuator.
 */
public class ToggleActuator extends InstantCommand {
  public ToggleActuator() {
    requires(HatchActuator.get());
  }

  @Override
  protected void initialize() {
    if(HatchActuator.get().isOut()) {
      HatchActuator.get().actuate(false);
      SmartDashboard.putBoolean("Actuator Out", false);
    } else {
      HatchActuator.get().actuate(true);
      SmartDashboard.putBoolean("Actuator Out", true);
    }
  }
}
