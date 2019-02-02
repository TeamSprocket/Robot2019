/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.macro.MacroCommand;

public class Align extends MacroCommand {
  private double tx, ty, steer_adjust, dis_adjust, baseSpeed;
  private final double kX = 0.05;
  private final double kY = 0.05;

  public Align() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    baseSpeed = SmartDashboard.getNumber("Base Speed: ", 0.05);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    
    if(tx > 0) {
      steer_adjust = kX * tx;
    }
    else {
      steer_adjust = kX * tx;
    }
    dis_adjust = kY * ty;
    double leftSpeed = steer_adjust + dis_adjust;
    double rightSpeed = -steer_adjust - dis_adjust;
    if(Math.abs(leftSpeed) < baseSpeed) {
      if(tx < 0) 
        leftSpeed = -baseSpeed;
      else
        leftSpeed = baseSpeed;
    }
    if(Math.abs(rightSpeed) < baseSpeed) {
      if(tx < 0) 
        rightSpeed = baseSpeed;
      else
        rightSpeed = -baseSpeed;
    }
    Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(tx) <= 1;
  }

  @Override
  protected void interrupted() {
    System.out.println("Interrupted!");
  }
}