/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;

/**
 * TODO: Add docs
 */
public class Drivetrain extends Subsystem {
  // TODO: Decide to use WPI_TalonSRX or TalonSRX
  private final WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(
      RobotMap.Drivetrain.FRONT_LEFT_TALON);
  private final WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(
      RobotMap.Drivetrain.FRONT_RIGHT_TALON);
  private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(
      RobotMap.Drivetrain.BACK_LEFT_TALON);
  private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(
			RobotMap.Drivetrain.BACK_RIGHT_TALON);

  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private final Encoder encoderLeft = new Encoder(RobotMap.Sensor.LEFT_ENCODER_A, RobotMap.Sensor.LEFT_ENCODER_B);

  private Drivetrain() {
    // TODO: Check if this works
    frontRightTalon.setInverted(true);
    backRightTalon.setInverted(true);

    backLeftTalon.follow(frontLeftTalon);
    backRightTalon.follow(frontRightTalon);
	}

  private void setLeft(double speed) {
    frontLeftTalon.set(speed);
  }

  private void setRight(double speed) {
    frontRightTalon.set(speed);
  }

  public void tankDrive(double left, double right) {
    setLeft(left);
    setRight(right);
  }

  public void arcadeDrive(double speed, double turn) {
    setLeft(speed + turn);
    setRight(speed - turn);
  }

  public void stop() {
    setLeft(0);
    setRight(0);
  }

  public ADXRS450_Gyro getGyro() {
    return gyro;
  }

  public Encoder getEncoder(){
    return encoderLeft;
  }

  @Override
  public void initDefaultCommand() {
  }

  // Singleton instance, getter, and constructor
	private static final Drivetrain instance = new Drivetrain();

	public static Drivetrain getInstance() {
		return instance;
	}
}
