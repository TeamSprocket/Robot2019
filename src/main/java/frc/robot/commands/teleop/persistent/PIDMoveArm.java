/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.persistent.PersistentCommand;

public class PIDMoveArm extends PersistentCommand {
  private static final double ARM_OUTPUT_RANGE = 0.5;

  private static final double UPPER_POT_LIMIT = 205, LOWER_POT_LIMIT = -5;

  private final PIDController armController;

  private double setpoint = 0;

  public PIDMoveArm() {
    requires(Arm.get());

    SmartDashboard.putNumber("ARM_kP", 0.1);
    SmartDashboard.putNumber("ARM_kI", 0.0);
    SmartDashboard.putNumber("ARM_kD", 0.4);
    SmartDashboard.putNumber("ARM_kF", 0.4);

    armController = new PIDController(
      SmartDashboard.getNumber("ARM_kP", 0), 
      SmartDashboard.getNumber("ARM_kI", 0), 
      SmartDashboard.getNumber("ARM_kD", 0),
      SmartDashboard.getNumber("ARM_kF", 0),
      Arm.get().getPot(),
      Arm.get()::setSpeed);
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
      SmartDashboard.getNumber("ARM_kD", 0),
      feedForward());
    armController.setSetpoint(setpoint);

    double axis = -OI.Controllers.gamepad.getRawAxis(5);
    System.out.println(armController.getSetpoint());
    

    if((axis < -0.1 && Arm.get().getPot().get() < UPPER_POT_LIMIT && !Arm.get().getBackLimitSwitch().get())
      || (axis > 0.1 && Arm.get().getPot().get() > LOWER_POT_LIMIT && !Arm.get().getFrontLimitSwitch().get())) {
      setpoint += axis;
    }
  }

  private static final double a1 = 0.238837, b1 = -27.3604, c1 = -0.354398;
  private static final double a2 = 0.320962, b2 = -62.7432, c2 = 0.283038;
  
  private double feedForward() {
    double angle = Arm.get().getPot().get();

    double upperBound = a1 * Math.cos(angle + b1) + c1;
    double lowerBound = a2 * Math.cos(angle + b2) + c2;

    return (upperBound + lowerBound) / 2;
  }
}
