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
public class HabPneumatics extends Subsystem {
  private final DoubleSolenoid pistonFrontLeft = new DoubleSolenoid(
      RobotMap.HabPneumatics.FRONT_LEFT_PISTON_FORWARD, RobotMap.HabPneumatics.FRONT_LEFT_PISTON_REVERSE);

  private final DoubleSolenoid pistonFrontRight = new DoubleSolenoid(
    RobotMap.HabPneumatics.FRONT_RIGHT_PISTON_FORWARD, RobotMap.HabPneumatics.FRONT_RIGHT_PISTON_REVERSE);
      
  private final DoubleSolenoid pistonBackLeft = new DoubleSolenoid(
    RobotMap.HabPneumatics.BACK_LEFT_PISTON_FORWARD, RobotMap.HabPneumatics.BACK_LEFT_PISTON_REVERSE);

  private final DoubleSolenoid pistonBackRight = new DoubleSolenoid(
    RobotMap.HabPneumatics.BACK_RIGHT_PISTON_FORWARD, RobotMap.HabPneumatics.BACK_RIGHT_PISTON_REVERSE);

  public boolean isFrontOut() {
    if(pistonFrontLeft.get() == Value.kReverse || pistonFrontRight.get() == Value.kReverse)
      return true;
    else
      return false;
  }

  public boolean isBackOut() {
    if(pistonBackLeft.get() == Value.kReverse || pistonBackRight.get() == Value.kReverse)
      return true;
    else
      return false;
  }

  public void actuateFront() {
    if(isFrontOut()) {
      pistonFrontLeft.set(Value.kReverse);
      pistonFrontRight.set(Value.kReverse);
    }
    else{
      pistonFrontLeft.set(Value.kForward);
      pistonFrontRight.set(Value.kForward);
    }
  }

  public void actuateBack() {
    if(isBackOut()){
      pistonBackLeft.set(Value.kReverse);
      pistonBackRight.set(Value.kReverse);
    }
    else {
      pistonBackLeft.set(Value.kForward);
      pistonBackRight.set(Value.kForward);
    }
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  private static final HabPneumatics instance = new HabPneumatics();

	public static HabPneumatics getInstance() {
		return instance;
	}

	private HabPneumatics() {
	}

}
