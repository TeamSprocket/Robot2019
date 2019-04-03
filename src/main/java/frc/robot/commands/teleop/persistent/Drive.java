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
public class Drive extends PersistentCommand {
  private static double SPEED_MULTIPLIER = 1.0, TURN_MULTIPLIER = 0.8;
  private boolean squared = false;

  public Drive() {
    requires(Drivetrain.get());
  }

  public Drive(boolean isSquared) {
    requires(Drivetrain.get());
    squared = isSquared;
  }

  @Override
  protected void execute() {
    double speed = -OI.Controllers.leftJoystick.getY();
    double turn = OI.Controllers.rightJoystick.getX();

    if(squared) {
      speed = speed * speed * Math.signum(speed);
      turn = turn * turn * Math.signum(turn);
    }

    Drivetrain.get().arcadeDrive(SPEED_MULTIPLIER * OI.deadband(speed),
    TURN_MULTIPLIER * OI.deadband(turn));
  }

  @Override
  protected void terminate() {
    Drivetrain.get().stop();
  }
}
