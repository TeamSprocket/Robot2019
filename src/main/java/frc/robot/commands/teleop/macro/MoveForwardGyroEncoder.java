/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.macro.MacroCommand;

public class MoveForwardGyroEncoder extends MacroCommand {
  private static double conversion = 819.1875395;  // rotationalUnits/meters

  private final double distance;
  private final PIDController distController, angleController;

  private double speed, turn;

  public MoveForwardGyroEncoder(double distance) {
    requires(Robot.drivetrain);
    this.distance = distance * conversion;

    distController = new PIDController(
      SmartDashboard.getNumber("Dist kP", 1),
      SmartDashboard.getNumber("Dist kI", 0),
      SmartDashboard.getNumber("Dist kD", 0),
      new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
          throw new UnsupportedOperationException();
        }
      
        @Override
        public double pidGet() {
          return Robot.drivetrain.getAverageEncoderDistance();
        }
      
        @Override
        public PIDSourceType getPIDSourceType() {
          throw new UnsupportedOperationException();
        }
      },
      o -> {speed = o;});
    angleController = new PIDController(
      SmartDashboard.getNumber("Angle kP", 1),
      SmartDashboard.getNumber("Angle kI", 0),
      SmartDashboard.getNumber("Angle kD", 0),
      Robot.drivetrain.getGyro(),
      o -> {turn = o;});
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    distController.setSetpoint(Robot.drivetrain.getAverageEncoderDistance() + distance);
    angleController.setSetpoint(Robot.drivetrain.getGyro().getAngle());

    System.out.println("Dist current: " + Robot.drivetrain.getAverageEncoderDistance() +
      " target: " + distController.getSetpoint());
    System.out.println("Angle current: " + Robot.drivetrain.getGyro().getAngle() +
      " target: " + angleController.getSetpoint());

    distController.setAbsoluteTolerance(25);
    angleController.setAbsoluteTolerance(5);

    distController.setOutputRange(-0.4, 0.4);
    angleController.setOutputRange(-0.1, 0.1);
    
    distController.enable();
    angleController.enable();
  }

  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDrive(speed, turn);
  }

  @Override
  protected boolean isFinished() {
    return distController.onTarget() && angleController.onTarget();
  }

  @Override
  protected void terminate() {
    distController.disable();
    angleController.disable();
  }
}
