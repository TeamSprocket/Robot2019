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

/**
 * A macro command to be used in teleop mode that moves the robot forward for a specified distance, 
 * utilizing an encoder on each side to keep the robot moving straight.
 * TODO: Make it work
 */
public class MoveForwardDualEncoder extends MacroCommand {
  private static final double LEFT_TOLERANCE = 25, RIGHT_TOLERANCE = 25;
  private static final double LEFT_RATE_TOLERANCE = 400, RIGHT_RATE_TOLERANCE = 400;
  private static final double LEFT_OUTPUT_RANGE = 0.4, RIGHT_OUTPUT_RANGE = 0.4;
  private static final double LEFT_kP = 0.4, LEFT_kI = 0.09, LEFT_kD = 1.6;
  private static final double RIGHT_kP = 1.5, RIGHT_kI = 0.5, RIGHT_kD = 1.5;
  
  // TODO: See if this works
  static {
    SmartDashboard.putNumber("LEFT_kP", LEFT_kP);
    SmartDashboard.putNumber("LEFT_kI", LEFT_kI);
    SmartDashboard.putNumber("LEFT_kD", LEFT_kD);
    SmartDashboard.putNumber("RIGHT_kP", RIGHT_kP);
    SmartDashboard.putNumber("RIGHT_kI", RIGHT_kI);
    SmartDashboard.putNumber("RIGHT_kD", RIGHT_kD);
  }

  private final double distance;
  // private final PIDController leftController, rightController;

  public MoveForwardDualEncoder(double distance) {
    requires(Robot.drivetrain);
    this.distance = distance * 819.1875395;
    // leftController = new PIDController(
    //   SmartDashboard.getNumber("LEFT_kP", LEFT_kP),
    //   SmartDashboard.getNumber("LEFT_kI", LEFT_kI),
    //   SmartDashboard.getNumber("LEFT_kD", LEFT_kD),
    //   Robot.drivetrain.getLeftEncoder(),
    //   Robot.drivetrain::setLeft);
    // rightController = new PIDController(
    //   SmartDashboard.getNumber("RIGHT_kP", RIGHT_kP),
    //   SmartDashboard.getNumber("RIGHT_kI", RIGHT_kI),
    //   SmartDashboard.getNumber("RIGHT_kD", RIGHT_kD),
    //   Robot.drivetrain.getRightEncoder(),
    //   Robot.drivetrain::setRight);
  }

  @Override
  protected void initialize() {
    // leftController.setSetpoint(
    //   Robot.drivetrain.getLeftEncoder().getDistance() + distance);
    // rightController.setSetpoint(
    //   Robot.drivetrain.getRightEncoder().getDistance() + distance);

    // System.out.println("Left current: " + Robot.drivetrain.getLeftEncoder().getDistance() +
    //   " target: " + leftController.getSetpoint());
    // System.out.println("Right current: " + Robot.drivetrain.getRightEncoder().getDistance() +
    //   " target: " + rightController.getSetpoint());
      
    // leftController.setAbsoluteTolerance(LEFT_TOLERANCE);
    // rightController.setAbsoluteTolerance(RIGHT_TOLERANCE);

    // leftController.setOutputRange(-LEFT_OUTPUT_RANGE, LEFT_OUTPUT_RANGE);
    // rightController.setOutputRange(-RIGHT_OUTPUT_RANGE, RIGHT_OUTPUT_RANGE);

    // leftController.enable();
    // rightController.enable();
  }

  @Override
  protected void execute() {
    // System.out.println(leftController.get() + "\t" + rightController.get());
  }

  @Override
  protected boolean isFinished() {
    return false;
    // return leftController.onTarget() && rightController.onTarget()
    //     && Math.abs(Robot.drivetrain.getLeftEncoder().getRate()) <= LEFT_RATE_TOLERANCE
    //     && Math.abs(Robot.drivetrain.getRightEncoder().getRate()) <= RIGHT_RATE_TOLERANCE;
  }

  @Override
  protected void terminate() {
    // leftController.disable();
    // rightController.disable();
    // SmartDashboard.putBoolean("Dual Running", false);
  }
}
