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

public class SetArm extends MacroCommand {
  private static final double POT_kP = 1, POT_kI = 0, POT_kD = 0;
  private static final double POT_TOLERANCE = 4;
  private static final double POT_RANGE = 0.3;
  private final PIDController controller;

  static {
    SmartDashboard.putNumber("POT_kP", POT_kP);
    SmartDashboard.putNumber("POT_kI", POT_kI);
    SmartDashboard.putNumber("POT_kD", POT_kD);
  }
  
  private final double targetAngle;

  public SetArm(double angle) {
    requires(Robot.arm);

    controller = new PIDController(
      SmartDashboard.getNumber("POT_kP", POT_kP), 
      SmartDashboard.getNumber("POT_kI", POT_kI), 
      SmartDashboard.getNumber("POT_kD", POT_kD), Robot.arm.getPot(),
      Robot.arm::setSpeed);
      
    targetAngle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    controller.setPID(SmartDashboard.getNumber("POT_kP", POT_kP), 
      SmartDashboard.getNumber("POT_kI", POT_kI), 
      SmartDashboard.getNumber("POT_kD", POT_kD));
    controller.setSetpoint(targetAngle);
    controller.setAbsoluteTolerance(POT_TOLERANCE);
    contro
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void terminate() {
  }
}
