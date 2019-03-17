/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * A Subsystem used to control the talons and sensors to drive the robot.
 */
public final class Drivetrain extends Subsystem {
  private final WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(
    RobotMap.Drivetrain.FRONT_LEFT_TALON);
  private final WPI_TalonSRX midLeftTalon = new WPI_TalonSRX(
    RobotMap.Drivetrain.MID_LEFT_TALON);
  private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(
    RobotMap.Drivetrain.BACK_LEFT_TALON);
  private final WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(
    RobotMap.Drivetrain.FRONT_RIGHT_TALON);
  private final WPI_TalonSRX midRightTalon = new WPI_TalonSRX(
    RobotMap.Drivetrain.MID_RIGHT_TALON);
  private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(
    RobotMap.Drivetrain.BACK_RIGHT_TALON);

  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private final Encoder leftEncoder = new Encoder(
    RobotMap.Drivetrain.LEFT_ENCODER_A,
    RobotMap.Drivetrain.LEFT_ENCODER_B);
  private final Encoder rightEncoder = new Encoder(
    RobotMap.Drivetrain.RIGHT_ENCODER_A,
    RobotMap.Drivetrain.RIGHT_ENCODER_B);

  private Drivetrain() {
    frontRightTalon.setInverted(true);
    midRightTalon.setInverted(true);
    backRightTalon.setInverted(true);

    backLeftTalon.follow(frontLeftTalon);
    midLeftTalon.follow(frontLeftTalon);
    backRightTalon.follow(frontRightTalon);
    midRightTalon.follow(frontRightTalon);
	}

  public void setLeft(double speed) {
    frontLeftTalon.set(speed);
  }

  public void setRight(double speed) {
    frontRightTalon.set(speed);
  }

  public void stop() {
    setLeft(0);
    setRight(0);
  }

  public void tankDrive(double left, double right) {
    setLeft(left);
    setRight(right);
  }

  public void arcadeDrive(double speed, double turn) {
    setLeft(speed + turn);
    setRight(speed - turn);
  }

  public ADXRS450_Gyro getGyro() {
    return gyro;
  }

  public Encoder getLeftEncoder() {
    return leftEncoder;
  }

  public Encoder getRightEncoder() {
    return rightEncoder;
  }
  
  @Deprecated
  public double getAverageEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  @Deprecated
  public double getAverageEncoderRate() {
    return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
  }

  @Override
  public void initDefaultCommand() {
  }

  // Singleton instance and getter
	private static final Drivetrain instance = new Drivetrain();

	public static Drivetrain get() {
		return instance;
  }
}
