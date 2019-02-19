/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * TODO: Add docs
 */
public class PIDMoveArm extends PersistentCommand {
  private static final double ARM_kP = 0.02, ARM_kI = 0, ARM_kD = 0.01;
  private static final double ARM_OUTPUT_RANGE = 0.75;

  private static final double DELTA_SETPOINT_MULTIPLIER = 2.5;

  private final PIDController armController;

  public PIDMoveArm() {
    requires(Arm.get());

    armController = new PIDController(
      SmartDashboard.getNumber("ARM_kP", ARM_kP), 
      SmartDashboard.getNumber("ARM_kI", ARM_kI), 
      SmartDashboard.getNumber("ARM_kD", ARM_kD),
      Arm.get().getPot(),
      o -> Arm.get().setSpeed(-o));
  }

  @Override
  protected void initialize() {
    armController.setOutputRange(-ARM_OUTPUT_RANGE, ARM_OUTPUT_RANGE);
    armController.enable();
  }

  @Override
  protected void execute() {
    armController.setPID(
      SmartDashboard.getNumber("ARM_kP", 0), 
      SmartDashboard.getNumber("ARM_kI", 0), 
      SmartDashboard.getNumber("ARM_kD", 0));
    armController.setSetpoint(Arm.get().getSetpoint());

    SmartDashboard.putNumber("POV", OI.Controllers.gamepad.getPOV());
    switch(OI.Controllers.gamepad.getPOV()) {
      case 0:
        Arm.get().setSetpoint(90);
        break;
      case 90:
        Arm.get().setSetpoint(60);
        break;
      case 180:
        Arm.get().setSetpoint(0);
        break;
      case 270:
        Arm.get().setSetpoint(120);
        break;
    }

    double deltaSetpoint = -OI.Controllers.gamepad.getRawAxis(5);
    Arm.get().changeSetpoint(OI.deadband(deltaSetpoint) * DELTA_SETPOINT_MULTIPLIER);
  }

  @Override
  protected void terminate() {
    armController.disable();
    Arm.get().stop();
  }

  // TODO: Clean up

  // y~acos(x+b)+c
  // private static final double a1 = 0.238837, b1 = -27.3604, c1 = -0.354398;
  // private static final double a2 = 0.320962, b2 = -62.7432, c2 = 0.283038;

  // cim w/ hatch
  // y~a*sin(x+b)+c
}