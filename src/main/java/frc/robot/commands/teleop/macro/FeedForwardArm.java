/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import frc.robot.OI;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.macro.MacroCommand;

public class FeedForwardArm extends MacroCommand {
  public FeedForwardArm() {
    requires(Arm.get());
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    double angle = Arm.get().getPot().get();


  }

  @Override
  protected boolean isFinished() {
    return !OI.Buttons.armFeedForwardButton.get();
  }

  @Override
  protected void terminate() {
    
  }
}
