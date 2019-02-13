/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem used for controlling the arm
 */
public class Arm extends Subsystem {
  private final AnalogPotentiometer pot = new AnalogPotentiometer(RobotMap.Arm.POT, 720, 0);

  public AnalogPotentiometer getPot() {
    return pot;
  }

  @Override
  protected void initDefaultCommand() {
  }

  // Singleton instance, getter, and constructor
  private static final Arm instance = new Arm();

  public static Arm getInstance() {
  return instance;
  }
}
