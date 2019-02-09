/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.macro.MacroCommand;

public class MoveForwardv2 extends MacroCommand {
  private PIDController distPID, turnPID;
  private PIDOutputNum dist, turn;
  private PIDSourceEncoders encoders;
  private double targetDistance;
  private double conversion = 819.1875395;  // rotationalUnits/meters

  public MoveForwardv2(double distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    dist = new PIDOutputNum();
    turn = new PIDOutputNum();
    encoders = new PIDSourceEncoders();
    targetDistance = distance * conversion;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("initialized");
    Robot.drivetrain.getLeftEncoder().reset();
    Robot.drivetrain.getRightEncoder().reset();
    Robot.drivetrain.getGyro().reset();

    distPID = new PIDController(SmartDashboard.getNumber("kP_dis", 0), SmartDashboard.getNumber("kI_dis", 0), 
    SmartDashboard.getNumber("kD_dis", 0), encoders, dist);
    turnPID = new PIDController(SmartDashboard.getNumber("kP_str", 0), SmartDashboard.getNumber("kI_str", 0), 
    SmartDashboard.getNumber("kD_str", 0), Robot.drivetrain.getGyro(), turn);

    distPID.setSetpoint(targetDistance);
    distPID.setOutputRange(-0.8, 0.8);
    distPID.enable();

    turnPID.setSetpoint(0);
    turnPID.setOutputRange(-0.2, 0.2);
    turnPID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDrive(dist.getOutput(), turn.getOutput());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(encoders.pidGet() - targetDistance) <= 50 
      && Math.abs(Robot.drivetrain.getLeftEncoder().getRate()) <= 250;
  }

  @Override
  protected void terminate() {
    distPID.disable();
    turnPID.disable();
    Robot.drivetrain.stop();
  }
}

class PIDOutputNum implements PIDOutput {
  private double output;
  public void pidWrite(double output) {
    System.out.println("PID written: " + output);
    this.output = output;
  }
  public double getOutput() {
    return output;
  }
}

class PIDSourceEncoders implements PIDSource {
  private PIDSourceType source = PIDSourceType.kDisplacement;
  public PIDSourceType getPIDSourceType() {
    return source;
  }
  public void setPIDSourceType(PIDSourceType pidSource) {
    source = pidSource;
  }
  public double pidGet() {
    // System.out.println("PID Get! : " + Robot.drivetrain.getLeftEncoder().getDistance());
    return Robot.drivetrain.getLeftEncoder().getDistance();
  }
}
