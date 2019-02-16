/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem used for controlling the arm
 */
public class Arm extends Subsystem {
  private final WPI_TalonSRX armTalon = new WPI_TalonSRX(RobotMap.Arm.ARM_TALON);
  private final AnalogPotentiometer pot = new AnalogPotentiometer(RobotMap.Arm.POT, 3600, 0);
  
  @Override
  protected void initDefaultCommand() {
  }
  
  public void setSpeed(double speed) {
    armTalon.set(speed);
  }

  public AnalogPotentiometer getPot() {
    return pot;
  }
  // Singleton instance, getter, and constructor
  private static final Arm instance = new Arm();

  public static Arm getInstance() {
    return instance;
  }
}
