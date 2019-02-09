/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.macro.MacroPIDCommand;

public class MoveForward extends MacroPIDCommand {
  private double baseSpeed;
  private final double kP = 1.5;
  private final double kI = 0.09;
  private final double kD = 1.4;
  private double targetDistance;
  private final double conversion = 819.1875395;  // rotationalUnits/meters
  
  public MoveForward(double distance) {
    requires(Robot.drivetrain);
    targetDistance = distance * conversion;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    baseSpeed = SmartDashboard.getNumber("Base Speed: ", 0.45);
    getPIDController().setSetpoint(targetDistance);
    getPIDController().setOutputRange(-1, 1);
    getPIDController().setPID(SmartDashboard.getNumber("kP", kP), SmartDashboard.getNumber("kI", kI), SmartDashboard.getNumber("kD", kD));
    getPIDController().enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void usePIDOutput(double output) {
    System.out.println(output);
    Robot.drivetrain.arcadeDrive(output, 0);
  }
  @Override
  protected double returnPIDInput() {
    return Robot.drivetrain.getLeftEncoder().getDistance();
  } 

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.drivetrain.getLeftEncoder().getDistance() - targetDistance) <= 50;
  }

  @Override
  protected void interrupted() {
    System.out.println("Interrupted!");
  }
}
