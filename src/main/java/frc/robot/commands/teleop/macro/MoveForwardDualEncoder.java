/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.macro.MacroCommand;

public class MoveForwardDualEncoder extends MacroCommand {
  private final double distance;
  private final PIDController leftController, rightController;

  public MoveForwardDualEncoder(double distance) {
    requires(Robot.drivetrain);
    this.distance = distance;
    leftController = new PIDController(
      SmartDashboard.getNumber("Left kP", 1),
      SmartDashboard.getNumber("Left kI", 0),
      SmartDashboard.getNumber("Left kD", 0),
      Robot.drivetrain.getLeftEncoder(),
      Robot.drivetrain::setLeft);
    rightController = new PIDController(
      SmartDashboard.getNumber("Right kP", 1),
      SmartDashboard.getNumber("Right kI", 0),
      SmartDashboard.getNumber("Right kD", 0),
      Robot.drivetrain.getLeftEncoder(),
      Robot.drivetrain::setRight);
  }

  @Override
  protected void initialize() {
    leftController.setSetpoint(
      Robot.drivetrain.getLeftEncoder().getDistance() + distance);
    rightController.setSetpoint(
      Robot.drivetrain.getRightEncoder().getDistance() + distance);
    leftController.setAbsoluteTolerance(50);
    rightController.setAbsoluteTolerance(50);
    
    leftController.setOutputRange(-0.4, 0.4);
    rightController.setOutputRange(-0.4, 0.4);

    leftController.enable();
    rightController.enable();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return leftController.onTarget() && rightController.onTarget();
  }

  @Override
  protected void interrupted() {
  }
}
