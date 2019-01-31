/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import frc.robot.OI;
import frc.robot.Robot;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * TODO: Add docs
 */
public class Shoot extends PersistentCommand {
  public Shoot() {
    requires(Robot.cargoShooter);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(OI.gamepad.getY()) >= 0.1)
      Robot.cargoShooter.setSpeed(OI.gamepad.getY());
    else
      Robot.cargoShooter.setSpeed(0);
  }
}
