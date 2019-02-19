/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.units.distances;

/**
 * Add your docs here.
 */
public class Meter extends Distance {
  private static final double TO_ENCODER = 0;

  public Meter(double value) {
    super(value);
  }

  @Override
  public Meter inMeters() {
    return this;
  }

  @Override
  public Pulse inPulses() {
    return new Pulse(value * TO_ENCODER);
  }

  @Override
  public String toString() {
    return value + " meters";
  }
}
