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
  private final double UPPER_POT_LIMIT = 100000;
  private final double LOWER_POT_LIMIT = 0;

  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = OI.Controllers.gamepad.getRawAxis(5);
    // not tested yet
    if(Math.abs(speed) > 0.05) {
      // test for potentiometer limit and motion direction  
      if((Robot.arm.getPot().get() > UPPER_POT_LIMIT && speed < 0) || 
      (Robot.arm.getPot().get() < LOWER_POT_LIMIT && speed > 0)) {
        Robot.arm.setSpeed(speed * 0.5);
      }
      // test for limit switches and motion direction
      if((Robot.arm.getLimit1().get() && speed < 0) || 
      (Robot.arm.getLimit2().get() && speed > 0)) {
        Robot.arm.setSpeed(speed * 0.5);
      }
      // general limit test
      if(Robot.arm.getPot().get() < UPPER_POT_LIMIT 
      && Robot.arm.getPot().get() > LOWER_POT_LIMIT && 
      !Robot.arm.getLimit1().get() && !Robot.arm.getLimit2().get()) {
        Robot.arm.setSpeed(speed * 0.5);
      }
    }
    else {
      Robot.arm.setSpeed(0);
    }
  }
// 575, 350
}
