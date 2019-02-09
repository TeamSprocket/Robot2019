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
import frc.util.drivers.Inverted_ADXRS450_Gyro;

public class PIDTurn extends MacroPIDCommand {
  private double currentAngle, lastOutput;
  private final double kP = 0;
  private final double kI = 0;
  private final double kD = 0;
  private final double kX = 0.05;
  private final double kY = 0.05;
  private final double finalAngle;

  public PIDTurn(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    finalAngle = angle;
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    lastOutput = 1;
    getPIDController().setSetpoint(0);
    getPIDController().setOutputRange(-0.3, 0.3);
    getPIDController().setPID(SmartDashboard.getNumber("kP", kP), SmartDashboard.getNumber("kI", kI), SmartDashboard.getNumber("kD", kD));
    getPIDController().enable();
  }

  // Called repeatedly when this Command is scheduled to run
  // @Override
  // protected void execute() {
  //   tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  //   ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    
  //   if(tx > 0) {
  //     steer_adjust = kX * tx;
  //   }
  //   else {
  //     steer_adjust = kX * tx;
  //   }
  //   dis_adjust = kY * ty;
  //   double leftSpeed = steer_adjust + dis_adjust;
  //   double rightSpeed = -steer_adjust - dis_adjust;
  //   if(Math.abs(leftSpeed) < baseSpeed) {
  //     if(tx < 0) 
  //       leftSpeed = -baseSpeed;
  //     else
  //       leftSpeed = baseSpeed;
  //   }
  //   if(Math.abs(rightSpeed) < baseSpeed) {
  //     if(tx < 0) 
  //       rightSpeed = baseSpeed;
  //     else
  //       rightSpeed = -baseSpeed;
  //   }
  //   Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
  // }
  @Override
  protected void usePIDOutput(double output) {
    // if(Math.abs(output) < baseSpeed) {
    //   if(tx < 0) 
    //     output = -baseSpeed;
    //   else 
    //     output = baseSpeed;
    // }
    lastOutput = output;
    Robot.drivetrain.arcadeDrive(0, -output);
  }
  @Override
  protected double returnPIDInput() {
    currentAngle = Robot.drivetrain.getGyro().getAngle();
    return finalAngle - currentAngle ;
  } 

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(finalAngle - currentAngle) <= 0.5 || Math.abs(lastOutput) <= 0.08;
  }

  @Override
  protected void interrupted() {
    System.out.println("Interrupted!");
  }
}
