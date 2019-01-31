/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

import frc.util.commands.auton.AutonCommand;

/**
 * TODO: Add docs
 */
public class Delay extends AutonCommand {
  private final double duration;

  public Delay(double duration) {
    this.duration = duration;
  }

  @Override
  protected void initialize() {
    setTimeout(duration);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
