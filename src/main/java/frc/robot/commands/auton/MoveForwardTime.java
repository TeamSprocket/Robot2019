/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton;

import frc.robot.subsystems.Drivetrain;
import frc.util.commands.auton.AutonCommand;

public class MoveForwardTime extends AutonCommand {
  private final double duration;

  public MoveForwardTime(double duration) {
    requires(Drivetrain.get());

    this.duration = duration;
  }

  @Override
  protected void initialize() {
    setTimeout(duration);
  }

  @Override
  protected void execute() {
    Drivetrain.get().arcadeDrive(1.0, 0);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Drivetrain.get().stop();
  }

  @Override
  protected void interrupted() {
    Drivetrain.get().stop();
  }
}
