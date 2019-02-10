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
    this.distance = distance * 819.1875395;
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
      Robot.drivetrain.getRightEncoder(),
      Robot.drivetrain::setRight);
  }

  @Override
  protected void initialize() {
    leftController.setSetpoint(
      Robot.drivetrain.getLeftEncoder().getDistance() + distance);
    rightController.setSetpoint(
      Robot.drivetrain.getRightEncoder().getDistance() + distance);

    System.out.println("Left current: " + Robot.drivetrain.getLeftEncoder().getDistance() +
      " target: " + leftController.getSetpoint());
    System.out.println("Right current: " + Robot.drivetrain.getRightEncoder().getDistance() +
      " target: " + rightController.getSetpoint());
      
    leftController.setAbsoluteTolerance(25);
    rightController.setAbsoluteTolerance(25);

    leftController.setOutputRange(-0.4, 0.4);
    rightController.setOutputRange(-0.4, 0.4);

    leftController.enable();
    rightController.enable();
  }

  @Override
  protected void execute() {
    // System.out.println(leftController.get() + "\t" + rightController.get());
  }

  @Override
  protected boolean isFinished() {
    return leftController.onTarget() && rightController.onTarget()
        && Math.abs(Robot.drivetrain.getLeftEncoder().getRate()) <= 400
        && Math.abs(Robot.drivetrain.getRightEncoder().getRate()) <= 400;
  }

  @Override
  protected void terminate() {
    leftController.disable();
    rightController.disable();
    SmartDashboard.putBoolean("Dual Running", false);
  }
}
