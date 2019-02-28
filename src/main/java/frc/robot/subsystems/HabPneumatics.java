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
 * Subsystem used to control the pistons to climb the Hab.
 */
public class HabPneumatics extends Subsystem {
  private final DoubleSolenoid frontPistons = new DoubleSolenoid(
    RobotMap.HabPneumatics.FRONT_FORWARD,
    RobotMap.HabPneumatics.FRONT_REVERSE);
  private final DoubleSolenoid backPistons = new DoubleSolenoid(
    RobotMap.HabPneumatics.BACK_FORWARD,
    RobotMap.HabPneumatics.BACK_REVERSE);

  private HabPneumatics() {
  }

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
  private static final HabPneumatics instance = new HabPneumatics();

	public static HabPneumatics get() {
		return instance;
  }
}
