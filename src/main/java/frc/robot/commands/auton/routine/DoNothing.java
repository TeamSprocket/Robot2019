/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton.routine;

import frc.util.commands.auton.routine.AutonRoutine;

/**
 * An AutonRoutine that does absolutely nothing. This should be returned by the
 * AutonRoutineFactory if no appropriate action is available or no action is
 * desired.
 */
public class DoNothing extends AutonRoutine {
  public DoNothing() {
  }
}
