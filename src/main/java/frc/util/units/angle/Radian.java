/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.units.angle;

/**
 * Add your docs here.
 */
public class Radian extends Angle {
  public Radian(double value) {
    super(value);
  }

  @Override
  public Degree inDegrees() {
    return new Degree(Math.toDegrees(value));
  }

  @Override
  public Radian inRadians() {
    return this;
  }

  @Override
  public String toString() {
    return value + " radians";
  }
}
