/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.util.commands.instant.InstantCommand;
import frc.util.commands.teleop.macro.MacroCommand;

public class FeedForwardArm extends MacroCommand {
  private static final double a = -0.129699, b = -0.029082, c = 0.0319926;

  public FeedForwardArm() {
    requires(Arm.get());
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    Arm.get().setSpeed(a * Math.sin(b * Arm.get().getPot().get()) + c);
  }

  @Override
  protected boolean isFinished() {
    return !OI.Buttons.armFeedForwardButton.get();
  }

  @Override
  protected void terminate() {
    Arm.get().stop();
  }
}
