/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton.routine;

import frc.util.FieldConfiguration;
import frc.util.commands.auton.routine.AutonRoutine;

/**
 * Add your docs here.
 */
public class AutonRoutineFactory {
  public static enum AutonMode {
    DO_NOTHING
  }

  public static AutonRoutine createRoutine(FieldConfiguration config, AutonMode mode) {
    switch(mode) {
      default:
        return new DoNothing();
    }
  }
}
