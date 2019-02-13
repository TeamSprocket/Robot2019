/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem used for the talons that move the robot
 */
public class Drivetrain extends Subsystem {
  private final WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(RobotMap.Drivetrain.FRONT_LEFT_TALON);
  private final WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(RobotMap.Drivetrain.FRONT_RIGHT_TALON);
  private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(RobotMap.Drivetrain.BACK_LEFT_TALON);
  private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(RobotMap.Drivetrain.BACK_RIGHT_TALON);

  public static final int RIGHT_ENCODER_A = 0;
  public static final int RIGHT_ENCODER_B = 1;
  public static final int LEFT_ENCODER_A = 2;
  public static final int LEFT_ENCODER_B = 3;

  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private final Encoder leftEncoder = new Encoder(LEFT_ENCODER_A, LEFT_ENCODER_B);
  private final Encoder rightEncoder = new Encoder(RIGHT_ENCODER_A, RIGHT_ENCODER_B);

  
  private Drivetrain() {
    frontRightTalon.setInverted(true);
    backRightTalon.setInverted(true);

    backLeftTalon.follow(frontLeftTalon);
    backRightTalon.follow(frontRightTalon);
	}

  public void setLeft(double speed) {
    frontLeftTalon.set(speed);
  }

  public void setRight(double speed) {
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

  public Encoder getLeftEncoder() {
    return leftEncoder;
  }

  public Encoder getRightEncoder() {
    return rightEncoder;
  }
  
  public double getAverageEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  public double getAverageEncoderRate() {
    return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
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
