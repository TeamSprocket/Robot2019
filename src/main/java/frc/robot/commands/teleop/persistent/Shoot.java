/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.CargoShooter;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * A persistent command that assigns controls to shoot the cargo
 */
public class Shoot extends PersistentCommand {
  public Shoot() {
    requires(CargoShooter.get());
  }

  @Override
  protected void execute() {
    double speed = OI.Controllers.gamepad.getRawAxis(1);
    SmartDashboard.putNumber("Shooter speed", speed);
    if(Math.abs(speed) >= 0.1)
      CargoShooter.get().setSpeed(speed);
    else
      CargoShooter.get().setSpeed(0);
  }

  @Override
  protected void terminate() {
    CargoShooter.get().stop();
  }
}
