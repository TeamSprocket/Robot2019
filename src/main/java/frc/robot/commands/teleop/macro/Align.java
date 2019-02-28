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
 * A MacroCommand that aligns the robot to a vision target.
 */
public class Align extends MacroCommand {
  private static final double ALIGN_kP = 0.2, ALIGN_kI = 0.0001, ALIGN_kD = 0.3;
  private static final double ALIGN_BASE_SPEED = 0.1, ALIGN_INCREMENT = 0.3, ALIGN_MAX_AREA = 20;
  private static final double ALIGN_BASE_TURN = 0.1;
  private static final double OUTPUT_RANGE = 0.1;
  
  private double tx, ty, ta, turn, baseSpeed;
  private boolean previousTyZero, previousPreviousTyZero;
  private PIDController controller;

  public Align() {
    requires(Drivetrain.get());
    baseSpeed = SmartDashboard.getNumber("ALIGN_BASE_SPEED", ALIGN_BASE_SPEED);
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
          tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
          ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
          return tx;
        }
      
        @Override
        public PIDSourceType getPIDSourceType() {
          return PIDSourceType.kDisplacement;
        }
      },
      o -> {turn = o;});
  }

  @Override
  protected void initialize() {
    baseSpeed = SmartDashboard.getNumber("ALIGN_BASE_SPEED", ALIGN_BASE_SPEED);
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
    previousTyZero = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0) == 0;
    System.out.println(tx + "\t" + ta);
    Drivetrain.get().arcadeDrive(SmartDashboard.getNumber("ALIGN_SPEED_BASE", ALIGN_BASE_SPEED) + 
    SmartDashboard.getNumber("ALIGN_SPEED_INCREMENT", 0) * 
    (SmartDashboard.getNumber("ALIGN_MAX_AREA", 0) - ta) / 
    SmartDashboard.getNumber("ALIGN_MAX_AREA", 0), 
    SmartDashboard.getNumber("ALIGN_TURN_BASE", 0) +
    SmartDashboard.getNumber("ALIGN_TURN_INCREMENT", 0) * 
    (ta / SmartDashboard.getNumber("ALIGN_MAX_AREA", 0)) -
    turn);
  }

  @Override
  protected boolean isFinished() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 0 && previousTyZero;
  }

  @Override
  protected void terminate() {
    Drivetrain.get().stop();
    controller.disable();
  }
}
