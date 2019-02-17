/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.units;

/**
 * // TODO: Add docs for all abstract units
 */
public abstract class Unit {
  protected double value;

  public Unit(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
