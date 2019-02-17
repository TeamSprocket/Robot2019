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
public class Pulse extends Distance {
  private static double TO_METER = 819.1875395;

  public Pulse(double value) {
    super(value);
  }

  @Override
  public Meter inMeters() {
    return new Meter(value * TO_METER);
  }

  @Override
  public Pulse inPulses() {
    return this;
  }
}
