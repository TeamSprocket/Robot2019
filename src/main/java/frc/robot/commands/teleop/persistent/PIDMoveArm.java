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

public class PIDMoveArm extends PersistentCommand {
  private static final double ARM_OUTPUT_RANGE = 0.75;
  private static final double UPPER_POT_LIMIT = 205, LOWER_POT_LIMIT = -5;

  private final PIDController armController;

  private double setpoint = 0;

  public PIDMoveArm() {
    requires(Arm.get());

    armController = new PIDController(
      SmartDashboard.getNumber("ARM_kP", 0), 
      SmartDashboard.getNumber("ARM_kI", 0), 
      SmartDashboard.getNumber("ARM_kD", 0),
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
    armController.setSetpoint(setpoint);

    double axis = -OI.Controllers.gamepad.getRawAxis(5);
    SmartDashboard.putNumber("setpoint", armController.getSetpoint());
    SmartDashboard.putNumber("output", armController.get());

    SmartDashboard.putNumber("POV", OI.Controllers.gamepad.getPOV());
    switch(OI.Controllers.gamepad.getPOV()) {
      case 180:
        setpoint = 0;
        break;
      case 0:
        setpoint = 90;
        break;
    }

    if((axis < -0.1 && setpoint > LOWER_POT_LIMIT && !Arm.get().getBackLimitSwitch().get())
      || (axis > 0.1 && setpoint < UPPER_POT_LIMIT && !Arm.get().getFrontLimitSwitch().get())) {
      setpoint += 2.5 * axis;
    }
  }

  @Override
  protected void terminate() {
    armController.disable();
    Arm.get().stop();
  }

  // y~acos(x+b)+c
  // private static final double a1 = 0.238837, b1 = -27.3604, c1 = -0.354398;
  // private static final double a2 = 0.320962, b2 = -62.7432, c2 = 0.283038;

  // cim w/ hatch
  // y~a*sin(x+b)+c
}
