/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class HabPneumatics extends Subsystem {
  // private final Solenoid frontPistons = new Solenoid(RobotMap.HabPneumatics.FRONT_SOLENOID);
  // private final Solenoid backPistons = new Solenoid(RobotMap.HabPneumatics.BACK_SOLENOID); 
  private final DoubleSolenoid leftPistons = new DoubleSolenoid(RobotMap.HabPneumatics.FRONT_LEFT, RobotMap.HabPneumatics.BACK_LEFT);
  private final DoubleSolenoid rightPistons = new DoubleSolenoid(RobotMap.HabPneumatics.FRONT_RIGHT, RobotMap.HabPneumatics.BACK_RIGHT);

  public void actuateFront() {
    // frontPistons.set(true);
    leftPistons.set(Value.kForward);
    rightPistons.set(Value.kForward);
  }
  
  public void actuateBack() {
    // backPistons.set(true);
    leftPistons.set(Value.kReverse);
    rightPistons.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  // Singleton instance and getter
  private static final HabPneumatics instance = new HabPneumatics();

	public static HabPneumatics get() {
		return instance;
  }
  
  private HabPneumatics() {
  }
}
