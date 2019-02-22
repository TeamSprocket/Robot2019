/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * A PersistentCommand that controls the arm by setting speed using the right
 * gamepad joystick, allowing for more direct control.
 * 
 * This class is kept for testing purposes and should not be bound during
 * competition
 */
// @Deprecated
public class MoveArm extends PersistentCommand {
  private static final double SPEED_MODIFIER = 0.5;

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
