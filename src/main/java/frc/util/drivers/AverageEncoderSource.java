/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.drivers;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class AverageEncoderSource implements PIDSource {
  @Deprecated
  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {
    throw new UnsupportedOperationException();
  }

  @Deprecated
  @Override
  public PIDSourceType getPIDSourceType() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double pidGet() {
    return 0;
	}
}
