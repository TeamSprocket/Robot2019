/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.util.subsystems.Testable;

/**
 * Add your docs here.
 */
public class TestSubsystem extends Subsystem implements Testable {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void test() {

  }

  // Singleton instance, getter, and constructor
	private static final TestSubsystem instance = new TestSubsystem();

	public static TestSubsystem getInstance() {
		return instance;
	}

	private TestSubsystem() {
	}
}
