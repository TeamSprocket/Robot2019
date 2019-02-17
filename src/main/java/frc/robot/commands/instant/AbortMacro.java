/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CargoShooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchActuator;
import frc.util.commands.instant.InstantCommand;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * An InstantCommand that aborts all currently executing MacroCommands, as well
 * as restarts all bound PersistentCommands.
 */
public class AbortMacro extends InstantCommand {
  public AbortMacro() {
    requires(Drivetrain.get());
    requires(Arm.get());
    requires(HatchActuator.get());
    requires(CargoShooter.get());
  }

  @Override
  protected void initialize() {
    PersistentCommand.startAllPersistent();
    DriverStation.reportWarning("All MacroCommands aborted!", false);
  }
}
