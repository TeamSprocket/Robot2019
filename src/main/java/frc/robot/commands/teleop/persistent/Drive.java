/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import javax.lang.model.util.ElementScanner6;

import frc.robot.OI;
import frc.robot.Robot;
import frc.util.commands.teleop.persistent.PersistentCommand;

/**
 * TODO: Add docs
 */
public class Drive extends PersistentCommand {
  public Drive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    double speed = getSpeed();
    double turn = clamp(OI.Controllers.racingWheel.getX() * 1.5, -1, 1);

    if(Math.abs(speed) < 0.1)
      speed = 0.0;
    if(Math.abs(turn) < 0.1)
      turn = 0.0;

    Robot.drivetrain.arcadeDrive(speed, turn);
  }

  private double getSpeed() {
    if(OI.Controllers.racingWheel.getRawButton(6))
      return 1;
    else if(OI.Controllers.racingWheel.getRawButton(5))
      return -1;
    else
      return 0;
  }

  private double clamp(double val, double lower, double upper) {
    if(val < lower)
      return lower;
    else if(val > upper)
      return upper;
    else
      return val;
  }
}
