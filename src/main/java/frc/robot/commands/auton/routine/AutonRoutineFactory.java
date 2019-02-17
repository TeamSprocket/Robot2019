/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton.routine;

import frc.util.commands.auton.routine.AutonRoutine;

/**
 * This class provides static factory methods for dynamically creating an
 * appropriate AutonRoutine given a FieldConfiguration and AutonMode
 */
public final class AutonRoutineFactory {
  public static enum AutonMode {
    DO_NOTHING
  }

  public static enum StartPosition {
    LEFT, CENTER, RIGHT;
  }

  public static enum StartLevel {
    LEVEL_ONE, LEVEL_2;
  }

  public static AutonRoutine createRoutine(AutonMode mode, StartPosition pos, StartLevel lev) {
    switch(mode) {
      default:
        return new DoNothing();
    }
  }

  // Private constructor to prevent instantiation
  private AutonRoutineFactory() {
  }
}
