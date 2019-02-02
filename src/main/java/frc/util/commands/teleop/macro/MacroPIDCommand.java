/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.commands.teleop.macro;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * A MacroCommand is a Command that is run during the teleoperated period,
 * temprorarily suspending ongoing PersistentCommands.
 * 
 * It is important to note that running a MacroCommand which requires a
 * subsystem currently being used by a PersistentCommand during the
 * teleoperated period will temporarily suspend the PersistentCommand until
 * the MacroCommand has finished executing. Upon completion, all suspended
 * PersistentCommands will immediately continue.
 * 
 * MacroCommands may be more complex than InstantCommands or AutonCommands,
 * utilizing multiple subsystems to perform more difficult tasks. Nonetheless,
 * they should still be kept short in duration. Furthermore, MacroCommands
 * should also have parameterized constructors if appropriate.
 * 
 * MacroCommands must override all Command methods except end and interrupted.
 */
public abstract class MacroPIDCommand extends PIDCommand {
  private final Set<Subsystem> requirements = new HashSet<>();

  @Override
  protected synchronized void requires(Subsystem subsystem) {
    super.requires(subsystem);
    requirements.add(subsystem);
  }
  public MacroPIDCommand() {
    super(0,0,0);
  }
  
  @Override
  protected final void end() {
    for(Subsystem s : requirements)
      PersistentCommand.startPersistent(s);
    SmartDashboard.putBoolean("MC", false);
  }
}
