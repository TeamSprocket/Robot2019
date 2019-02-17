/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.units.distances;

import frc.util.units.Unit;

/**
 * Add your docs here.
 */
public abstract class Distance extends Unit {
  public Distance(double value) {
    super(value);
  }

  public abstract Meter inMeters();
  public abstract Pulse inPulses();
}
