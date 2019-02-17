/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.util.commands.teleop.macro.MacroCommand;
import frc.util.units.distances.Distance;

/**
 * A MacroCommand that implements an algorithm utilizing PIDControllers on both
 * the gyro and the encoders in order to maintain a straight heading.
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

  private final double targetDistance;
  // private final PIDController distController, angleController;

  private double speed, turn;

  public MoveForwardGyroEncoder(Distance distance) {
    requires(Drivetrain.get());
    targetDistance = distance.inPulses().getValue();

    // distController = new PIDController(
    //   SmartDashboard.getNumber("DIST_kP", DIST_kP),
    //   SmartDashboard.getNumber("DIST_kI", DIST_kI),
    //   SmartDashboard.getNumber("DIST_kD", DIST_kD),
    //   new PIDSource() {
    //     @Override
    //     public void setPIDSourceType(PIDSourceType pidSource) {
    //     }
      
    //     @Override
    //     public double pidGet() {
    //       return Drivetrain.get().getAverageEncoderDistance();
    //     }
      
    //     @Override
    //     public PIDSourceType getPIDSourceType() {
    //       return PIDSourceType.kDisplacement;
    //     }
    //   },
    //   o -> {speed = o;});
    // angleController = new PIDController(
    //   SmartDashboard.getNumber("ANGLE_kP", ANGLE_kP),
    //   SmartDashboard.getNumber("ANGLE_kI", ANGLE_kI),
    //   SmartDashboard.getNumber("ANGLE_kD", ANGLE_kD),
    //   Drivetrain.get().getGyro(),
    //   o -> {turn = o;});
  }

  @Override
  protected void initialize() {
    // distController.setPID(
    //   SmartDashboard.getNumber("DIST_kP", DIST_kP),
    //   SmartDashboard.getNumber("DIST_kI", DIST_kI),
    //   SmartDashboard.getNumber("DIST_kD", DIST_kD));
    // angleController.setPID(
    //   SmartDashboard.getNumber("ANGLE_kP", ANGLE_kP),
    //   SmartDashboard.getNumber("ANGLE_kI", ANGLE_kI),
    //   SmartDashboard.getNumber("ANGLE_kD", ANGLE_kD));

    // distController.setSetpoint(Drivetrain.get().getAverageEncoderDistance() + distance);
    // angleController.setSetpoint(Drivetrain.get().getGyro().getAngle());

    // System.out.println("Dist current: " + Drivetrain.get().getAverageEncoderDistance() +
    //   " target: " + distController.getSetpoint());
    // System.out.println("Angle current: " + Drivetrain.get().getGyro().getAngle() +
    //   " target: " + angleController.getSetpoint());

    // distController.setAbsoluteTolerance(DIST_TOLERANCE);
    // angleController.setAbsoluteTolerance(ANGLE_TOLERANCE);

    // distController.setOutputRange(-DIST_OUTPUT_RANGE, DIST_OUTPUT_RANGE);
    // angleController.setOutputRange(-ANGLE_OUTPUT_RANGE, ANGLE_OUTPUT_RANGE);
    
    // distController.enable();
    // angleController.enable();
  }

  @Override
  protected void execute() {
    Drivetrain.get().arcadeDrive(speed, turn);
  }

  @Override
  protected boolean isFinished() {
    // return distController.onTarget() && angleController.onTarget()
    //   && Math.abs(Drivetrain.get().getAverageEncoderRate()) <= DIST_RATE_TOLERANCE
    //   && Math.abs(Drivetrain.get().getGyro().getRate()) <= ANGLE_RATE_TOLERANCE;
    return false;
  }

  @Override
  protected void terminate() {
    // distController.disable();
    // angleController.disable();
  }
}
