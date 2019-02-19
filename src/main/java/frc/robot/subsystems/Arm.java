/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.util.drivers.ChickenPotPie;

/**
 * Subsystem used for controlling the arm
 */
public class Arm extends Subsystem {
  private final WPI_TalonSRX armTalon = new WPI_TalonSRX(RobotMap.Arm.ARM_TALON);
  private final ChickenPotPie pot;
  
  private final DigitalInput frontLimitSwitch = new DigitalInput(RobotMap.Arm.FRONT_LIMIT_SWITCH);
  private final DigitalInput backLimitSwitch = new DigitalInput(RobotMap.Arm.BACK_LIMIT_SWITCH);

  private Arm() {
    AnalogInput potInput = new AnalogInput(RobotMap.Arm.POT);

    pot = new ChickenPotPie(potInput, 3600, 0);

    armTalon.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  protected void initDefaultCommand() {
  }
  
  public void setSpeed(double speed) {
    armTalon.set(speed - getFeedForward());
    SmartDashboard.putNumber("Arm Speed", speed - getFeedForward());
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
  
  private static final double a1 = 0.208435, b1 = 70.101, c1 = 0.0816035;
  private static final double a2 = 0.231395, b2 = 51.8217, c2 = -0.125988;
  
  private double getFeedForward() {
    double angle = Arm.get().getPot().get();

    double upperBound = a1 * Math.sin(Math.toRadians(angle + b1)) + c1;
    double lowerBound = a2 * Math.sin(Math.toRadians(angle + b2)) + c2;

    return 0.45 * upperBound + 0.3 * lowerBound;
  }

  // Singleton instance and getter
  private static final Arm instance = new Arm();

  public static Arm get() {
    return instance;
  }
}
