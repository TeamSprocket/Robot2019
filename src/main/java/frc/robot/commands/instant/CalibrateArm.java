/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.instant;

import frc.robot.subsystems.Arm;
import frc.util.commands.instant.InstantCommand;

/**
 * An InstantCommand that calibrates the arm potentiometer, setting the current
 * angle as zero.
 * 
 * Deprecated as of 2/27 because we got a pot and mount that actually works
 * and is reliable so there is not really a need to calibrate it every now and
 * then anymore.
 */
@Deprecated
public class CalibrateArm extends InstantCommand {
  @Override
  protected void initialize() {
    Arm.get().calibrate();
  }
}
