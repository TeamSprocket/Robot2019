/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchActuator extends Subsystem {
  private final DoubleSolenoid piston = new DoubleSolenoid(
      RobotMap.HatchActuator.PISTON_FORWARD, RobotMap.HatchActuator.PISTON_REVERSE);

  public boolean isIn() {
    if(piston.get() == Value.kForward)
      return true;
    else
      return false;
  }

  public void actuate(boolean in) {
    if(in)
      piston.set(Value.kForward);
    else
      piston.set(Value.kReverse);
  }
  
  @Override
  protected void initDefaultCommand() {

  }

  // Singleton instance, getter, and constructor
  private static final HatchActuator instance = new HatchActuator();

	public static HatchActuator getInstance() {
		return instance;
	}

	private HatchActuator() {
	}
}
