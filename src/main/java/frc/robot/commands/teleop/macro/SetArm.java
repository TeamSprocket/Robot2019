/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.macro.MacroCommand;
import frc.util.units.angle.Degree;

public class SetArm extends MacroCommand {
  public static enum ArmPosition {
    // TODO: Find actual angles
    DOWN(0), HATCH(1), CARGO(2), TEST(45);

    private final double angle;

    private ArmPosition(double angle) {
      this.angle = angle;
    }

    public Degree getAngle() {
      return new Degree(angle);
    }
  }

  private static final double ARM_kP = 1, ARM_kI = 0, ARM_kD = 0, ARM_kF = 0;
  private static final double ARM_TOLERANCE = 4, ARM_RATE_TOLERANCE = 4;
  private static final double ARM_OUTPUT_RANGE = 0.6;
  
  private final PIDController armController;
  private final double targetAngle;

  public SetArm(ArmPosition pos) {
    this(pos.getAngle().getValue());
  }

  public SetArm(double angle) {
    requires(Arm.get());

    // SmartDashboard.putNumber("ARM_kP", ARM_kP);
    // SmartDashboard.putNumber("ARM_kI", ARM_kI);
    // SmartDashboard.putNumber("ARM_kD", ARM_kD);
    // SmartDashboard.putNumber("ARM_kF", ARM_kF);

    targetAngle = angle;

    armController = new PIDController(
      SmartDashboard.getNumber("ARM_kP", ARM_kP), 
      SmartDashboard.getNumber("ARM_kI", ARM_kI), 
      SmartDashboard.getNumber("ARM_kD", ARM_kD),
      SmartDashboard.getNumber("ARM_kF", ARM_kF),
      Arm.get().getPot(),
      Arm.get()::setSpeed);
  }

  @Override
  protected void initialize() {
    armController.setPID(
      SmartDashboard.getNumber("ARM_kP", ARM_kP), 
      SmartDashboard.getNumber("ARM_kI", ARM_kI), 
      SmartDashboard.getNumber("ARM_kD", ARM_kD),
      SmartDashboard.getNumber("ARM_kF", ARM_kF));
    armController.setSetpoint(targetAngle);
    armController.setAbsoluteTolerance(ARM_TOLERANCE);
    armController.setOutputRange(-ARM_OUTPUT_RANGE, ARM_OUTPUT_RANGE);
    armController.enable();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void terminate() {
    armController.disable();
  }
}
