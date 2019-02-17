/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.util.drivers.ChickenPotPie;

/**
 * Subsystem used for controlling the arm
 */
public class Arm extends Subsystem {
  private final WPI_TalonSRX armTalon = new WPI_TalonSRX(RobotMap.Arm.ARM_TALON);
  private final ChickenPotPie pot = new ChickenPotPie(RobotMap.Arm.POT, 3600, 0);
  
  private final DigitalInput frontLimitSwitch = new DigitalInput(RobotMap.Arm.FRONT_LIMIT_SWITCH);
  private final DigitalInput backLimitSwitch = new DigitalInput(RobotMap.Arm.BACK_LIMIT_SWITCH);

  private Arm() {
  }

  @Override
  protected void initDefaultCommand() {
  }
  
  public void setSpeed(double speed) {
    armTalon.set(speed);
  }

  public void stop() {
    setSpeed(0);
  }

  public void calibrate() {
    pot.setOffset(0);
    pot.setOffset(-pot.get());
  }
  
  public ChickenPotPie getPot() {
    return pot;
  }

  public DigitalInput getFrontLimitSwitch() {
    return frontLimitSwitch;
  }

  public DigitalInput getBackLimitSwitch() {
    return backLimitSwitch;
  }

  // Singleton instance and getter
  private static final Arm instance = new Arm();

  public static Arm get() {
    return instance;
  }
}
