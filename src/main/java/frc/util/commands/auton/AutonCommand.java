/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.commands.auton;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An AutonCommand is a Command that can be run during the autonomous period.
 * 
 * AutonCommands should be modular and execute exactly one task, so that they
 * can easily be composed together within AutonRoutines. Furthermore,
 * AutonCommands should also have parameterized constructors if appropriate.
 * 
 * AutonCommands must override all Command methods.
 */
public abstract class AutonCommand extends Command {
  @Override
	protected abstract void initialize();

	@Override
	protected abstract void execute();

  @Override
  protected abstract boolean isFinished();

	@Override
	protected abstract void end();

	@Override
  protected abstract void interrupted();
}
