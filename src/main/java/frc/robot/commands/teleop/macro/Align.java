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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.util.commands.teleop.macro.MacroCommand;

/**
 * A MacroCommand that aligns the robot to a vision target.
 */
public class Align extends MacroCommand {
  private static final double SPEED_BASE = 0.1, SPEED_INCREMENT = 0.2;
  private static final double TURN_BASE = 0.0, TURN_INCREMENT = 0;
  private static final double MAX_AREA = 15;
  private static final double kP = 0.13, kI = 0, kD = 0.3;

  private static final double OUTPUT_RANGE = 0.1;
  
  private final PIDController controller;

  private double turn;

  public Align() {
    requires(Drivetrain.get());

    controller = new PIDController(
      SmartDashboard.getNumber("ALIGN_kP", kP),
      SmartDashboard.getNumber("ALIGN_kI", kI),
      SmartDashboard.getNumber("ALIGN_kD", kD),
      new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }
      
        @Override
        public double pidGet() {
          return Limelight.get().getTx();
        }
      
        @Override
        public PIDSourceType getPIDSourceType() {
          return PIDSourceType.kDisplacement;
        }
      },
      o -> turn = o);

      setDefaultConstants();
  }

  @Override
  protected void initialize() {
    controller.setPID(
      SmartDashboard.getNumber("ALIGN_kP", kP),
      SmartDashboard.getNumber("ALIGN_kI", kI),
      SmartDashboard.getNumber("ALIGN_kD", kD));
    controller.setSetpoint(targetTxFromTa(Limelight.get().getTa()));
    controller.setOutputRange(-OUTPUT_RANGE, OUTPUT_RANGE);
    controller.enable();
  }

  public static final double kA = -1.44522, kB = 16.9672;
  private double targetTxFromTa(double ta) {
    return kA * ta + kB;
  }

  @Override
  protected void execute() {
    double speed = SmartDashboard.getNumber("ALIGN_SPEED_BASE", SPEED_BASE) + 
    SmartDashboard.getNumber("ALIGN_SPEED_INCREMENT", SPEED_INCREMENT) * 
    (SmartDashboard.getNumber("ALIGN_MAX_AREA", MAX_AREA) - Limelight.get().getTa()) / 
    SmartDashboard.getNumber("ALIGN_MAX_AREA", MAX_AREA);
    double turns = SmartDashboard.getNumber("ALIGN_TURN_BASE", TURN_BASE) +
    SmartDashboard.getNumber("ALIGN_TURN_INCREMENT", TURN_INCREMENT) * 
    (Limelight.get().getTa() / SmartDashboard.getNumber("ALIGN_MAX_AREA", MAX_AREA)) -
    turn;
    Drivetrain.get().arcadeDrive(-speed, turns);
    controller.setSetpoint(targetTxFromTa(Limelight.get().getTa()));
  }

  @Override
  protected boolean isFinished() {
    return Limelight.get().getTa() >= 7.5 || !Limelight.get().targetVisible();
  }

  @Override
  protected void terminate() {
    Drivetrain.get().stop();
    controller.disable();
  }

  private void setDefaultConstants() {
    SmartDashboard.putNumber("ALIGN_SPEED_BASE", SPEED_BASE);
    SmartDashboard.putNumber("ALIGN_SPEED_INCREMENT", SPEED_INCREMENT);
    SmartDashboard.putNumber("ALIGN_TURN_BASE", TURN_BASE);
    SmartDashboard.putNumber("ALIGN_TURN_INCREMENT", TURN_INCREMENT);
    SmartDashboard.putNumber("ALIGN_MAX_AREA", MAX_AREA);
    SmartDashboard.putNumber("ALIGN_kP", kP);
    SmartDashboard.putNumber("ALIGN_kI", kI);
    SmartDashboard.putNumber("ALIGN_kD", kD);
  }
}
