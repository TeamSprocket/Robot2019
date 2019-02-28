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
 * A PersistentCommand that controls the arm by changing the setpoint of a
 * PIDController using the right gamepad joystick, allowing for more robust
 * control.
 */
public class PIDMoveArm extends PersistentCommand {
  private static final double ARM_kP = 0.015, ARM_kI = 0.00, ARM_kD = 0.04;
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

    setDefaultConstants();
  }

  @Override
  protected void initialize() {
    Arm.get().setSetpoint(Arm.get().getPot().get());

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
        Arm.get().setSetpoint(85);
        break;
      case 90:
        Arm.get().setSetpoint(50);
        break;
      case 180:
        Arm.get().setSetpoint(5);
        break;
      case 270:
        Arm.get().setSetpoint(135);
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

  private void setDefaultConstants() {
    SmartDashboard.setDefaultNumber("ARM_kP", ARM_kP);
    SmartDashboard.setDefaultNumber("ARM_kI", ARM_kI);
    SmartDashboard.setDefaultNumber("ARM_kD", ARM_kD);
  }
}
