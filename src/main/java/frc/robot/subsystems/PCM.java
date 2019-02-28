/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem used to control the Pneumatics Control Module.
 */
public class PCM extends Subsystem {
  private final Compressor compressor = new Compressor(0);

  private PCM() {
  }

  public boolean getClosedLoopControl() {
    return compressor.getClosedLoopControl();
  }

  public void setClosedLoopControl(boolean on) {
    compressor.setClosedLoopControl(on);
  }

  @Override
  public void initDefaultCommand() {
  }

  // Singleton instance and getter
  private static final PCM instance = new PCM();

	public static PCM get() {
		return instance;
  }
}
