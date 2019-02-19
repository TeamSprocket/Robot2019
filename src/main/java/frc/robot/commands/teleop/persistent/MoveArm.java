/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * TODO: Add docs
 */
public class MoveArm extends PersistentCommand {
  private static final double SPEED_MODIFIER = 0.5;
  private static final double UPPER_POT_LIMIT = 205, LOWER_POT_LIMIT = -5;

  public MoveArm() {
    requires(Arm.get());
  }

  @Override
  protected void execute() {
    double speed = OI.Controllers.gamepad.getRawAxis(5);
    Arm.get().setSpeed(OI.deadband(speed) * SPEED_MODIFIER);
  }

  @Override
  protected void terminate() {
    Arm.get().stop();
  }
}
