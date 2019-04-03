/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


/**
 * A Subsystem used to control the pistons to climb the Hab.
 */
public final class HabClimb extends Subsystem {
  private final DoubleSolenoid frontPistons = new DoubleSolenoid(
    RobotMap.HabClimb.FRONT_FORWARD,
    RobotMap.HabClimb.FRONT_REVERSE);
  private final DoubleSolenoid backPistons = new DoubleSolenoid(
    RobotMap.HabClimb.BACK_FORWARD,
    RobotMap.HabClimb.BACK_REVERSE);

  // private final DigitalInput frontSwitch = new DigitalInput(RobotMap.HabClimb.FRONT_SWITCH);
  // private final DigitalInput backSwitch = new DigitalInput(RobotMap.HabClimb.BACK_SWITCH);

  private HabClimb() {
  }

  // public boolean frontSwitchPressed() {
  //   return frontSwitch.get();
  // }

  // public boolean backSwitchPressed() {
  //   return backSwitch.get();
  // }
  
  public void actuateFront() {
    if(frontPistons.get() == Value.kForward)
      frontPistons.set(Value.kReverse);
    else
      frontPistons.set(Value.kForward);
  }
  
  public void actuateBack() {
    if(backPistons.get() == Value.kForward)
      backPistons.set(Value.kReverse);
    else
      backPistons.set(Value.kForward);
  }

  @Override
  public void initDefaultCommand() {
  }

  // Singleton instance and getter
  private static final HabClimb instance = new HabClimb();

	public static HabClimb get() {
		return instance;
  }
}
