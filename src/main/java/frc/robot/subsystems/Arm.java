/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.util.drivers.ChickenPotPie;

/**
 * Subsystem used for controlling the arm
 */
public class Arm extends Subsystem {
  private static final double UPPER_ANGLE_LIMIT = 205, LOWER_ANGLE_LIMIT = -13;

  // Wood arm + hatch
  // private static final double kA1 = 0.208435, kB1 = 70.101, kC1 = 0.0816035;
  // private static final double kA2 = 0.231395, kB2 = 51.8217, kC2 = -0.125988;
  
  // New arm + hatch

  private static final double kA1 = -0.204212, kB1 = 49.1661, kC1 = 0.102572;
  private static final double kA2 = -0.20369, kB2 = 62.8452, kC2 = -0.0627395;
  
  private static final double UPPER_BOUND_WEIGHT = 0.5, LOWER_BOUND_WEIGHT = 0.5;
  
  private static final double OFFSET = -3003 + 2821 - 3145 + 0.7 + 104 + 69;

  private final WPI_TalonSRX armTalon = new WPI_TalonSRX(RobotMap.Arm.ARM_TALON);
  
  private final ChickenPotPie pot = new ChickenPotPie(RobotMap.Arm.POT, 3600, OFFSET);
  private final DigitalInput frontLimitSwitch = new DigitalInput(RobotMap.Arm.FRONT_LIMIT_SWITCH);
  private final DigitalInput backLimitSwitch = new DigitalInput(RobotMap.Arm.BACK_LIMIT_SWITCH);

  private boolean feedForward = true;
  private double setpoint;

  private Arm() {
    armTalon.setNeutralMode(NeutralMode.Brake);
  }
  
  public void setSpeed(double speed) {
    if(((speed > 0) && pot.get() < UPPER_ANGLE_LIMIT) /*&& !backLimitSwitch.get() */
      || ((speed < 0) && pot.get() > LOWER_ANGLE_LIMIT /*&& !frontLimitSwitch.get()*/)) {
      if(feedForward)
        armTalon.set(speed + calculateFeedForward());
      else
        armTalon.set(speed);
    } else {
      if(feedForward)
        armTalon.set(calculateFeedForward());
      else
        armTalon.set(0);
    }
    SmartDashboard.putNumber("Arm Speed", armTalon.get());
  }

  public void stop() {
    setSpeed(0);
  }

  public double getSetpoint() {
    return setpoint;
  }

  public void setSetpoint(double angle) {
    if(angle > UPPER_ANGLE_LIMIT)
      setpoint = UPPER_ANGLE_LIMIT;
    else if(angle < LOWER_ANGLE_LIMIT)
      setpoint = LOWER_ANGLE_LIMIT;
    else
      setpoint = angle;
  }

  public void changeSetpoint(double angle) {
    setSetpoint(setpoint + angle);
  }

  public boolean feedForwardOn() {
    return feedForward;
  }

  public void setFeedForward(boolean feedForward) {
    this.feedForward = feedForward;
  }

  public void calibrate() {
    pot.setOffset(0);
    pot.setOffset(-pot.getRaw());
    Arm.get().setSetpoint(pot.get());
  }
  
  private double calculateFeedForward() {
    double angle = Arm.get().getPot().get();
    double upperBound = kA1 * Math.sin(Math.toRadians(angle + kB1)) + kC1;
    double lowerBound = kA2 * Math.sin(Math.toRadians(angle + kB2)) + kC2;
    return UPPER_BOUND_WEIGHT * upperBound + LOWER_BOUND_WEIGHT * lowerBound;
  }

  public ChickenPotPie getPot() {
    return pot;
  }

  @Override
  protected void initDefaultCommand() {
  }

  // Singleton instance and getter
  private static final Arm instance = new Arm();

  public static Arm get() {
    return instance;
  }
}
