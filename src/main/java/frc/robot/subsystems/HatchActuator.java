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
 * A Subsystem used to actuate the pistons that place the hatch on the Cargo
 * Ship or Rocket.
 */
public final class HatchActuator extends Subsystem {
  private final DoubleSolenoid conePiston = new DoubleSolenoid(
    RobotMap.HatchActuator.CONE_PISTON_FORWARD,
    RobotMap.HatchActuator.CONE_PISTON_REVERSE);
  private final DoubleSolenoid actuatorPiston = new DoubleSolenoid(
    RobotMap.HatchActuator.ACTUATOR_PISTON_FORWARD,
    RobotMap.HatchActuator.ACTUATOR_PISTON_REVERSE);

  private HatchActuator() {
  }
  
  public boolean isOpen() {
    if(conePiston.get() == Value.kReverse)
      return true;
    else
      return false;
  }

  public boolean isActuated() {
    if(actuatorPiston.get() == Value.kReverse)
      return true;
    else
      return false;
  }

  public void actuate(boolean out) {
    if(out)
      actuatorPiston.set(Value.kReverse);
    else
      actuatorPiston.set(Value.kForward);
  }

  public void open(boolean open) {
    if(open)
      conePiston.set(Value.kReverse);
    else
      conePiston.set(Value.kForward);
  }
  
  @Override
  protected void initDefaultCommand() {
  }

  // Singleton instance and getter
  private static final HatchActuator instance = new HatchActuator();

	public static HatchActuator get() {
		return instance;
	}
}
