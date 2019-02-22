/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import frc.robot.OI;
import frc.robot.subsystems.CargoShooter;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * A PersistentCommand that controls shooting cargo with the left gamepad
 * joystick.
 */
public class Shoot extends PersistentCommand {
  public Shoot() {
    requires(CargoShooter.get());
  }

  @Override
  protected void execute() {
    double speed = OI.Controllers.gamepad.getRawAxis(1);
    CargoShooter.get().setSpeed(Math.signum(OI.deadband(speed)));
  }

  @Override
  protected void terminate() {
    CargoShooter.get().stop();
  }
}
