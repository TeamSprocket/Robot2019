/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import frc.robot.OI;
import frc.robot.Robot;
import frc.util.commands.teleop.persistent.PersistentCommand;

public class MoveArm extends PersistentCommand {
  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(OI.Controllers.gamepad.getRawAxis(5)) > 0.05) {
      Robot.arm.setSpeed(OI.Controllers.gamepad.getRawAxis(5)*0.5);
    }
    else {
      Robot.arm.setSpeed(0);
    }
  }

}
