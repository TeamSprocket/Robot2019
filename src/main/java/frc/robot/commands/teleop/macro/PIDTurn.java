/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.util.commands.teleop.macro.MacroCommand;

/**
 * A MacroCommand to that turns the robot a specified angle measure.
 */
public class PIDTurn extends MacroCommand {
  private static final double TURN_kP = 0.035, TURN_kI = 0, TURN_kD = 0.1;
  private static final double TOLERANCE = 0.5, RATE_TOLERANCE = 0.1;
  private static final double OUTPUT_RANGE = 1;
  
  static {
    SmartDashboard.putNumber("TURN_kP", TURN_kP);
    SmartDashboard.putNumber("TURN_kI", TURN_kI);
    SmartDashboard.putNumber("TURN_kD", TURN_kD);
  }

  private final double finalAngle;
  private double turn;
  private final PIDController controller;

  public PIDTurn(double angle) {
    requires(Drivetrain.get());

    controller = new PIDController(
      SmartDashboard.getNumber("TURN_kP", TURN_kP), 
      SmartDashboard.getNumber("TURN_kI", TURN_kI), 
      SmartDashboard.getNumber("TURN_kD", TURN_kD), 
      Drivetrain.get().getGyro(), o -> {turn = o;});
    
    finalAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    controller.setSetpoint(Drivetrain.get().getGyro().getAngle() + finalAngle);
    controller.setOutputRange(-OUTPUT_RANGE, OUTPUT_RANGE);
    controller.setPID(SmartDashboard.getNumber("TURN_kP", TURN_kP), 
      SmartDashboard.getNumber("TURN_kI", TURN_kI), SmartDashboard.getNumber("TURN_kD", TURN_kD));
    controller.setAbsoluteTolerance(TOLERANCE);
    controller.enable();
  }

  @Override
  protected void execute() {
    Drivetrain.get().arcadeDrive(0, turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return controller.onTarget() && Math.abs(Drivetrain.get().getGyro().getRate()) <= RATE_TOLERANCE;
  // return false;
  }



  @Override
  protected void terminate() {
    controller.disable();
  }
}
