/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.commands.auton.routine;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An AutonRoutine is a complete routine of Commands which are executed in
 * sequence.
 * 
 * Add Commands to the routine in the constructor by using the addSequential
 * and addParallel methods, like any other CommandGroup. Although not
 * enforced (because the addSequential and addParallel methods are declared
 * final), PersistentCommands should never be added to AutonRoutines.
 * 
 * Additionally, AutonRoutines time out automatically after 15 seconds.
 */
public abstract class AutonRoutine extends CommandGroup {
  @Override
	public final void initialize() {
    DriverStation.reportWarning("Launching auton routine: " + this.getClass().getSimpleName(), false);
    setTimeout(15);
  }
  
  @Override
  public final boolean isFinished() {
    return super.isFinished() || isTimedOut();
  }
}
