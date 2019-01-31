/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

public class FieldConfiguration {
  private LCR startingPosition;
  private Level startingLevel;

  public FieldConfiguration(LCR startingPosition, Level startingLevel) {
    this.startingPosition = startingPosition;
    this.startingLevel = startingLevel;
  }

  public LCR getStartingPosition() {
    return startingPosition;
  }

  public Level getStartingLevel() {
    return startingLevel;
  }
}
