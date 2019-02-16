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

public class MoveArm extends PersistentCommand {
  private final double UPPER_POT_LIMIT = 100000;
  private final double LOWER_POT_LIMIT = 0;

  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Arm.get());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = OI.Controllers.gamepad.getRawAxis(5);
    // not tested yet
    if(Math.abs(speed) > 0.05) {
      // test for potentiometer limit and motion direction  
      if((Arm.get().getPot().get() > UPPER_POT_LIMIT && speed < 0) || 
      (Arm.get().getPot().get() < LOWER_POT_LIMIT && speed > 0)) {
        Arm.get().setSpeed(speed * 0.5);
      }
      // test for limit switches and motion direction
      if((Arm.get().getLimit1().get() && speed < 0) || 
      (Arm.get().getLimit2().get() && speed > 0)) {
        Arm.get().setSpeed(speed * 0.5);
      }
      // general limit test
      if(Arm.get().getPot().get() < UPPER_POT_LIMIT 
      && Arm.get().getPot().get() > LOWER_POT_LIMIT && 
      !Arm.get().getLimit1().get() && !Arm.get().getLimit2().get()) {
        Arm.get().setSpeed(speed * 0.5);
      }
    }
    else {
      Arm.get().setSpeed(0);
    }
  }
// 575, 350
}
