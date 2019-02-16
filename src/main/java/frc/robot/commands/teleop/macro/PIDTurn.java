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
 * A macro command to be used in teleop mode that turns the robot a specified angle measure.
 */
public class PIDTurn extends MacroCommand {
  private static final double TURN_kP = 1, TURN_kI = 0, TURN_kD = 0;
  private static final double TOLERANCE = 0.5, RATE_TOLERANCE = 0.08;
  private static final double OUTPUT_RANGE = 0.3;
  
  static {
    SmartDashboard.putNumber("TURN_kP", TURN_kP);
    SmartDashboard.putNumber("TURN_kI", TURN_kI);
    SmartDashboard.putNumber("TURN_kD", TURN_kD);
  }

  private final double finalAngle;
  private double turn;
  // private final PIDController controller;

  public PIDTurn(double angle) {
    requires(Robot.drivetrain);

    // controller = new PIDController(
    //   SmartDashboard.getNumber("TURN_kP", TURN_kP), 
    //   SmartDashboard.getNumber("TURN_kI", TURN_kI), 
    //   SmartDashboard.getNumber("TURN_kD", TURN_kD), 
    //   Robot.drivetrain.getGyro(), o -> {turn = o;});
    
    finalAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // controller.setSetpoint(Robot.drivetrain.getGyro().getAngle() + finalAngle);
    // controller.setOutputRange(-OUTPUT_RANGE, OUTPUT_RANGE);
    // controller.setPID(SmartDashboard.getNumber("TURN_kP", TURN_kP), 
    //   SmartDashboard.getNumber("TURN_kI", TURN_kI), SmartDashboard.getNumber("TURN_kD", TURN_kD));
    // controller.setAbsoluteTolerance(TOLERANCE);
    // controller.enable();
  }

  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDrive(0, -turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return controller.onTarget() && Math.abs(Robot.drivetrain.getAverageEncoderRate()) <= RATE_TOLERANCE;
  return false;
  }



  @Override
  protected void terminate() {
    // controller.disable();
  }
}
