/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import frc.util.commands.teleop.macro.MacroCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchActuator;

/**
 * A MacroCommand that actuates the HatchActuator outward, delays for a
 * timeout, then unactuates the HatchActuator inward.
 */
public class ActuateHatch extends MacroCommand {
  private static double TIMEOUT = 0.5, MIN_ANGLE = 60;

  public ActuateHatch() {
    requires(HatchActuator.get());
    requires(Drivetrain.get());
  }

  @Override
  protected void initialize() {
    if(Arm.get().getPot().get() > MIN_ANGLE) {
      HatchActuator.get().actuate(true);
      Drivetrain.get().arcadeDrive(-0.125, 0);
      setTimeout(TIMEOUT);
    } else {
      setTimeout(0);
    }
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
