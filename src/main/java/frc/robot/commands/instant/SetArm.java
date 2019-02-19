/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.robot.subsystems.Arm;
import frc.util.commands.instant.InstantCommand;
import frc.util.units.angle.Angle;

public class SetArm extends InstantCommand {
  private final Angle angle;

  public SetArm(Angle angle) {
    this.angle = angle;
  }

  @Override
  protected void initialize() {
    Arm.get().setSetpoint(angle.inDegrees().getValue());
  }
}
