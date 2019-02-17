/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.units.angle;

import frc.util.units.Unit;

/**
 * Add your docs here.
 */
public abstract class Angle extends Unit {
  public Angle(double value) {
    super(value);
  }

  public abstract Degree inDegrees();
  public abstract Radian inRadians();
}
