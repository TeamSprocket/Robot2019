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
import frc.util.Constants;
import frc.util.commands.teleop.macro.MacroCommand;

/**
 * A macro command that moves the robot forward a specified distance, utilizing a gyro to keep the bot
 * moving straight.
 */
public class MoveForwardGyroEncoder extends MacroCommand {
  private static final double DIST_TOLERANCE = 40, ANGLE_TOLERANCE = 5;
  private static final double DIST_RATE_TOLERANCE = 150, ANGLE_RATE_TOLERANCE = 5;
  private static final double DIST_OUTPUT_RANGE = 0.8, ANGLE_OUTPUT_RANGE = 0.2;
  private static final double DIST_kP = 0.4, DIST_kI = 0.09, DIST_kD = 1.6;
  private static final double ANGLE_kP = 1.5, ANGLE_kI = 0.5, ANGLE_kD = 1.5;

  // TODO: See if this works
  static {
    SmartDashboard.putNumber("DIST_kP", DIST_kP);
    SmartDashboard.putNumber("DIST_kI", DIST_kI);
    SmartDashboard.putNumber("DIST_kD", DIST_kD);
    SmartDashboard.putNumber("ANGLE_kP", ANGLE_kP);
    SmartDashboard.putNumber("ANGLE_kI", ANGLE_kI);
    SmartDashboard.putNumber("ANGLE_kD", ANGLE_kD);
  }

  private final double distance;
  private final PIDController distController, angleController;

  private double speed, turn;

  public MoveForwardGyroEncoder(double distance) {
    requires(Robot.drivetrain);
    this.distance = distance * Constants.ENCODER_TO_METER;

    distController = new PIDController(
      SmartDashboard.getNumber("DIST_kP", DIST_kP),
      SmartDashboard.getNumber("DIST_kI", DIST_kI),
      SmartDashboard.getNumber("DIST_kD", DIST_kD),
      new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }
      
        @Override
        public double pidGet() {
          return Robot.drivetrain.getAverageEncoderDistance();
        }
      
        @Override
        public PIDSourceType getPIDSourceType() {
          return PIDSourceType.kDisplacement;
        }
      },
      o -> {speed = o;});
    angleController = new PIDController(
      SmartDashboard.getNumber("ANGLE_kP", ANGLE_kP),
      SmartDashboard.getNumber("ANGLE_kI", ANGLE_kI),
      SmartDashboard.getNumber("ANGLE_kD", ANGLE_kD),
      Robot.drivetrain.getGyro(),
      o -> {turn = o;});
  }

  @Override
  protected void initialize() {
    distController.setPID(
      SmartDashboard.getNumber("DIST_kP", DIST_kP),
      SmartDashboard.getNumber("DIST_kI", DIST_kI),
      SmartDashboard.getNumber("DIST_kD", DIST_kD));
    angleController.setPID(
      SmartDashboard.getNumber("ANGLE_kP", ANGLE_kP),
      SmartDashboard.getNumber("ANGLE_kI", ANGLE_kI),
      SmartDashboard.getNumber("ANGLE_kD", ANGLE_kD));

    distController.setSetpoint(Robot.drivetrain.getAverageEncoderDistance() + distance);
    angleController.setSetpoint(Robot.drivetrain.getGyro().getAngle());

    System.out.println("Dist current: " + Robot.drivetrain.getAverageEncoderDistance() +
      " target: " + distController.getSetpoint());
    System.out.println("Angle current: " + Robot.drivetrain.getGyro().getAngle() +
      " target: " + angleController.getSetpoint());

    distController.setAbsoluteTolerance(DIST_TOLERANCE);
    angleController.setAbsoluteTolerance(ANGLE_TOLERANCE);

    distController.setOutputRange(-DIST_OUTPUT_RANGE, DIST_OUTPUT_RANGE);
    angleController.setOutputRange(-ANGLE_OUTPUT_RANGE, ANGLE_OUTPUT_RANGE);
    
    distController.enable();
    angleController.enable();
  }

  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDrive(speed, turn);
  }

  @Override
  protected boolean isFinished() {
    return distController.onTarget() && angleController.onTarget()
      && Math.abs(Robot.drivetrain.getAverageEncoderRate()) <= DIST_RATE_TOLERANCE
      && Math.abs(Robot.drivetrain.getGyro().getRate()) <= ANGLE_RATE_TOLERANCE;
  }

  @Override
  protected void terminate() {
    distController.disable();
    angleController.disable();
  }
}
