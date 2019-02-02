/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * TODO: Add docs
 */
public class CargoShooter extends Subsystem {
  private final WPI_TalonSRX leftTalon = new WPI_TalonSRX(RobotMap.CargoShooter.LEFT_TALON);
  private final WPI_TalonSRX rightTalon = new WPI_TalonSRX(RobotMap.CargoShooter.RIGHT_TALON);

  public void setSpeed(double speed) {
    leftTalon.set(speed);
    rightTalon.set(speed);
  }

  public void stop() {
    setSpeed(0);
  }

  @Override
  public void initDefaultCommand() {
  }

  // Singleton instance, getter, and constructor
	private static final CargoShooter instance = new CargoShooter();

	public static CargoShooter getInstance() {
		return instance;
	}

	private CargoShooter() {
	}
}