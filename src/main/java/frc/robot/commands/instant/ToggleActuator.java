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

public class ToggleActuator extends InstantCommand {
  public ToggleActuator() {
    requires(Robot.hatchActuator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.hatchActuator.isIn()) {
      Robot.hatchActuator.actuate(false);
      SmartDashboard.putBoolean("actuatorIn", false);
    } else {
      Robot.hatchActuator.actuate(true);
      SmartDashboard.putBoolean("actuatorIn", true);
    }
  }
}
