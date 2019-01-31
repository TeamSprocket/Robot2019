/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.commands.instant;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An InstantCommand is a Command that can be run instantaneously during both
 * the autonomous and teleoperated period.
 * 
 * InstantCommands do not have an execute loop whatsoever. Everything must be
 * done in the initialize method. As such, it is impossible to perform actions
 * that require a duration of time to be completed. If your task is not
 * instantaneous, consider using a MacroCommand.
 * 
 * InstantCommands should be modular and execute exactly one task, so that they
 * can easily be composed together within AutonRoutines. Furthermore,
 * InstantCommands should also have parameterized constructors if appropriate.
 * 
 * InstantCommands must override only the initialize method. All other Command
 * methods are declared final.
 */
public abstract class InstantCommand extends Command {
  @Override
  protected abstract void initialize();

  @Override
	protected final boolean isFinished() {
		return true;
  }
  
  @Override
  protected final void execute() {
  }

  @Override
  protected final void end() {
  }

  @Override
  protected final void interrupted() {
  }
}
