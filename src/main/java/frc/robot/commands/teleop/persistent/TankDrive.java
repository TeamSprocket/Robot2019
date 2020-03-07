/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * A PersistentCommand that controls the drivetrain of the robot using the
 * primary joysticks.
 */
public class TankDrive extends PersistentCommand {
  private static double SPEED_MULTIPLIER = 0.5;
  private boolean squared = false;

  public TankDrive() {
    requires(Drivetrain.get());
  }

  public TankDrive(boolean isSquared) {
    requires(Drivetrain.get());
    squared = isSquared;
  }

  @Override
  protected void execute() {
    double left = OI.deadband(OI.Controllers.leftJoystick.getY());
    double right = OI.deadband(OI.Controllers.rightJoystick.getY());

    if(squared) {
      left = left * left * Math.signum(left);
      right = right * right * Math.signum(right);
    }

    Drivetrain.get().tankDrive(SPEED_MULTIPLIER * left, SPEED_MULTIPLIER * right);
  }

  @Override
  protected void terminate() {
    Drivetrain.get().stop();
  }
}
