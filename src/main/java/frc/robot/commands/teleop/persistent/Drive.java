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
 * A persistent command that assigns controls to drive the robot
 */
public class Drive extends PersistentCommand {
  public Drive() {
    requires(Drivetrain.get());
  }

  @Override
  protected void execute() {
    double speed = -OI.Controllers.leftJoystick.getY();
    double turn = OI.Controllers.rightJoystick.getX();
    Drivetrain.get().arcadeDrive(OI.deadband(speed), OI.deadband(turn));
  }

  @Override
  protected void terminate() {
    Drivetrain.get().stop();
  }
}
