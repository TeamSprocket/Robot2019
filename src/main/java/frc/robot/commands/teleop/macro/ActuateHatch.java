/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import frc.util.commands.teleop.macro.MacroCommand;
import frc.robot.subsystems.HatchActuator;

/**
 * TODOL Add docs
 */
public class ActuateHatch extends MacroCommand {
  private static double TIMEOUT = 0.75;

  public ActuateHatch() {
    requires(HatchActuator.get());
  }

  @Override
  protected void initialize() {
    HatchActuator.get().actuate(true);
    setTimeout(TIMEOUT);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void terminate() {
    HatchActuator.get().actuate(false);
  }
}
