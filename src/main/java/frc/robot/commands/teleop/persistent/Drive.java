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
 * Add your docs here.
 */
public class Drive extends PersistentCommand {
  public Drive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    double speed = -OI.leftJoystick.getY();
    double turn = OI.rightJoystick.getX();

    if(Math.abs(speed) < 0.1)
      speed = 0.0;
    if(Math.abs(turn) < 0.1)
      turn = 0.0;

    Robot.drivetrain.arcadeDrive(speed, turn);
  }
}
