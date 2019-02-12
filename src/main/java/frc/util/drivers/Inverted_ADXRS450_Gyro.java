/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.drivers;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * An inverted gyro which returns inverted rates and angles. Use this class
 * instead of ADXRS450_Gyro if the gyro on the robot is installed upside down.
 */
public class Inverted_ADXRS450_Gyro extends ADXRS450_Gyro {
  @Override
  public double getRate() {
    return -super.getRate();
  }

  @Override
  public double getAngle() {
    return -super.getAngle();
  }
}
