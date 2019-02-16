/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.util.commands.teleop.macro.MacroCommand;

/**
 * A macro command to be used in teleop mode that aligns the robot to a vision target.
 */
public class Align extends MacroCommand {
  private static final double ALIGN_kP = 0, ALIGN_kI = 0, ALIGN_kD = 0;
  private static final double DEFAULT_BASE_SPEED = 0.45;
  private static final double OUTPUT_RANGE = 0.3;

  static {
    SmartDashboard.putNumber("ALIGN_BASE_SPEED", DEFAULT_BASE_SPEED);
    SmartDashboard.putNumber("ALIGN_kP", ALIGN_kP);
    SmartDashboard.putNumber("ALIGN_kI", ALIGN_kI);
    SmartDashboard.putNumber("ALIGN_kD", ALIGN_kD);
  }
  
  private double tx, speed, baseSpeed;
  private PIDController controller;

  public Align() {
    requires(Drivetrain.get());
    baseSpeed = SmartDashboard.getNumber("ALIGN_BASE_SPEED", 0.45);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

    controller = new PIDController(
      SmartDashboard.getNumber("ALIGN_kP", ALIGN_kP),
      SmartDashboard.getNumber("ALIGN_kI", ALIGN_kI),
      SmartDashboard.getNumber("ALIGN_kD", ALIGN_kD),
      new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }
      
        @Override
        public double pidGet() {
          tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
          return tx;
        }
      
        @Override
        public PIDSourceType getPIDSourceType() {
          return PIDSourceType.kDisplacement;
        }
      },
      o -> {speed = o;});
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    controller.setPID(
      SmartDashboard.getNumber("ALIGN_kP", ALIGN_kP),
      SmartDashboard.getNumber("ALIGN_kI", ALIGN_kI),
      SmartDashboard.getNumber("ALIGN_kD", ALIGN_kD));
    controller.setSetpoint(0);
    controller.setOutputRange(-OUTPUT_RANGE, OUTPUT_RANGE);
    controller.enable();
  }

  @Override
  protected void execute() {
    System.out.println(tx);
    Drivetrain.get().arcadeDrive(baseSpeed, -speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return Math.abs(tx) <= 0.5 || Math.abs(lastOutput) <= 0.08;
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0) == 0;
  }

  @Override
  protected void terminate() {
    controller.disable();
  }
}
