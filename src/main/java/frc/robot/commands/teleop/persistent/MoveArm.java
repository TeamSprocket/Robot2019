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

public class MoveArm extends PersistentCommand {
  private static final double SPEED_MODIFIER = 0.5;
  // TODO: Find out limit difference
  private static final double UPPER_POT_LIMIT = 200, LOWER_POT_LIMIT = -5;

  public MoveArm() {
    requires(Arm.get());
  }

  @Override
  protected void execute() {
    double speed = -OI.Controllers.gamepad.getRawAxis(5);

    if((speed < 0.1 && Arm.get().getPot().get() < UPPER_POT_LIMIT && !Arm.get().getBackLimitSwitch().get())
      || (speed > 0.1 && Arm.get().getPot().get() > LOWER_POT_LIMIT && !Arm.get().getFrontLimitSwitch().get())) {
      Arm.get().setSpeed(speed * SPEED_MODIFIER);
      SmartDashboard.putNumber("Arm speed", speed * SPEED_MODIFIER);
    } else {
      Arm.get().stop();
    }
  }
// TODO: What's this???
// 575, 350
}
